/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package vista;

import modelo.Rol;
import modelo.Usuario;
import bd.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The Class UsuarioDialog.
 */
public class UsuarioDialog extends JDialog {

    /**
	 * The campo nombre.
	 */
    private JTextField campoNombre;
    
    /**
	 * The campo correo.
	 */
    private JTextField campoCorreo;
    
    /**
	 * The campo contraseña.
	 */
    private JPasswordField campoContraseña;
    
    /**
	 * The combo rol.
	 */
    private JComboBox<Rol> comboRol;

    /**
	 * The guardado.
	 */
    private boolean guardado = false;
    
    /**
	 * The original.
	 */
    private Usuario original;

    /**
	 * Instantiates a new usuario dialog.
	 *
	 * @param parent  the parent
	 * @param usuario the usuario
	 */
    public UsuarioDialog(Window parent, Usuario usuario) {
        super(parent, "Usuario", ModalityType.APPLICATION_MODAL);
        this.original = usuario;
        inicializar(usuario);
    }

    /**
	 * Inicializar.
	 *
	 * @param usuario the usuario
	 */
    private void inicializar(Usuario usuario) {
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        campoNombre = new JTextField();
        campoCorreo = new JTextField();
        campoContraseña = new JPasswordField();
        comboRol = new JComboBox<>(Rol.values());

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Correo:"));
        panelFormulario.add(campoCorreo);
        panelFormulario.add(new JLabel("Contraseña:"));
        panelFormulario.add(campoContraseña);
        panelFormulario.add(new JLabel("Rol:"));
        panelFormulario.add(comboRol);

        add(panelFormulario, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        if (usuario != null) {
            campoNombre.setText(usuario.getNombre());
            campoCorreo.setText(usuario.getCorreo());
            campoContraseña.setText(usuario.getContrasena());
            comboRol.setSelectedItem(usuario.getRol());

            campoCorreo.setEnabled(false); // Correo no editable
            if (usuario.getRol() == Rol.GESTOR) {
                comboRol.setEnabled(false); // No se permite cambiar rol de gestor
            }
        }

        pack();
        setLocationRelativeTo(getParent());
    }

    /**
	 * Guardar.
	 */
    private void guardar() {
        String nombre = campoNombre.getText().trim();
        String correo = campoCorreo.getText().trim();
        String contraseña = new String(campoContraseña.getPassword()).trim();
        Rol rol = (Rol) comboRol.getSelectedItem();

        if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        if (contraseña.length() < 4) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 4 caracteres.");
            return;
        }

        List<Usuario> todos = UsuarioDAO.obtenerTodos();
        boolean correoRepetido = todos.stream()
            .anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo) && (original == null || !u.equals(original)));

        if (correoRepetido) {
            JOptionPane.showMessageDialog(this, "Ya existe un usuario con ese correo.");
            return;
        }

        if (original == null) {
            Usuario nuevo = new Usuario(nombre, correo, contraseña, rol);
            UsuarioDAO.insertar(nuevo);
        } else {
            original.setNombre(nombre);
            original.setContrasena(contraseña);
            original.setRol(rol);
            UsuarioDAO.actualizar(original);
        }

        guardado = true;
        dispose();
    }

    /**
	 * Checks if is guardado.
	 *
	 * @return true, if is guardado
	 */
    public boolean isGuardado() {
        return guardado;
    }
}
