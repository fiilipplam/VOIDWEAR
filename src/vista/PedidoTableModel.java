/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import bd.ClienteDAO;
import bd.PedidoDAO;
import modelo.Pedido;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PedidoTableModel.
 */
public class PedidoTableModel extends AbstractTableModel {

    /**
	 * The columnas.
	 */
    private final String[] columnas = {"ID", "Fecha", "Cliente", "Total (€)"};
    
    /**
	 * The pedidos.
	 */
    private List<Pedido> pedidos = new ArrayList<>();

    /**
	 * Instantiates a new pedido table model.
	 */
    // Constructor por defecto para evitar null
    public PedidoTableModel() {
        this.pedidos = new ArrayList<>();
    }

    /**
	 * Instantiates a new pedido table model.
	 *
	 * @param pedidos the pedidos
	 */
    public PedidoTableModel(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    /**
	 * Sets the pedidos.
	 *
	 * @param lista the new pedidos
	 */
    public void setPedidos(List<Pedido> lista) {
        this.pedidos = lista;
        fireTableDataChanged();
    }

    /**
	 * Gets the pedido at.
	 *
	 * @param fila the fila
	 * @return the pedido at
	 */
    public Pedido getPedidoAt(int fila) {
        return pedidos.get(fila);
    }

    /**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
    @Override
    public int getRowCount() {
        return pedidos.size();
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
        Pedido p = pedidos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getIdPedido();
            case 1 -> p.getFecha();
            case 2 -> ClienteDAO.obtenerNombrePorId(p.getIdCliente());
            case 3 -> PedidoDAO.calcularTotalPedido(p.getIdPedido());
            default -> null;
        };
    }
}
