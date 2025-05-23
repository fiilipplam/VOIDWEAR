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
import java.util.HashMap;
import java.util.Map;

/**
 * The Class LineaPedidoDAO.
 */
public class LineaPedidoDAO {

	/**
	 * Insertar.
	 *
	 * @param idPedido       the id pedido
	 * @param idProducto     the id producto
	 * @param cantidad       the cantidad
	 * @param precioUnitario the precio unitario
	 */
	public static void insertar(int idPedido, int idProducto, int cantidad, double precioUnitario) {
		String sql = "INSERT INTO lineapedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPedido);
			ps.setInt(2, idProducto);
			ps.setInt(3, cantidad);
			ps.setDouble(4, precioUnitario);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Eliminar por pedido.
	 *
	 * @param idPedido the id pedido
	 */
	public static void eliminarPorPedido(int idPedido) {
		String sql = "DELETE FROM lineapedido WHERE id_pedido = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPedido);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtener cantidad por producto.
	 *
	 * @param idPedido the id pedido
	 * @return the map
	 */
	public static Map<Integer, Integer> obtenerCantidadPorProducto(int idPedido) {
		Map<Integer, Integer> resultado = new HashMap<>();
		String sql = "SELECT id_producto, cantidad FROM lineapedido WHERE id_pedido = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPedido);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultado.put(rs.getInt("id_producto"), rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
