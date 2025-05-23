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
import bd.UsuarioDAO;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * The Class UsuariosPanel.
 */
public class UsuariosPanel extends JPanel {

    /**
	 * The tabla usuarios.
	 */
    private JTable tablaUsuarios;
    
    /**
	 * The modelo tabla.
	 */
    private UsuarioTableModel modeloTabla;
    
    /**
	 * The sorter.
	 */
    private TableRowSorter<UsuarioTableModel> sorter;
    
    /**
	 * The campo buscar correo.
	 */
    private JTextField campoBuscarNombre, campoBuscarCorreo;
    
    /**
	 * The combo rol.
	 */
    private JComboBox<Rol> comboRol;
    
    /**
	 * The btn eliminar.
	 */
    private JButton btnAnadir, btnEditar, btnEliminar;
    
    /**
	 * The usuario actual.
	 */
    private Usuario usuarioActual;

    /**
	 * Instantiates a new usuarios panel.
	 *
	 * @param usuarioActual the usuario actual
	 */
    public UsuariosPanel(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        inicializarComponentes();
        cargarUsuarios();
    }

    /**
	 * Inicializar componentes.
	 */
    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Filtros
        JPanel panelFiltros = new JPanel(new FlowLayout());
        campoBuscarNombre = new JTextField(10);
        campoBuscarCorreo = new JTextField(10);
        comboRol = new JComboBox<>(Rol.values());

        panelFiltros.add(new JLabel("Nombre:"));
        panelFiltros.add(campoBuscarNombre);
        panelFiltros.add(new JLabel("Correo:"));
        panelFiltros.add(campoBuscarCorreo);
        panelFiltros.add(new JLabel("Rol:"));
        panelFiltros.add(comboRol);

        add(panelFiltros, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new UsuarioTableModel();
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(modeloTabla);
        tablaUsuarios.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAnadir = new JButton("Añadir");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        btnAnadir.addActionListener(e -> mostrarDialogo(null));
        btnEditar.addActionListener(e -> editarSeleccionado());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());

        panelBotones.add(btnAnadir);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // Filtros dinámicos
        DocumentListener filtro = new DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
        };
        campoBuscarNombre.getDocument().addDocumentListener(filtro);
        campoBuscarCorreo.getDocument().addDocumentListener(filtro);
        comboRol.addActionListener(e -> aplicarFiltro());
    }

    /**
	 * Aplicar filtro.
	 */
    private void aplicarFiltro() {
        RowFilter<UsuarioTableModel, Object> rf = new RowFilter<>() {
            public boolean include(Entry<? extends UsuarioTableModel, ? extends Object> entry) {
                Usuario u = modeloTabla.getUsuarioAt((int) entry.getIdentifier());
                String nombre = campoBuscarNombre.getText().toLowerCase();
                String correo = campoBuscarCorreo.getText().toLowerCase();
                Rol rol = (Rol) comboRol.getSelectedItem();
                return u.getNombre().toLowerCase().contains(nombre)
                        && u.getCorreo().toLowerCase().contains(correo)
                        && (rol == null || u.getRol() == rol);
            }
        };
        sorter.setRowFilter(rf);
    }

    /**
	 * Cargar usuarios.
	 */
    private void cargarUsuarios() {
        modeloTabla.setUsuarios(UsuarioDAO.obtenerTodos());
    }

    /**
	 * Mostrar dialogo.
	 *
	 * @param usuario the usuario
	 */
    private void mostrarDialogo(Usuario usuario) {
        UsuarioDialog dialogo = new UsuarioDialog(SwingUtilities.getWindowAncestor(this), usuario);
        dialogo.setVisible(true);
        if (dialogo.isGuardado()) {
            cargarUsuarios();
        }
    }

    /**
	 * Editar seleccionado.
	 */
    private void editarSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila >= 0) {
            Usuario u = modeloTabla.getUsuarioAt(sorter.convertRowIndexToModel(fila));
            mostrarDialogo(u);
        }
    }

    /**
	 * Eliminar seleccionado.
	 */
    private void eliminarSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila >= 0) {
            Usuario u = modeloTabla.getUsuarioAt(sorter.convertRowIndexToModel(fila));
            if (u.getRol() == Rol.GESTOR) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar a otro gestor.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar al usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                UsuarioDAO.eliminar(u);
                cargarUsuarios();
            }
        }
    }
}
