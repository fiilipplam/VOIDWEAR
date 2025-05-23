package vista;

import bd.ClienteDAO;
import bd.PedidoDAO;
import modelo.Pedido;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PedidoTableModel extends AbstractTableModel {

    private final String[] columnas = {"ID", "Fecha", "Cliente", "Total (€)"};
    private List<Pedido> pedidos = new ArrayList<>();

    // Constructor por defecto para evitar null
    public PedidoTableModel() {
        this.pedidos = new ArrayList<>();
    }

    public PedidoTableModel(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setPedidos(List<Pedido> lista) {
        this.pedidos = lista;
        fireTableDataChanged();
    }

    public Pedido getPedidoAt(int fila) {
        return pedidos.get(fila);
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
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
