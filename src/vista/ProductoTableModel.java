package vista;

import modelo.Producto;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {
    private final String[] columnas = {"Nombre", "Precio", "Categoría", "Talla", "Color", "Stock", "Imagen"};
    private List<Producto> productos;

    public ProductoTableModel(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProductoAt(int rowIndex) {
        return productos.get(rowIndex);
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = productos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNombre();
            case 1 -> p.getPrecio();
            case 2 -> p.getCategoria();
            case 3 -> p.getTalla();
            case 4 -> p.getColor();
            case 5 -> p.getStock();
            case 6 -> p.getImagen();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1) return Double.class;
        if (columnIndex == 5) return Integer.class;
        if (columnIndex == 6) return ImageIcon.class;
        return String.class;
    }

    public void actualizarProducto(int fila, Producto productoActualizado) {
        productos.set(fila, productoActualizado);
        fireTableRowsUpdated(fila, fila);
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        fireTableRowsInserted(productos.size() - 1, productos.size() - 1);
    }
}
