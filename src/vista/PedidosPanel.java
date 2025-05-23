/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import modelo.Usuario;
import modelo.Rol;
import modelo.Pedido;
import modelo.Cliente;
import modelo.Sesion;
import bd.PedidoDAO;
import bd.ClienteDAO;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The Class PedidosPanel.
 */
public class PedidosPanel extends JPanel {

    /**
	 * The usuario.
	 */
    private Usuario usuario;
    
    /**
	 * The tabla.
	 */
    private JTable tabla;
    
    /**
	 * The modelo tabla.
	 */
    private PedidoTableModel modeloTabla;
    
    /**
	 * The sorter.
	 */
    private TableRowSorter<PedidoTableModel> sorter;
    
    /**
	 * The campo filtro cliente.
	 */
    private JTextField campoFiltroCliente;
    
    /**
	 * The btn ver.
	 */
    private JButton btnAnadir, btnEditar, btnEliminar, btnVer;

    /**
	 * Instantiates a new pedidos panel.
	 *
	 * @param usuario the usuario
	 */
    public PedidosPanel(Usuario usuario) {
        this.usuario = usuario;
        inicializarComponentes();
        cargarPedidos();
        aplicarPermisos();
    }

    /**
	 * Inicializar componentes.
	 */
    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Filtros
        JPanel panelFiltros = new JPanel();
        campoFiltroCliente = new JTextField(15);
        panelFiltros.add(new JLabel("Filtrar por cliente:"));
        panelFiltros.add(campoFiltroCliente);
        campoFiltroCliente.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
        });
        add(panelFiltros, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new PedidoTableModel();              // ✅ Crear modelo vacío
        cargarPedidos();                                   // ✅ Asignar datos ANTES del sorter
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(modeloTabla);        // ✅ Crear sorter DESPUÉS de tener datos
        tabla.setRowSorter(sorter);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        btnAnadir = new JButton("Añadir");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnVer = new JButton("Ver Detalle");

        panelBotones.add(btnAnadir);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVer);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnAnadir.addActionListener(e -> anadirPedido());
        btnEditar.addActionListener(e -> editarPedido());
        btnEliminar.addActionListener(e -> eliminarPedido());
        btnVer.addActionListener(e -> verDetalle());
    }


    /**
	 * Aplicar filtro.
	 */
    private void aplicarFiltro() {
        String texto = campoFiltroCliente.getText().toLowerCase();
        sorter.setRowFilter(new RowFilter<>() {
            public boolean include(Entry<? extends PedidoTableModel, ? extends Integer> entry) {
                Pedido p = modeloTabla.getPedidoAt(entry.getIdentifier());
                return ClienteDAO.obtenerNombrePorId(p.getIdCliente()).toLowerCase().contains(texto);
            }
        });
    }

    /**
	 * Cargar pedidos.
	 */
    private void cargarPedidos() {
        if (usuario.getRol() == Rol.CLIENTE && Sesion.clienteActual != null) {
            modeloTabla.setPedidos(PedidoDAO.obtenerPorCliente(Sesion.clienteActual.getIdCliente()));
        } else {
            modeloTabla.setPedidos(PedidoDAO.obtenerTodos());
        }
    }

    /**
	 * Aplicar permisos.
	 */
    private void aplicarPermisos() {
        if (usuario.getRol() == Rol.CLIENTE) {
            btnAnadir.setEnabled(true);
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }

    /**
	 * Anadir pedido.
	 */
    private void anadirPedido() {
        DetallePedidoDialog dialogo = new DetallePedidoDialog(null, usuario);
        dialogo.setVisible(true);
        if (dialogo.isGuardado()) cargarPedidos();
    }

    /**
	 * Editar pedido.
	 */
    private void editarPedido() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            Pedido pedido = modeloTabla.getPedidoAt(sorter.convertRowIndexToModel(fila));
            boolean esHoy = pedido.getFecha().equals(LocalDate.now());
            boolean clientePropio = usuario.getRol() != Rol.CLIENTE ||
                    (Sesion.clienteActual != null && Sesion.clienteActual.getIdCliente() == pedido.getIdCliente());

            if (clientePropio && esHoy) {
                DetallePedidoDialog dialogo = new DetallePedidoDialog(pedido, usuario);
                dialogo.setVisible(true);
                if (dialogo.isGuardado()) cargarPedidos();
            } else {
                JOptionPane.showMessageDialog(this, "Solo puedes editar pedidos propios del día actual.");
            }
        }
    }

    /**
	 * Eliminar pedido.
	 */
    private void eliminarPedido() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            Pedido pedido = modeloTabla.getPedidoAt(sorter.convertRowIndexToModel(fila));
            boolean esHoy = pedido.getFecha().equals(LocalDate.now());
            boolean clientePropio = usuario.getRol() != Rol.CLIENTE ||
                    (Sesion.clienteActual != null && Sesion.clienteActual.getIdCliente() == pedido.getIdCliente());

            if (clientePropio && esHoy) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar pedido?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    PedidoDAO.eliminar(pedido.getIdPedido());
                    cargarPedidos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Solo puedes eliminar pedidos propios del día actual.");
            }
        }
    }

    /**
	 * Ver detalle.
	 */
    private void verDetalle() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            Pedido pedido = modeloTabla.getPedidoAt(sorter.convertRowIndexToModel(fila));
            DetallePedidoDialog dialogo = new DetallePedidoDialog(pedido, usuario);
            dialogo.setEditable(false);
            dialogo.setVisible(true);
        }
    }
}
