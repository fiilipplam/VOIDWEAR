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
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.Producto;
import util.ImageUtils;

/**
 * The Class DetalleProductoDialog.
 */
public class DetalleProductoDialog extends JDialog {
	
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 6237917594235090269L;
	
	/**
	 * The producto.
	 */
	private Producto producto;
	
	/**
	 * The etiqueta imagen.
	 */
	private JLabel etiquetaImagen;
	
	/**
	 * The panel central.
	 */
	private JPanel panelCentral;
	
	/**
	 * The lbl nombre.
	 */
	private JLabel lblNombre;
	
	/**
	 * The lbl precio.
	 */
	private JLabel lblPrecio;
	
	/**
	 * The lbl categoria.
	 */
	private JLabel lblCategoria;
	
	/**
	 * The lbl talla.
	 */
	private JLabel lblTalla;
	
	/**
	 * The lbl color.
	 */
	private JLabel lblColor;
	
	/**
	 * The lbl stock.
	 */
	private JLabel lblStock;
	
	/**
	 * The val nombre.
	 */
	private JLabel valNombre;
	
	/**
	 * The val precio.
	 */
	private JLabel valPrecio;
	
	/**
	 * The val categoria.
	 */
	private JLabel valCategoria;
	
	/**
	 * The val talla.
	 */
	private JLabel valTalla;
	
	/**
	 * The val color.
	 */
	private JLabel valColor;
	
	/**
	 * The val stock.
	 */
	private JLabel valStock;
	
	/**
	 * The boton cerrar.
	 */
	private JButton botonCerrar;
	
	/**
	 * The panel botones.
	 */
	private JPanel panelBotones;
	
	/**
	 * The icono.
	 */
	private ImageIcon icono;

	/**
	 * Instantiates a new detalle producto dialog.
	 *
	 * @param owner    the owner
	 * @param producto the producto
	 */
	public DetalleProductoDialog(Window owner, Producto producto) {
		super(owner, "Detalles del Producto", ModalityType.APPLICATION_MODAL);
		this.producto = producto;

		getContentPane().setLayout(new BorderLayout());
		setSize(800, 511);
		setLocationRelativeTo(owner);

		panelCentral = new JPanel();
		panelCentral.setLayout(null);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(409, 116, 100, 25);
		panelCentral.add(lblNombre);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(409, 156, 100, 25);
		panelCentral.add(lblPrecio);

		lblCategoria = new JLabel("Categoría:");
		lblCategoria.setBounds(409, 196, 100, 25);
		panelCentral.add(lblCategoria);

		lblTalla = new JLabel("Talla:");
		lblTalla.setBounds(409, 236, 100, 25);
		panelCentral.add(lblTalla);

		lblColor = new JLabel("Color:");
		lblColor.setBounds(409, 276, 100, 25);
		panelCentral.add(lblColor);

		lblStock = new JLabel("Stock:");
		lblStock.setBounds(409, 316, 100, 25);
		panelCentral.add(lblStock);

		valNombre = new JLabel(producto.getNombre());
		valNombre.setBounds(509, 116, 200, 25);
		panelCentral.add(valNombre);

		valPrecio = new JLabel(String.valueOf(producto.getPrecio()));
		valPrecio.setBounds(509, 156, 200, 25);
		panelCentral.add(valPrecio);

		valCategoria = new JLabel(producto.getCategoria());
		valCategoria.setBounds(509, 196, 200, 25);
		panelCentral.add(valCategoria);

		valTalla = new JLabel(producto.getTalla());
		valTalla.setBounds(509, 236, 200, 25);
		panelCentral.add(valTalla);

		valColor = new JLabel(producto.getColor());
		valColor.setBounds(509, 276, 200, 25);
		panelCentral.add(valColor);

		valStock = new JLabel(String.valueOf(producto.getStock()));
		valStock.setBounds(509, 316, 200, 25);
		panelCentral.add(valStock);

		etiquetaImagen = new JLabel();
		etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaImagen.setBounds(50, 73, 300, 300);
		cargarImagen(producto.getImagen());
		panelCentral.add(etiquetaImagen);

		getContentPane().add(panelCentral, BorderLayout.CENTER);

		panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botonCerrar = new JButton("Cerrar");
		panelBotones.add(botonCerrar);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		botonCerrar.addActionListener(e -> dispose());
	}

	/**
	 * Cargar imagen.
	 *
	 * @param ruta the ruta
	 */
	private void cargarImagen(String ruta) {
		icono = ImageUtils.cargarMiniatura(ruta, 300, 300);
		etiquetaImagen.setIcon(icono);
	}
}
