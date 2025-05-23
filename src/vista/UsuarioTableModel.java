/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import modelo.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class UsuarioTableModel.
 */
public class UsuarioTableModel extends AbstractTableModel {

    /**
	 * The columnas.
	 */
    private final String[] columnas = {"Nombre", "Correo", "Rol", "Contraseña"};
    
    /**
	 * The usuarios.
	 */
    private List<Usuario> usuarios = new ArrayList<>();

    /**
	 * Sets the usuarios.
	 *
	 * @param lista the new usuarios
	 */
    public void setUsuarios(List<Usuario> lista) {
        this.usuarios = lista;
        fireTableDataChanged();
    }

    /**
	 * Gets the usuario at.
	 *
	 * @param fila the fila
	 * @return the usuario at
	 */
    public Usuario getUsuarioAt(int fila) {
        return usuarios.get(fila);
    }

    /**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
    @Override
    public int getRowCount() {
        return usuarios.size();
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
        Usuario u = usuarios.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> u.getNombre();
            case 1 -> u.getCorreo();
            case 2 -> u.getRol().toString();
            case 3 -> "●●●●●"; // Contraseña oculta
            default -> null;
        };
    }
}
