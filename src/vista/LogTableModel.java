/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import modelo.Log;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class LogTableModel.
 */
public class LogTableModel extends AbstractTableModel {

    /**
	 * The columnas.
	 */
    private final String[] columnas = {"ID", "Usuario", "Tipo de evento", "Mensaje", "Fecha y hora"};
    
    /**
	 * The logs.
	 */
    private List<Log> logs = new ArrayList<>();

    /**
	 * Sets the logs.
	 *
	 * @param lista the new logs
	 */
    public void setLogs(List<Log> lista) {
        this.logs = lista;
        fireTableDataChanged();
    }

    /**
	 * Gets the log at.
	 *
	 * @param fila the fila
	 * @return the log at
	 */
    public Log getLogAt(int fila) {
        return logs.get(fila);
    }

    /**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
    @Override
    public int getRowCount() {
        return logs.size();
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
