package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import modelo.Usuario;
import modelo.Cliente;
import modelo.Rol;
import modelo.Sesion;
import bd.ClienteDAO;
import bd.UsuarioDAO;

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
        btnInvitado.addActionListener(e -> accederComoInvitado());
    }

    private void autenticarUsuario(ActionEvent e) {
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        List<Usuario> usuarios = UsuarioDAO.obtenerTodos();
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo) && u.getContrasena().equals(contrasena)) {

                // Guardar sesión del usuario
                Sesion.usuarioActual = u;

                // Si es cliente, buscar su cliente en SQL
                if (Sesion.esCliente()) {
                    Cliente cliente = ClienteDAO.obtenerPorUsuario(u.getCorreo());
                    if (cliente == null) {
                        JOptionPane.showMessageDialog(this,
                            "No se ha encontrado el cliente asociado a este usuario.\nNo se puede continuar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Sesion.clienteActual = cliente;
                }

                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
                dispose();
                new MainFrame(u).setVisible(true);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");
    }


    private void accederComoInvitado() {
        Usuario invitado = new Usuario("Invitado", "invitado@voidwear.com", "", Rol.CLIENTE);
        new MainFrame(invitado).setVisible(true);
        dispose();
    }
}
