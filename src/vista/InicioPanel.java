/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * The Class InicioPanel.
 */
public class InicioPanel extends JPanel {

    /**
	 * The usuario.
	 */
    private Usuario usuario;

    /**
	 * Instantiates a new inicio panel.
	 *
	 * @param usuario the usuario
	 */
    public InicioPanel(Usuario usuario) {
        this.usuario = usuario;
        inicializarComponentes();
    }

    /**
	 * Inicializar componentes.
	 */
    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel superior con logo y bienvenida
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setBackground(Color.WHITE);

        // Logo
        JLabel labelLogo = new JLabel();
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icono = new ImageIcon("recursos/imagenes/logos/logo_detallado.png");
        Image imagenEscalada = icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        labelLogo.setIcon(new ImageIcon(imagenEscalada));
        panelSuperior.add(labelLogo, BorderLayout.NORTH);

        // Bienvenida
        JLabel labelBienvenida = new JLabel("Bienvenido, " + usuario.getNombre(), SwingConstants.CENTER);
        labelBienvenida.setFont(new Font("SansSerif", Font.BOLD, 22));
        labelBienvenida.setForeground(Color.DARK_GRAY);
        panelSuperior.add(labelBienvenida, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel de información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panelInfo.setBackground(Color.WHITE);

        agregarSeccion(panelInfo, "¿Quiénes somos?", "VoidWear es una marca de ropa urbana comprometida con la autenticidad y la calidad. Diseñamos prendas que expresan actitud y estilo.");
        agregarSeccion(panelInfo, "Misión y visión", "Nuestra misión es ofrecer ropa que represente la identidad de quienes se atreven a ser diferentes. Buscamos liderar el cambio en la moda urbana.");
        agregarSeccion(panelInfo, "Contacto", "Correo: contacto@voidwear.com\nTeléfono: 123 456 789\nInstagram: @voidwear");
        agregarSeccion(panelInfo, "Horario de atención", "Lunes a Viernes: 10:00 - 18:00\nSábados: 11:00 - 14:00");

        JScrollPane scrollPane = new JScrollPane(panelInfo);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
	 * Agregar seccion.
	 *
	 * @param panel     the panel
	 * @param titulo    the titulo
	 * @param contenido the contenido
	 */
    private void agregarSeccion(JPanel panel, String titulo, String contenido) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));

        JTextArea txtContenido = new JTextArea(contenido);
        txtContenido.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true);
        txtContenido.setEditable(false);
        txtContenido.setOpaque(false);
        txtContenido.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblTitulo);
        panel.add(txtContenido);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
    }
}
