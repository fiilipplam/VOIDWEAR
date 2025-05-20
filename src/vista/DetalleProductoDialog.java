package vista;

import modelo.Producto;
import util.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class DetalleProductoDialog extends JDialog {
    private Producto producto;
    private JLabel etiquetaImagen;

    public DetalleProductoDialog(Window owner, Producto producto) {
        super(owner, "Detalles del Producto", ModalityType.APPLICATION_MODAL);
        this.producto = producto;

        getContentPane().setLayout(new BorderLayout());
        setSize(800, 511);
        setLocationRelativeTo(owner);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);

        // Etiquetas de campo
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(409, 116, 100, 25);
        panelCentral.add(lblNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(409, 156, 100, 25);
        panelCentral.add(lblPrecio);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(409, 196, 100, 25);
        panelCentral.add(lblCategoria);

        JLabel lblTalla = new JLabel("Talla:");
        lblTalla.setBounds(409, 236, 100, 25);
        panelCentral.add(lblTalla);

        JLabel lblColor = new JLabel("Color:");
        lblColor.setBounds(409, 276, 100, 25);
        panelCentral.add(lblColor);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(409, 316, 100, 25);
        panelCentral.add(lblStock);

        // Valores como JLabels
        JLabel valNombre = new JLabel(producto.getNombre());
        valNombre.setBounds(509, 116, 200, 25);
        panelCentral.add(valNombre);

        JLabel valPrecio = new JLabel(String.valueOf(producto.getPrecio()));
        valPrecio.setBounds(509, 156, 200, 25);
        panelCentral.add(valPrecio);

        JLabel valCategoria = new JLabel(producto.getCategoria());
        valCategoria.setBounds(509, 196, 200, 25);
        panelCentral.add(valCategoria);

        JLabel valTalla = new JLabel(producto.getTalla());
        valTalla.setBounds(509, 236, 200, 25);
        panelCentral.add(valTalla);

        JLabel valColor = new JLabel(producto.getColor());
        valColor.setBounds(509, 276, 200, 25);
        panelCentral.add(valColor);

        JLabel valStock = new JLabel(String.valueOf(producto.getStock()));
        valStock.setBounds(509, 316, 200, 25);
        panelCentral.add(valStock);

        // Imagen del producto
        etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setBounds(50, 73, 300, 300);
        cargarImagen(producto.getImagen());
        panelCentral.add(etiquetaImagen);

        getContentPane().add(panelCentral, BorderLayout.CENTER);

        // Botón cerrar
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonCerrar = new JButton("Cerrar");
        panelBotones.add(botonCerrar);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        botonCerrar.addActionListener(e -> dispose());
    }

    private void cargarImagen(String ruta) {
        ImageIcon icono = ImageUtils.cargarMiniatura(ruta, 300, 300);
        etiquetaImagen.setIcon(icono);
    }
}
