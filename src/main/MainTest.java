package main;

import javax.swing.SwingUtilities;

import modelo.Rol;
import modelo.Usuario;
import vista.MainFrame;

public class MainTest {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Simular un usuario cualquiera
			Usuario usuarioSimulado = new Usuario("Prueba", "correo@ejemplo.com", "1234", Rol.GESTOR);

			MainFrame ventanaPrincipal = new MainFrame(usuarioSimulado);
			ventanaPrincipal.setVisible(true);
		});
	}
}
