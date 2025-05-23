/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

/**
 * The Class ClienteDAO.
 */
public class ClienteDAO {

	/**
	 * Obtener por id.
	 *
	 * @param idCliente the id cliente
	 * @return the cliente
	 */
	public static Cliente obtenerPorId(int idCliente) {
		String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Cliente(rs.getInt("id_cliente"), rs.getString("direccion"), rs.getString("telefono"),
						rs.getInt("id_usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Insertar.
	 *
	 * @param cliente the cliente
	 */
	public static void insertar(Cliente cliente) {
	    String sql = "INSERT INTO cliente (direccion, telefono, id_usuario) VALUES (?, ?, ?)";

	    try (Connection con = ConexionBD.getConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, cliente.getDireccion());
	        ps.setString(2, cliente.getTelefono());
	        ps.setInt(3, cliente.getIdUsuario());
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        System.err.println("Error al insertar cliente en SQL: " + e.getMessage());
	    }
	}


	/**
	 * Obtener nombre SQL.
	 *
	 * @param idUsuario the id usuario
	 * @return the string
	 */
	public static String obtenerNombreSQL(int idUsuario) {
		String sql = "SELECT nombre FROM usuario WHERE id_usuario = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Desconocido";
	}

	/**
	 * Obtener por usuario.
	 *
	 * @param correo the correo
	 * @return the cliente
	 */
	public static Cliente obtenerPorUsuario(String correo) {
	    Cliente cliente = null;
	    String sql = "SELECT c.* FROM cliente c JOIN usuario u ON c.id_usuario = u.id_usuario WHERE u.correo = ?";
	    try (Connection con = ConexionBD.getConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, correo);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            cliente = new Cliente(
	                rs.getInt("id_cliente"),
	                rs.getString("direccion"),
	                rs.getString("telefono"),
	                rs.getInt("id_usuario")
	            );
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener cliente por correo: " + e.getMessage());
	    }
	    return cliente;
	}


	/**
	 * Obtener id por usuario.
	 *
	 * @param correoUsuario the correo usuario
	 * @return the int
	 */
	public static int obtenerIdPorUsuario(String correoUsuario) {
		Cliente c = obtenerPorUsuario(correoUsuario);
		return c != null ? c.getIdCliente() : -1;
	}

	/**
	 * Obtener nombre por id.
	 *
	 * @param idCliente the id cliente
	 * @return the string
	 */
	public static String obtenerNombrePorId(int idCliente) {
		String sql = "SELECT u.nombre FROM cliente c JOIN usuario u ON c.id_usuario = u.id_usuario WHERE c.id_cliente = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Desconocido";
	}

	/**
	 * Obtener todos.
	 *
	 * @return the list
	 */
	public static List<Cliente> obtenerTodos() {
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM cliente";
		try (Connection con = ConexionBD.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(new Cliente(rs.getInt("id_cliente"), rs.getString("direccion"), rs.getString("telefono"),
						rs.getInt("id_usuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
