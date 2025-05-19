package vista;

import modelo.Producto;
import util.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class DetalleProductoDialog extends JDialog {

    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCategoria;
    private JLabel lblTalla;
    private JLabel lblColor;
    private JLabel lblStock;
    private JLabel lblImagen;

    public DetalleProductoDialog(JFrame parent, Producto producto) {
        super(parent, "Detalles del Producto", true);
        setLayout(new BorderLayout(10, 10));
        setSize(500, 600);
        setLocationRelativeTo(parent);

        // Panel principal
        JPanel panelInfo = new JPanel(new GridLayout(7, 1, 5, 5));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblNombre = new JLabel("Nombre: " + producto.getNombre());
        lblPrecio = new JLabel("Precio: " + producto.getPrecio() + " €");
        lblCategoria = new JLabel("Categoría: " + producto.getCategoria());
        lblTalla = new JLabel("Talla: " + producto.getTalla());
        lblColor = new JLabel("Color: " + producto.getColor());
        lblStock = new JLabel("Stock: " + producto.getStock());

        panelInfo.add(lblNombre);
        panelInfo.add(lblPrecio);
        panelInfo.add(lblCategoria);
        panelInfo.add(lblTalla);
        panelInfo.add(lblColor);
        panelInfo.add(lblStock);

        // Imagen
        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        if (producto.getImagen() != null && !producto.getImagen().isEmpty()) {
            ImageIcon icono = ImageUtils.cargarMiniatura(producto.getImagen(), 250, 250);
            if (icono != null) {
                lblImagen.setIcon(icono);
            } else {
                lblImagen.setText("Imagen no disponible");
            }
        } else {
            lblImagen.setText("Sin imagen");
        }

        add(panelInfo, BorderLayout.NORTH);
        add(lblImagen, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);

        add(panelBoton, BorderLayout.SOUTH);
    }
}
