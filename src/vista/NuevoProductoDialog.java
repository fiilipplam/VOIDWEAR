package vista;

import modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class NuevoProductoDialog extends JDialog {

    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JComboBox<String> comboCategoria;
    private JComboBox<String> comboTalla;
    private JComboBox<String> comboColor;
    private JTextField campoStock;
    private JLabel etiquetaImagen;
    private String rutaImagenRelativa;

    private Producto productoCreado;
    private boolean guardado = false;

    public NuevoProductoDialog() {
        setModal(true);
        setTitle("Nuevo Producto");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(0, 2, 5, 5));
        campoNombre = new JTextField();
        campoPrecio = new JTextField();
        comboCategoria = new JComboBox<>(new String[]{"Camisetas", "Pantalones", "Sudaderas", "Zapatillas", "Accesorios"});
        comboTalla = new JComboBox<>(new String[]{"S", "M", "L", "XL"});
        comboColor = new JComboBox<>(new String[]{"Rojo", "Negro", "Blanco", "Gris", "Verde"});
        campoStock = new JTextField();
        etiquetaImagen = new JLabel("Seleccionar imagen", SwingConstants.CENTER);

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel("Precio:"));
        panelCampos.add(campoPrecio);
        panelCampos.add(new JLabel("Categoría:"));
        panelCampos.add(comboCategoria);
        panelCampos.add(new JLabel("Talla:"));
        panelCampos.add(comboTalla);
        panelCampos.add(new JLabel("Color:"));
        panelCampos.add(comboColor);
        panelCampos.add(new JLabel("Stock:"));
        panelCampos.add(campoStock);
        panelCampos.add(new JLabel("Imagen:"));
        panelCampos.add(etiquetaImagen);

        add(panelCampos, BorderLayout.CENTER);

        JButton btnSeleccionarImagen = new JButton("Seleccionar imagen");
        btnSeleccionarImagen.addActionListener(e -> seleccionarImagen());

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            if (validarCampos()) {
                crearProducto();
                guardado = true;
                dispose();
            }
        });

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnSeleccionarImagen);
        panelInferior.add(btnGuardar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private boolean validarCampos() {
        if (campoNombre.getText().trim().isEmpty() ||
            campoPrecio.getText().trim().isEmpty() ||
            campoStock.getText().trim().isEmpty() ||
            rutaImagenRelativa == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return false;
        }
        try {
            Double.parseDouble(campoPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número.");
            return false;
        }
        try {
            Integer.parseInt(campoStock.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El stock debe ser un número entero.");
            return false;
        }
        return true;
    }

    private void seleccionarImagen() {
        JFileChooser selector = new JFileChooser("recursos/imagenes/productos");
        selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = selector.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            String rutaAbsoluta = archivo.getAbsolutePath().replace("\\", "/");
            int index = rutaAbsoluta.indexOf("recursos/");
            if (index != -1) {
                rutaImagenRelativa = rutaAbsoluta.substring(index);
                etiquetaImagen.setText(archivo.getName());
            } else {
                JOptionPane.showMessageDialog(this, "La imagen debe estar en la carpeta recursos/imagenes/productos");
                rutaImagenRelativa = null;
            }
        }
    }

    private void crearProducto() {
        productoCreado = new Producto();
        productoCreado.setNombre(campoNombre.getText().trim());
        productoCreado.setPrecio(Double.parseDouble(campoPrecio.getText().trim()));
        productoCreado.setCategoria((String) comboCategoria.getSelectedItem());
        productoCreado.setTalla((String) comboTalla.getSelectedItem());
        productoCreado.setColor((String) comboColor.getSelectedItem());
        productoCreado.setStock(Integer.parseInt(campoStock.getText().trim()));
        productoCreado.setImagen(rutaImagenRelativa);
    }

    public boolean seGuardo() {
        return guardado;
    }

    public Producto getProductoCreado() {
        return productoCreado;
    }
}
