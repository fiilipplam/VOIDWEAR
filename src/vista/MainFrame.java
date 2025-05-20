package vista;

import javax.swing.*;

import bd.ProductoDAO;
import vista.InicioPanel;
import vista.PedidosPanel;



import java.awt.*;
import modelo.Usuario;
import modelo.Producto;
import modelo.Rol;
import java.util.List;

public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4577069936890186817L;

	private Usuario usuario;

    private JPanel panelPrincipal;
    private JPanel panelLateral;
    private JPanel panelCentral;
    private JLabel lblUsuario;
    private JLabel lblRol;

    public MainFrame(Usuario usuario) {
        this.usuario = usuario;
        inicializarComponentes();
        configurarVentana();
        aplicarPermisos();
    }

    private void inicializarComponentes() {
        setTitle("VOIDWEAR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new BorderLayout());

        // Parte superior
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSuperior.setBackground(Color.DARK_GRAY);
        panelSuperior.setForeground(Color.WHITE);

        JLabel lblLogo = new JLabel("VOIDWEAR");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 18));

        lblUsuario = new JLabel("Usuario: " + usuario.getNombre());
        lblUsuario.setForeground(Color.WHITE);

        lblRol = new JLabel("Rol: " + usuario.getRol().toString().toUpperCase());
        lblRol.setForeground(Color.WHITE);

        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(lblLogo);
        panelSuperior.add(Box.createHorizontalStrut(20));
        panelSuperior.add(lblUsuario);
        panelSuperior.add(Box.createHorizontalStrut(20));
        panelSuperior.add(lblRol);
        panelSuperior.add(Box.createHorizontalGlue());
        panelSuperior.add(btnCerrarSesion);

        // Panel lateral
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setPreferredSize(new Dimension(180, getHeight()));
        panelLateral.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Botones del panel lateral
        JButton btnInicio = new JButton("Inicio");
        JButton btnProductos = new JButton("Productos");
        JButton btnPedidos = new JButton("Pedidos");
        JButton btnUsuarios = new JButton("Usuarios");
        JButton btnLogs = new JButton("Logs");
        JButton btnExportarXML = new JButton("Exportar XML");

        // Acciones de botones
        btnInicio.addActionListener(e -> mostrarPanel(new InicioPanel (usuario))); // PanelInicio
        btnProductos.addActionListener(e -> {
            ProductoDAO dao = new ProductoDAO(bd.ConexionBD.getConexion()); // ✅ conexión válida
            List<Producto> productos = dao.listarProductos();
            mostrarPanel(new ProductoPanel(productos, usuario.getRol().toString()));
        });

        btnPedidos.addActionListener(e -> mostrarPanel(new PedidosPanel(usuario))); // PanelPedidos
        btnUsuarios.addActionListener(e -> mostrarPanel(new UsuariosPanel(usuario))); // PanelUsuarios
        btnLogs.addActionListener(e -> mostrarPanel(new LogsPanel(usuario))); // PanelLogs
        btnExportarXML.addActionListener(e -> mostrarPanel(new JPanel())); // PanelExportar

     // Añadir botones según el rol
        panelLateral.add(btnInicio);
        panelLateral.add(btnProductos);

        // Todos los roles pueden ver pedidos
        panelLateral.add(btnPedidos);

        // Solo gestor puede ver estos botones
        if (usuario.getRol() == Rol.GESTOR) {
            panelLateral.add(btnUsuarios);
            panelLateral.add(btnLogs);
            panelLateral.add(btnExportarXML);
        }


        // Panel central
        panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Contenido"));

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelLateral, BorderLayout.WEST);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private void configurarVentana() {
        setVisible(true);
    }

    public void mostrarPanel(JPanel nuevoPanel) {
        panelCentral.removeAll();
        panelCentral.add(nuevoPanel, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void aplicarPermisos() {
        // (Opcional) Puedes aplicar aquí lógica adicional por rol
    }
}
