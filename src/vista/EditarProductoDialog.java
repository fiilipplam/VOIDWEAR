package vista;

import modelo.Producto;
import util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class EditarProductoDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2765952674077970217L;
	private Producto producto;
    private boolean cambiosGuardados = false;

    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JTextField campoCategoria;
    private JTextField campoTalla;
    private JTextField campoColor;
    private JTextField campoStock;
    private JLabel etiquetaImagen;
    private String rutaImagen;

    public EditarProductoDialog(Window owner, Producto producto) {
        super(owner, "Editar Producto", ModalityType.APPLICATION_MODAL);
        this.producto = producto;
        this.rutaImagen = producto.getImagen();

        getContentPane().setLayout(new BorderLayout());
        setSize(814, 544);
        setLocationRelativeTo(owner);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);

        // Campos
        campoNombre = new JTextField(producto.getNombre());
        campoPrecio = new JTextField(String.valueOf(producto.getPrecio()));
        campoCategoria = new JTextField(producto.getCategoria());
        campoTalla = new JTextField(producto.getTalla());
        campoColor = new JTextField(producto.getColor());
        campoStock = new JTextField(String.valueOf(producto.getStock()));
        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        cargarImagen(rutaImagen);

        // Labels separados
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(409, 116, 100, 25);
        panelCentral.add(lblNombre);
        campoNombre.setBounds(509, 116, 200, 25);
        panelCentral.add(campoNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(409, 156, 100, 25);
        panelCentral.add(lblPrecio);
        campoPrecio.setBounds(509, 156, 200, 25);
        panelCentral.add(campoPrecio);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(409, 196, 100, 25);
        panelCentral.add(lblCategoria);
        campoCategoria.setBounds(509, 196, 200, 25);
        panelCentral.add(campoCategoria);

        JLabel lblTalla = new JLabel("Talla:");
        lblTalla.setBounds(409, 236, 100, 25);
        panelCentral.add(lblTalla);
        campoTalla.setBounds(509, 236, 200, 25);
        panelCentral.add(campoTalla);

        JLabel lblColor = new JLabel("Color:");
        lblColor.setBounds(409, 276, 100, 25);
        panelCentral.add(lblColor);
        campoColor.setBounds(509, 276, 200, 25);
        panelCentral.add(campoColor);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(409, 316, 100, 25);
        panelCentral.add(lblStock);
        campoStock.setBounds(509, 316, 200, 25);
        panelCentral.add(campoStock);

        etiquetaImagen.setBounds(50, 73, 300, 300);
        panelCentral.add(etiquetaImagen);

        JButton botonCambiarImagen = new JButton("Cambiar Imagen");
        botonCambiarImagen.setBounds(97, 386, 200, 25);
        panelCentral.add(botonCambiarImagen);

        getContentPane().add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonGuardar = new JButton("Guardar");
        JButton botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);
        panelBotones.add(botonGuardar);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        botonCambiarImagen.addActionListener(e -> cambiarImagen());
        botonCancelar.addActionListener(e -> dispose());
        botonGuardar.addActionListener(this::guardarCambios);
    }

    private void cargarImagen(String ruta) {
        ImageIcon icono = ImageUtils.cargarMiniatura(ruta, 300, 300);
        etiquetaImagen.setIcon(icono);
    }

    private void cambiarImagen() {
        JFileChooser selector = new JFileChooser("recursos/imagenes/productos");
        selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = selector.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            String rutaAbsoluta = archivo.getAbsolutePath().replace("\\", "/");
            int index = rutaAbsoluta.indexOf("recursos/");
            if (index != -1) {
                rutaImagen = rutaAbsoluta.substring(index);
                etiquetaImagen.setText(archivo.getName());
                cargarImagen(rutaImagen);
            } else {
                JOptionPane.showMessageDialog(this, "La imagen debe estar en la carpeta recursos/imagenes/productos");
                rutaImagen = null;
            }
        }
    }

    private void guardarCambios(ActionEvent e) {
        try {
            producto.setNombre(campoNombre.getText());
            producto.setPrecio(Double.parseDouble(campoPrecio.getText()));
            producto.setCategoria(campoCategoria.getText());
            producto.setTalla(campoTalla.getText());
            producto.setColor(campoColor.getText());
            producto.setStock(Integer.parseInt(campoStock.getText()));
            producto.setImagen(rutaImagen);
            cambiosGuardados = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean seGuardaronCambios() {
        return cambiosGuardados;
    }

    public Producto getProductoActualizado() {
        return producto;
    }
}
