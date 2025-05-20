package vista;

import bd.ClienteDAO;
import bd.LineaPedidoDAO;
import bd.PedidoDAO;
import bd.ProductoDAO;
import modelo.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;

public class DetallePedidoDialog extends JDialog {

    private Usuario usuario;
    private Pedido pedido;
    private boolean guardado = false;
    private boolean editable = true;

    private JComboBox<Cliente> comboCliente;
    private JPanel panelProductos;
    private JLabel labelTotal;

    private Map<Producto, JSpinner> spinners = new HashMap<>();

    public DetallePedidoDialog(Pedido pedido, Usuario usuario) {
        super((Frame) null, true);
        this.usuario = usuario;
        this.pedido = pedido;
        setTitle(pedido == null ? "Nuevo Pedido" : "Detalle del Pedido");
        setSize(500, 600);
        setLocationRelativeTo(null);
        inicializar();
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        comboCliente.setEnabled(editable);
        for (JSpinner spinner : spinners.values()) {
            spinner.setEnabled(editable);
        }
    }

    private void inicializar() {
        setLayout(new BorderLayout());

        JPanel panelCliente = new JPanel();
        comboCliente = new JComboBox<>();

        if (usuario.getRol() == Rol.CLIENTE) {
            Cliente c = ClienteDAO.obtenerPorUsuario(usuario.getCorreo());
            comboCliente.addItem(c);
            comboCliente.setEnabled(false);
        } else {
            for (Cliente c : ClienteDAO.obtenerTodos()) {
                comboCliente.addItem(c);
            }
        }

        panelCliente.add(new JLabel("Cliente:"));
        panelCliente.add(comboCliente);
        add(panelCliente, BorderLayout.NORTH);

        panelProductos = new JPanel();
        panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));

        for (Producto p : ProductoDAO.obtenerTodos()) {
            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lbl = new JLabel(p.getNombre() + " (" + p.getStock() + " disponibles): ");
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, p.getStock(), 1));
            fila.add(lbl);
            fila.add(spinner);
            panelProductos.add(fila);
            spinners.put(p, spinner);
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        labelTotal = new JLabel("Total: 0.00 €");
        JButton btnCalcular = new JButton("Calcular total");
        btnCalcular.addActionListener(e -> calcularTotal());

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardarPedido());
        btnCancelar.addActionListener(e -> dispose());

        JPanel botones = new JPanel();
        botones.add(btnCalcular);
        botones.add(btnGuardar);
        botones.add(btnCancelar);

        panelInferior.add(labelTotal, BorderLayout.WEST);
        panelInferior.add(botones, BorderLayout.EAST);

        add(panelInferior, BorderLayout.SOUTH);

        if (pedido != null) {
            cargarPedido();
        }
    }

    private void cargarPedido() {
        comboCliente.setSelectedItem(ClienteDAO.obtenerPorId(pedido.getIdCliente()));
        Map<Integer, Integer> cantidades = LineaPedidoDAO.obtenerCantidadPorProducto(pedido.getIdPedido());
        for (Map.Entry<Producto, JSpinner> entry : spinners.entrySet()) {
            int cantidad = cantidades.getOrDefault(entry.getKey().getIdProducto(), 0);
            entry.getValue().setValue(cantidad);
        }
        calcularTotal();
    }

    private void calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, JSpinner> entry : spinners.entrySet()) {
            int cantidad = (int) entry.getValue().getValue();
            total += cantidad * entry.getKey().getPrecio();
        }
        labelTotal.setText("Total: " + String.format("%.2f", total) + " €");
    }

    private void guardarPedido() {
        Cliente cliente = (Cliente) comboCliente.getSelectedItem();
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente.");
            return;
        }

        Map<Producto, Integer> seleccionados = new HashMap<>();
        for (Map.Entry<Producto, JSpinner> entry : spinners.entrySet()) {
            int cantidad = (int) entry.getValue().getValue();
            if (cantidad > 0) {
                seleccionados.put(entry.getKey(), cantidad);
            }
        }

        if (seleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El pedido debe contener al menos un producto.");
            return;
        }

        if (pedido == null) {
            int idPedido = PedidoDAO.insertar(LocalDate.now(), cliente.getIdCliente());
            for (Map.Entry<Producto, Integer> entry : seleccionados.entrySet()) {
                LineaPedidoDAO.insertar(idPedido, entry.getKey().getIdProducto(), entry.getValue(), entry.getKey().getPrecio());
            }
        } else {
            PedidoDAO.actualizar(pedido.getIdPedido(), cliente.getIdCliente());
            LineaPedidoDAO.eliminarPorPedido(pedido.getIdPedido());
            for (Map.Entry<Producto, Integer> entry : seleccionados.entrySet()) {
                LineaPedidoDAO.insertar(pedido.getIdPedido(), entry.getKey().getIdProducto(), entry.getValue(), entry.getKey().getPrecio());
            }
        }

        guardado = true;
        dispose();
    }

    public boolean isGuardado() {
        return guardado;
    }
}
