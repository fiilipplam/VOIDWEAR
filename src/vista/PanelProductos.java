package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.Producto;

public class PanelProductos extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6745173352026083317L;
	private JTable tablaProductos;
    private ProductoTableModel tableModel;
    private JTextField campoBusqueda;
    private JButton btnAñadir, btnEditar, btnEliminar;

    public PanelProductos(List<Producto> productos, String rolUsuario) {
        setLayout(new BorderLayout());

        // 🔍 Panel de filtros/búsqueda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoBusqueda = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        panelSuperior.add(new JLabel("Buscar:"));
        panelSuperior.add(campoBusqueda);
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        // 🧾 Tabla de productos
        tableModel = new ProductoTableModel(productos);
        tablaProductos = new JTable(tableModel);

        // Renderizado de imágenes como miniaturas
        tablaProductos.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -1282852245427736222L;

			@Override
            public void setValue(Object value) {
                if (value != null) {
                    ImageIcon icono = new ImageIcon(value.toString());
                    Image imgEscalada = icono.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    setIcon(new ImageIcon(imgEscalada));
                    setText("");
                } else {
                    setIcon(null);
                    setText("Sin imagen");
                }
            }
        });

        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        // ⚙️ Botones de acciones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAñadir = new JButton("Añadir");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        panelInferior.add(btnAñadir);
        panelInferior.add(btnEditar);
        panelInferior.add(btnEliminar);
        add(panelInferior, BorderLayout.SOUTH);

        // 🔐 Permisos según rol
        configurarPermisos(rolUsuario);
    }

    private void configurarPermisos(String rol) {
        switch (rol.toLowerCase()) {
            case "gestor" -> {
                setBotonesVisibles(true, true, true);
                tablaProductos.setEnabled(true);
            }
            case "empleado" -> {
                setBotonesVisibles(false, true, false);
                tablaProductos.setEnabled(true);
            }
            case "cliente", "invitado" -> {
                setBotonesVisibles(false, false, false);
                tablaProductos.setEnabled(false);
            }
        }
    }

    private void setBotonesVisibles(boolean añadir, boolean editar, boolean eliminar) {
        btnAñadir.setVisible(añadir);
        btnEditar.setVisible(editar);
        btnEliminar.setVisible(eliminar);
    }

    public JTable getTablaProductos() {
        return tablaProductos;
    }

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JTextField getCampoBusqueda() {
        return campoBusqueda;
    }
}
