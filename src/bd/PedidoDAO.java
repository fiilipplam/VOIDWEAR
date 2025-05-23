/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Pedido;

/**
 * The Class PedidoDAO.
 */
public class PedidoDAO {

	/**
	 * Obtener todos.
	 *
	 * @return the list
	 */
	public static List<Pedido> obtenerTodos() {
		List<Pedido> lista = new ArrayList<>();
		String sql = "SELECT * FROM pedido";
		try (Connection con = ConexionBD.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(
						new Pedido(rs.getInt("id_pedido"), rs.getDate("fecha").toLocalDate(), rs.getInt("id_cliente")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Obtener por cliente.
	 *
	 * @param idCliente the id cliente
	 * @return the list
	 */
	public static List<Pedido> obtenerPorCliente(int idCliente) {
		List<Pedido> lista = new ArrayList<>();
		String sql = "SELECT id_pedido, fecha, id_cliente FROM pedido WHERE id_cliente = ?";

		try (Connection con = ConexionBD.getConexion();
		     PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idCliente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int idPedido = rs.getInt("id_pedido");
					LocalDate fecha = rs.getDate("fecha").toLocalDate();
					int clienteId = rs.getInt("id_cliente");

					Pedido pedido = new Pedido(idPedido, fecha, clienteId);
					lista.add(pedido);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al obtener pedidos por cliente: " + e.getMessage());
		}

		return lista;
	}


	/**
	 * Insertar.
	 *
	 * @param fecha     the fecha
	 * @param idCliente the id cliente
	 * @return the int
	 */
	public static int insertar(LocalDate fecha, int idCliente) {
		String sql = "INSERT INTO pedido (fecha, id_cliente) VALUES (?, ?)";
		try (Connection con = ConexionBD.getConexion();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setDate(1, Date.valueOf(fecha));
			ps.setInt(2, idCliente);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1); // ID generado
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Actualizar.
	 *
	 * @param idPedido  the id pedido
	 * @param idCliente the id cliente
	 */
	public static void actualizar(int idPedido, int idCliente) {
		String sql = "UPDATE pedido SET id_cliente = ? WHERE id_pedido = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCliente);
			ps.setInt(2, idPedido);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Eliminar.
	 *
	 * @param idPedido the id pedido
	 */
	public static void eliminar(int idPedido) {
		String sql = "DELETE FROM pedido WHERE id_pedido = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPedido);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calcular total pedido.
	 *
	 * @param idPedido the id pedido
	 * @return the double
	 */
	public static double calcularTotalPedido(int idPedido) {
		String sql = "SELECT calcular_total_pedido(?) AS total";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPedido);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
}
