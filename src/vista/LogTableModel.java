package vista;

import modelo.Log;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class LogTableModel extends AbstractTableModel {

    private final String[] columnas = {"ID", "Usuario", "Tipo de evento", "Mensaje", "Fecha y hora"};
    private List<Log> logs = new ArrayList<>();

    public void setLogs(List<Log> lista) {
        this.logs = lista;
        fireTableDataChanged();
    }

    public Log getLogAt(int fila) {
        return logs.get(fila);
    }

    @Override
    public int getRowCount() {
        return logs.size();
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
        Log log = logs.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> log.getIdLog();
            case 1 -> log.getUsuario();
            case 2 -> log.getTipoEvento();
            case 3 -> log.getMensaje();
            case 4 -> log.getFechaHora();
            default -> null;
        };
    }
}
