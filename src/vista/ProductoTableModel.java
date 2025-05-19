package vista;

import modelo.Producto;
import util.ImageUtils;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class ProductoTableModel extends AbstractTableModel {
    private final String[] columnas = {"Nombre", "Precio", "Categoría", "Talla", "Color", "Stock", "Imagen"};
    private List<Producto> productos;

    public ProductoTableModel() {
        this.productos = new ArrayList<>();
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    public Producto getProductoEnFila(int fila) {
        return productos.get(fila);
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
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Producto p = productos.get(fila);
        return switch (columna) {
            case 0 -> p.getNombre();
            case 1 -> p.getPrecio();
            case 2 -> p.getCategoria();
            case 3 -> p.getTalla();
            case 4 -> p.getColor();
            case 5 -> p.getStock();
            case 6 -> {
                String ruta = p.getImagen();
                if (ruta != null && !ruta.isEmpty()) {
                    yield ImageUtils.cargarMiniatura(ruta, 50, 50);
                } else {
                    yield null;
                }
            }
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 6) {
            return ImageIcon.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
