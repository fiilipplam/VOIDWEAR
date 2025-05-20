package vista;

import bd.LogDAO;
import modelo.Log;
import modelo.Rol;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class LogsPanel extends JPanel {

    private JTable tabla;
    private LogTableModel modeloTabla;
    private TableRowSorter<LogTableModel> sorter;
    private JTextField campoBusqueda;
    private Usuario usuario;

    public LogsPanel(Usuario usuario) {
        this.usuario = usuario;
        if (usuario.getRol() != Rol.GESTOR) {
            setLayout(new BorderLayout());
            add(new JLabel("Acceso denegado. Solo el gestor puede ver los logs.", SwingConstants.CENTER), BorderLayout.CENTER);
        } else {
            inicializarComponentes();
            cargarLogs();
        }
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Filtro de búsqueda general
        JPanel panelFiltro = new JPanel();
        campoBusqueda = new JTextField(30);
        panelFiltro.add(new JLabel("Buscar:"));
        panelFiltro.add(campoBusqueda);

        campoBusqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { aplicarFiltro(); }
        });

        add(panelFiltro, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new LogTableModel();
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void aplicarFiltro() {
        String texto = campoBusqueda.getText().toLowerCase();
        sorter.setRowFilter(new RowFilter<>() {
            public boolean include(Entry<? extends LogTableModel, ? extends Integer> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    String valor = String.valueOf(entry.getValue(i)).toLowerCase();
                    if (valor.contains(texto)) return true;
                }
                return false;
            }
        });
    }

    private void cargarLogs() {
        List<Log> logs = LogDAO.obtenerTodos();
        modeloTabla.setLogs(logs);
    }
}
