/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import modelo.Producto;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * The Class ProductoTableModel.
 */
public class ProductoTableModel extends AbstractTableModel {
    
    /**
	 * The columnas.
	 */
    private final String[] columnas = {"Nombre", "Precio", "Categoría", "Talla", "Color", "Stock", "Imagen"};
    
    /**
	 * The productos.
	 */
    private List<Producto> productos;

    /**
	 * Instantiates a new producto table model.
	 *
	 * @param productos the productos
	 */
    public ProductoTableModel(List<Producto> productos) {
        this.productos = productos;
    }

    /**
	 * Gets the producto at.
	 *
	 * @param rowIndex the row index
	 * @return the producto at
	 */
    public Producto getProductoAt(int rowIndex) {
        return productos.get(rowIndex);
    }

    /**
	 * Sets the productos.
	 *
	 * @param productos the new productos
	 */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    /**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
    @Override
    public int getRowCount() {
        return productos.size();
    }

    /**
	 * Gets the column count.
	 *
	 * @return the column count
	 */
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    /**
	 * Gets the column name.
	 *
	 * @param column the column
	 * @return the column name
	 */
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    /**
	 * Gets the value at.
	 *
	 * @param rowIndex    the row index
	 * @param columnIndex the column index
	 * @return the value at
	 */
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

    /**
	 * Gets the column class.
	 *
	 * @param columnIndex the column index
	 * @return the column class
	 */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1) return Double.class;
        if (columnIndex == 5) return Integer.class;
        if (columnIndex == 6) return ImageIcon.class;
        return String.class;
    }

    /**
	 * Actualizar producto.
	 *
	 * @param fila                the fila
	 * @param productoActualizado the producto actualizado
	 */
    public void actualizarProducto(int fila, Producto productoActualizado) {
        productos.set(fila, productoActualizado);
        fireTableRowsUpdated(fila, fila);
    }
    
    /**
	 * Agregar producto.
	 *
	 * @param producto the producto
	 */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        fireTableRowsInserted(productos.size() - 1, productos.size() - 1);
    }
    
    /**
	 * Eliminar producto.
	 *
	 * @param fila the fila
	 */
    public void eliminarProducto(int fila) {
        productos.remove(fila);
        fireTableRowsDeleted(fila, fila);
    }

}
