package vista;

import modelo.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTableModel extends AbstractTableModel {

    private final String[] columnas = {"Nombre", "Correo", "Rol", "Contraseña"};
    private List<Usuario> usuarios = new ArrayList<>();

    public void setUsuarios(List<Usuario> lista) {
        this.usuarios = lista;
        fireTableDataChanged();
    }

    public Usuario getUsuarioAt(int fila) {
        return usuarios.get(fila);
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
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
