package vista;

import modelo.Producto;
import util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class DetalleProductoDialog extends JDialog {
    private Producto producto;
    private boolean actualizado = false;

    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JTextField campoCategoria;
    private JTextField campoTalla;
    private JTextField campoColor;
    private JTextField campoStock;
    private JLabel etiquetaImagen;
    private String rutaImagen;

    public DetalleProductoDialog(Window owner, Producto producto) {
        super(owner, "Detalle del Producto", ModalityType.APPLICATION_MODAL);
        this.producto = producto;
        this.rutaImagen = producto.getImagen();

        setLayout(new BorderLayout());
        setSize(500, 600);
        setLocationRelativeTo(owner);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoNombre = new JTextField(producto.getNombre());
        campoPrecio = new JTextField(String.valueOf(producto.getPrecio()));
        campoCategoria = new JTextField(producto.getCategoria());
        campoTalla = new JTextField(producto.getTalla());
        campoColor = new JTextField(producto.getColor());
        campoStock = new JTextField(String.valueOf(producto.getStock()));
        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        cargarImagen(rutaImagen);

        int fila = 0;

        gbc.gridx = 0; gbc.gridy = fila;
        panelCentral.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelCentral.add(campoNombre, gbc);

        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelCentral.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        panelCentral.add(campoPrecio, gbc);

        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelCentral.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        panelCentral.add(campoCategoria, gbc);

        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelCentral.add(new JLabel("Talla:"), gbc);
        gbc.gridx = 1;
        panelCentral.add(campoTalla, gbc);

        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelCentral.add(new JLabel("Color:"), gbc);
        gbc.gridx = 1;
        panelCentral.add(campoColor, gbc);

        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelCentral.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1;
        panelCentral.add(campoStock, gbc);

        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panelCentral.add(etiquetaImagen, gbc);

        fila++;
        gbc.gridy = fila;
        JButton botonCambiarImagen = new JButton("Cambiar Imagen");
        panelCentral.add(botonCambiarImagen, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Botones inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonGuardar = new JButton("Guardar");
        JButton botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonCancelar);
        panelBotones.add(botonGuardar);
        add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        botonCambiarImagen.addActionListener(e -> cambiarImagen());
        botonCancelar.addActionListener(e -> dispose());
        botonGuardar.addActionListener(this::guardarCambios);
    }

    private void cargarImagen(String ruta) {
        ImageIcon icono = ImageUtils.cargarMiniatura(ruta, 300, 300);
        etiquetaImagen.setIcon(icono);
    }

    private void cambiarImagen() {
        JFileChooser fileChooser = new JFileChooser("imagenes/");
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            rutaImagen = "imagenes/" + archivoSeleccionado.getName();
            cargarImagen(rutaImagen);
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
            actualizado = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isActualizado() {
        return actualizado;
    }

    public Producto getProductoActualizado() {
        return producto;
    }
}
