package vista;

import modelo.Producto;
import bd.ProductoDAO;
import bd.ConexionBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class ProductoPanel extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1763535510459054923L;
	private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnCargar;

    public ProductoPanel() {
        setTitle("Catálogo de Productos - VOIDWEAR");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Modelo de tabla
        modeloTabla = new DefaultTableModel(new String[] {
            "ID", "Nombre", "Precio", "Categoría", "Talla", "Color", "Stock", "Imagen"
        }, 0);

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);

        // Botón cargar
        btnCargar = new JButton("Cargar productos");
        btnCargar.addActionListener(e -> cargarProductos());
        panel.add(btnCargar, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarProductos() {
        Connection conn = ConexionBD.getConexion();
        ProductoDAO dao = new ProductoDAO(conn);
        List<Producto> lista = dao.obtenerTodos();

        modeloTabla.setRowCount(0); // limpiar

        for (Producto p : lista) {
            modeloTabla.addRow(new Object[] {
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecio(),
                p.getCategoria(),
                p.getTalla(),
                p.getColor(),
                p.getStock(),
                p.getImagen()
            });
        }

        ConexionBD.cerrarConexion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductoPanel().setVisible(true);
        });
    }
}
