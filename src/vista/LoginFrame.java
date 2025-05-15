package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.persistence.*;

import bd.ConexionBD;
import modelo.Usuario;
import modelo.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginFrame extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    public LoginFrame() {
        setTitle("VOIDWEAR - Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();

        JButton btnLogin = new JButton("Iniciar sesión");
        JButton btnInvitado = new JButton("Acceder como invitado");

        panel.add(new JLabel("Correo electrónico:"));
        panel.add(txtCorreo);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);
        panel.add(btnLogin);
        panel.add(btnInvitado);

        add(panel, BorderLayout.CENTER);

        // Acción del botón login
        btnLogin.addActionListener((ActionEvent e) -> {
            String correo = txtCorreo.getText().trim();
            String contrasena = new String(txtContrasena.getPassword()).trim();

            Usuario usuario = validarCredenciales(correo, contrasena);

            if (usuario != null) {
                registrarAcceso(usuario.getIdUsuario(), "Inicio de sesión exitoso");
                abrirVentanaPrincipal(usuario);
            } else {
                JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción del botón invitado
        btnInvitado.addActionListener((ActionEvent e) -> {
            registrarAcceso(null, "Acceso como invitado");
            abrirCatalogoComoInvitado();
        });
    }

    /**
     * Verifica si el usuario existe en ObjectDB y si la contraseña es correcta
     */
    private Usuario validarCredenciales(String correo, String contrasena) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("usuarios.odb");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class);
            query.setParameter("correo", correo);
            List<Usuario> resultado = query.getResultList();

            if (!resultado.isEmpty()) {
                Usuario usuario = resultado.get(0);
                if (usuario.getContrasena().equals(contrasena)) {
                    return usuario;
                }
            }
        } finally {
            em.close();
            emf.close();
        }
        return null;
    }

    /**
     * Registra acceso en la tabla log de MySQL
     */
    private void registrarAcceso(Integer idUsuario, String evento) {
        try (Connection conn = ConexionBD.getConexion()) {
            String sql = "INSERT INTO log (id_usuario, evento, fecha_hora) VALUES (?, ?, NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql);

            if (idUsuario != null) {
                stmt.setInt(1, idUsuario);
            } else {
                stmt.setNull(1, java.sql.Types.INTEGER);
            }

            stmt.setString(2, evento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar en el log");
            e.printStackTrace();
        }
    }

    /**
     * Lanza la ventana principal con el usuario autenticado
     */
    private void abrirVentanaPrincipal(Usuario usuario) {
        this.dispose(); // Cierra ventana login
        new MainFrame(usuario).setVisible(true); // Lanza MainFrame
    }

    /**
     * Lanza el catálogo como invitado
     */
    private void abrirCatalogoComoInvitado() {
        this.dispose();
        new MainFrame(null).setVisible(true); // El MainFrame manejará si es null como invitado
    }
}
