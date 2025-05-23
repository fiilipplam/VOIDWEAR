/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import bd.ClienteDAO;
import bd.LineaPedidoDAO;
import bd.PedidoDAO;
import bd.ProductoDAO;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Producto;
import modelo.Rol;
import modelo.Usuario;
import modelo.Sesion;

/**
 * The Class DetallePedidoDialog.
 */
public class DetallePedidoDialog extends JDialog {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 6719692910702842172L;
	
	/**
	 * The usuario.
	 */
	private Usuario usuario;
	
	/**
	 * The pedido.
	 */
	private Pedido pedido;
	
	/**
	 * The guardado.
	 */
	private boolean guardado = false;
	
	/**
	 * The editable.
	 */
	private boolean editable = true;

	/**
	 * The combo cliente.
	 */
	private JComboBox<Cliente> comboCliente;
	
	/**
	 * The panel productos.
	 */
	private JPanel panelProductos;
	
	/**
	 * The label total.
	 */
	private JLabel labelTotal;
	
	/**
	 * The panel cliente.
	 */
	private JPanel panelCliente;
	
	/**
	 * The fila.
	 */
	private JPanel fila;
	
	/**
	 * The lbl.
	 */
	private JLabel lbl;
	
	/**
	 * The spinner.
	 */
	private JSpinner spinner;
	
	/**
	 * The scroll.
	 */
	private JScrollPane scroll;
	
	/**
	 * The panel inferior.
	 */
	private JPanel panelInferior;
	
	/**
	 * The btn calcular.
	 */
	private JButton btnCalcular;
	
	/**
	 * The btn guardar.
	 */
	private JButton btnGuardar;
	
	/**
	 * The btn cancelar.
	 */
	private JButton btnCancelar;
	
	/**
	 * The botones.
	 */
	private JPanel botones;

	/**
	 * The spinners.
	 */
	private Map<Producto, JSpinner> spinners = new HashMap<>();

	/**
	 * Instantiates a new detalle pedido dialog.
	 *
	 * @param pedido  the pedido
	 * @param usuario the usuario
	 */
	public DetallePedidoDialog(Pedido pedido, Usuario usuario) {
		super((Frame) null, true);
		this.usuario = usuario;
		this.pedido = pedido;
		
		if (Sesion.esCliente()) {
			if (comboCliente != null) comboCliente.setVisible(false);
			if (panelCliente != null) panelCliente.setVisible(false);
		}
		
		setTitle(pedido == null ? "Nuevo Pedido" : "Detalle del Pedido");
		setSize(500, 600);
		setLocationRelativeTo(null);
		inicializar();
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable the new editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
		comboCliente.setEnabled(editable);
		for (JSpinner spinner : spinners.values()) {
			spinner.setEnabled(editable);
		}
	}

	/**
	 * Inicializar.
	 */
	private void inicializar() {
		setLayout(new BorderLayout());

		panelCliente = new JPanel();
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
			fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
			lbl = new JLabel(p.getNombre() + " (" + p.getStock() + " disponibles): ");
			spinner = new JSpinner(new SpinnerNumberModel(0, 0, p.getStock(), 1));
			fila.add(lbl);
			fila.add(spinner);
			panelProductos.add(fila);
			spinners.put(p, spinner);
		}

		scroll = new JScrollPane(panelProductos);
		add(scroll, BorderLayout.CENTER);

		panelInferior = new JPanel(new BorderLayout());
		labelTotal = new JLabel("Total: 0.00 €");
		btnCalcular = new JButton("Calcular total");
		btnCalcular.addActionListener(e -> calcularTotal());

		btnGuardar = new JButton("Guardar");
		btnCancelar = new JButton("Cancelar");

		btnGuardar.addActionListener(e -> guardarPedido());
		btnCancelar.addActionListener(e -> dispose());

		botones = new JPanel();
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

	/**
	 * Cargar pedido.
	 */
	private void cargarPedido() {
		comboCliente.setSelectedItem(ClienteDAO.obtenerPorId(pedido.getIdCliente()));
		Map<Integer, Integer> cantidades = LineaPedidoDAO.obtenerCantidadPorProducto(pedido.getIdPedido());
		for (Map.Entry<Producto, JSpinner> entry : spinners.entrySet()) {
			int cantidad = cantidades.getOrDefault(entry.getKey().getIdProducto(), 0);
			entry.getValue().setValue(cantidad);
		}
		calcularTotal();
	}

	/**
	 * Calcular total.
	 */
	private void calcularTotal() {
		double total = 0;
		for (Map.Entry<Producto, JSpinner> entry : spinners.entrySet()) {
			int cantidad = (int) entry.getValue().getValue();
			total += cantidad * entry.getKey().getPrecio();
		}
		labelTotal.setText("Total: " + String.format("%.2f", total) + " €");
	}

	/**
	 * Guardar pedido.
	 */
	private void guardarPedido() {
		Cliente cliente;

		if (Sesion.esCliente()) {
			cliente = Sesion.clienteActual;
		} else {
			cliente = (Cliente) comboCliente.getSelectedItem();
			if (cliente == null) {
				JOptionPane.showMessageDialog(this, "Selecciona un cliente.");
				return;
			}
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


	/**
	 * Checks if is guardado.
	 *
	 * @return true, if is guardado
	 */
	public boolean isGuardado() {
		return guardado;
	}
}
