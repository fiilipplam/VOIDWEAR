package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.*;

import bd.ConexionBD;
import modelo.Usuario;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    public LoginFrame() {
        setTitle("VOIDWEAR - Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();

        panel.add(new JLabel("Correo electrónico:"));
        panel.add(txtCorreo);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);

        JPanel botones = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton btnIniciarSesion = new JButton("Iniciar sesión");
        JButton btnInvitado = new JButton("Acceder como invitado");
        botones.add(btnIniciarSesion);
        botones.add(btnInvitado);
        panel.add(botones);

        add(panel, BorderLayout.CENTER);

        btnIniciarSesion.addActionListener(this::autenticarUsuario);
        btnInvitado.addActionListener(e -> abrirComoInvitado());
    }

    private void autenticarUsuario(ActionEvent e) {
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("voidwearPU");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena", Usuario.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            List<Usuario> resultado = query.getResultList();

            if (!resultado.isEmpty()) {
                Usuario usuario = resultado.get(0);
                registrarAcceso(usuario.getId(), "Acceso exitoso");
                JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario.getNombre());

                // Aquí deberías abrir el MainFrame según el rol
                // Ejemplo: new MainFrame(usuario).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la autenticación.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            em.close();
            emf.close();
        }
    }

    private void abrirComoInvitado() {
        registrarAcceso(null, "Acceso como invitado");
        JOptionPane.showMessageDialog(this, "Has accedido como invitado.");
        // Aquí abrirías la vista del catálogo como invitado
        // new CatalogoInvitadoFrame().setVisible(true);
        dispose();
    }

    private void registrarAcceso(Integer idUsuario, String evento) {
        try (Connection conn = ConexionBD.getConexion()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO log (id_usuario, evento) VALUES (?, ?)");
            if (idUsuario != null) {
                ps.setInt(1, idUsuario);
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
            }
            ps.setString(2, evento);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
