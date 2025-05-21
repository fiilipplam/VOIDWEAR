package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Log;

public class LogDAO {

	public static List<Log> obtenerTodos() {
		List<Log> lista = new ArrayList<>();
		String sql = "SELECT * FROM log ORDER BY fecha_hora DESC";

		try (Connection con = ConexionBD.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Log log = new Log(rs.getInt("id_log"), rs.getString("usuario"), rs.getString("tipo_evento"),
						rs.getString("mensaje"), rs.getTimestamp("fecha_hora").toLocalDateTime());
				lista.add(log);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public static void insertar(Log log) {
		String sql = "INSERT INTO log (fecha_hora, usuario, tipo_evento, mensaje) VALUES (?, ?, ?, ?)";

		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, log.getFechaHora().toString());
			ps.setString(2, log.getUsuario());
			ps.setString(3, log.getTipoEvento());
			ps.setString(4, log.getMensaje());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
