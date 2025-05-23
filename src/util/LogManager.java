/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import bd.ConexionBD;
import modelo.Log;
import modelo.Usuario;

/**
 * The Class LogManager.
 */
public class LogManager {

	/**
	 * The Constant ARCHIVO_LOG.
	 */
	private static final String ARCHIVO_LOG = "log/eventos.txt";

	/**
	 * Registrar.
	 *
	 * @param usuario    the usuario
	 * @param tipoEvento the tipo evento
	 * @param mensaje    the mensaje
	 */
	// permite registrar con strings
	public static void registrar(String usuario, String tipoEvento, String mensaje) {
		Log log = new Log(LocalDateTime.now(), usuario, tipoEvento, mensaje);
		guardarEnArchivo(log);
		guardarEnBD(log);
	}

	/**
	 * Registrar.
	 *
	 * @param usuario    the usuario
	 * @param tipoEvento the tipo evento
	 * @param mensaje    the mensaje
	 */
	// permite registrar con objeto Usuario
	public static void registrar(Usuario usuario, String tipoEvento, String mensaje) {
		if (usuario != null) {
			registrar(usuario.getCorreo(), tipoEvento, mensaje);
		}
	}

	/**
	 * Guardar en archivo.
	 *
	 * @param log the log
	 */
	private static void guardarEnArchivo(Log log) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_LOG, true))) {
			bw.write(log.toString());
			bw.newLine();
		} catch (IOException e) {
			System.err.println("Error al guardar en el archivo de log: " + e.getMessage());
		}
	}

	/**
	 * Guardar en BD.
	 *
	 * @param log the log
	 */
	private static void guardarEnBD(Log log) {
		String sql = "INSERT INTO log (fecha_hora, usuario, tipo_evento, mensaje) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexionBD.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, log.getFechaHora().toString());
			stmt.setString(2, log.getUsuario());
			stmt.setString(3, log.getTipoEvento());
			stmt.setString(4, log.getMensaje());

			stmt.executeUpdate();

		} catch (Exception e) {
			System.err.println("Error al guardar log en la base de datos: " + e.getMessage());
		}
	}
}
