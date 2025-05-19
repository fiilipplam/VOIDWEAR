package vista;

import modelo.Producto;
import bd.ProductoDAO;
import util.ImageRenderer;
import util.ImageUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProductoPanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField campoBuscar;

    public ProductoPanel() {
        setLayout(new BorderLayout());

        // Filtro de búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("Buscar:"));
        campoBuscar = new JTextField(20);
        panelBusqueda.add(campoBuscar);
        JButton botonBuscar = new JButton("Buscar");
        panelBusqueda.add(botonBuscar);
        add(panelBusqueda, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Nombre", "Precio", "Categoría", "Talla", "Color", "Stock", "Imagen"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no editables
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) return ImageIcon.class;
                return String.class;
            }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(50);
        tabla.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer(60, 60));

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Cargar productos
        cargarProductos();

        // Doble clic en fila
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabla.getSelectedRow() != -1) {
                    int fila = tabla.getSelectedRow();
                    Producto producto = getProductoEnFila(fila);
                    DetalleProductoDialog dialog = new DetalleProductoDialog(SwingUtilities.getWindowAncestor(ProductoPanel.this), producto);
                    dialog.setVisible(true);
                }
            }
        });
    }

    private void cargarProductos() {
        ProductoDAO dao = new ProductoDAO(null);
        List<Producto> productos = dao.obtenerTodos();
        for (Producto p : productos) {
            modelo.addRow(new Object[]{
                    p.getNombre(),
                    String.valueOf(p.getPrecio()),
                    p.getCategoria(),
                    p.getTalla(),
                    p.getColor(),
                    String.valueOf(p.getStock()),
                    ImageUtils.cargarMiniatura(p.getImagen(), 40, 40)
            });
        }
    }

    private Producto getProductoEnFila(int fila) {
        String nombre = (String) modelo.getValueAt(fila, 0);
        ProductoDAO dao = new ProductoDAO(null);
        return dao.buscarPorNombre(nombre);
    }
}
