/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import bd.ConexionBD;
import bd.ProductoDAO;
import modelo.Producto;
import util.ImageRenderer;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

/**
 * The Class ProductoPanel.
 */
public class ProductoPanel extends JPanel {
    
    /**
	 * The tabla.
	 */
    private JTable tabla;
    
    /**
	 * The table model.
	 */
    private ProductoTableModel tableModel;
    
    /**
	 * The campo buscar.
	 */
    private JTextField campoBuscar;
    
    /**
	 * The combo ordenar.
	 */
    private JComboBox<String> comboOrdenar;
    
    /**
	 * The sorter.
	 */
    private TableRowSorter<ProductoTableModel> sorter;

    /**
	 * The boton anadir.
	 */
    private JButton botonAnadir;
    
    /**
	 * The boton editar.
	 */
    private JButton botonEditar;
    
    /**
	 * The boton eliminar.
	 */
    private JButton botonEliminar;
    
    /**
	 * The boton mostrar detalles.
	 */
    private JButton botonMostrarDetalles;

    /**
	 * Instantiates a new producto panel.
	 *
	 * @param productos  the productos
	 * @param rolUsuario the rol usuario
	 */
    public ProductoPanel(List<Producto> productos, String rolUsuario) {
        setLayout(new BorderLayout());

        // Panel de filtros
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoBuscar = new JTextField(20);
        comboOrdenar = new JComboBox<>(new String[]{"Nombre", "Precio", "Categoría"});
        JButton botonBuscar = new JButton("Buscar");

        panelSuperior.add(new JLabel("Buscar:"));
        panelSuperior.add(campoBuscar);
        panelSuperior.add(botonBuscar);
        panelSuperior.add(new JLabel("Ordenar por:"));
        panelSuperior.add(comboOrdenar);
        add(panelSuperior, BorderLayout.NORTH);

        // Tabla de productos
        tableModel = new ProductoTableModel(productos);
        tabla = new JTable(tableModel);
        tabla.setRowHeight(64);
        tabla.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer());

        sorter = new TableRowSorter<>(tableModel);
        tabla.setRowSorter(sorter);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Panel de botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botonAnadir = new JButton("Añadir");
        botonEditar = new JButton("Editar");
        botonEliminar = new JButton("Eliminar");
        botonMostrarDetalles = new JButton("Mostrar detalles");

        panelInferior.add(botonAnadir);
        panelInferior.add(botonEditar);
        panelInferior.add(botonEliminar);
        panelInferior.add(botonMostrarDetalles);
        add(panelInferior, BorderLayout.SOUTH);

        // Acción Buscar
        botonBuscar.addActionListener(e -> {
            String texto = campoBuscar.getText();
            if (texto.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            }
        });

        // Acción Mostrar Detalles
        botonMostrarDetalles.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int modeloFila = tabla.convertRowIndexToModel(fila);
                Producto producto = tableModel.getProductoAt(modeloFila);
                new DetalleProductoDialog(null, producto).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto.");
            }
        });

        // Acción Añadir con el nuevo diálogo
        botonAnadir.addActionListener(e -> {
            NuevoProductoDialog dialogo = new NuevoProductoDialog();
            dialogo.setVisible(true);

            if (dialogo.seGuardo()) {
                Producto nuevo = dialogo.getProductoCreado();
                ProductoDAO dao = new ProductoDAO(ConexionBD.getConexion());
                dao.insertarProducto(nuevo);
                tableModel.agregarProducto(nuevo);
            }
        });

     // Control de botones por rol
        if (rolUsuario.equalsIgnoreCase("gestor")) {
            // El gestor tiene acceso completo
            botonAnadir.setEnabled(true);
            botonEditar.setEnabled(true);
            botonEliminar.setEnabled(true);
        } else if (rolUsuario.equalsIgnoreCase("empleado")) {
            // El empleado solo puede editar
            botonAnadir.setEnabled(false);
            botonEditar.setEnabled(true);
            botonEliminar.setEnabled(false);
        } else {
            // Otros roles (cliente, invitado, etc.) no tienen permisos
            botonAnadir.setEnabled(false);
            botonEditar.setEnabled(false);
            botonEliminar.setEnabled(false);
        }

        
        botonEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int modeloFila = tabla.convertRowIndexToModel(fila);
                Producto producto = tableModel.getProductoAt(modeloFila);

                EditarProductoDialog dialogo = new EditarProductoDialog(null, producto);
                dialogo.setVisible(true);

                if (dialogo.seGuardaronCambios()) {
                    ProductoDAO dao = new ProductoDAO(ConexionBD.getConexion());
                    dao.actualizarProducto(producto);
                    tableModel.fireTableRowsUpdated(modeloFila, modeloFila);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para editar.");
            }
        });
        
        botonEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int modeloFila = tabla.convertRowIndexToModel(fila);
                Producto producto = tableModel.getProductoAt(modeloFila);

                int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar este producto?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    ProductoDAO dao = new ProductoDAO(ConexionBD.getConexion());
                    dao.eliminarProducto(producto.getIdProducto());
                    tableModel.eliminarProducto(modeloFila);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
            }
        });

    }
}
