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

import modelo.Producto;

/**
 * The Class ProductoDAO.
 */
public class ProductoDAO {

	/**
	 * The conexion.
	 */
	private Connection conexion;

	/**
	 * Instantiates a new producto DAO.
	 *
	 * @param conexion the conexion
	 */
	public ProductoDAO(Connection conexion) {
		this.setConexion(conexion);
	}

	/**
	 * Insertar producto.
	 *
	 * @param producto the producto
	 */
	public void insertarProducto(Producto producto) {
		String sql = "INSERT INTO producto (nombre, precio, categoria, talla, color, stock, imagen) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
			ps.setString(3, producto.getCategoria());
			ps.setString(4, producto.getTalla());
			ps.setString(5, producto.getColor());
			ps.setInt(6, producto.getStock());
			ps.setString(7, producto.getImagen());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtener por id.
	 *
	 * @param idProducto the id producto
	 * @return the producto
	 */
	public static Producto obtenerPorId(int idProducto) {
		String sql = "SELECT * FROM producto WHERE id_producto = ?";
		try (Connection con = ConexionBD.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idProducto);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Producto(rs.getInt("id_producto"), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("categoria"), rs.getString("talla"), rs.getString("color"), rs.getInt("stock"),
						rs.getString("imagen"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Actualizar producto.
	 *
	 * @param producto the producto
	 */
	public void actualizarProducto(Producto producto) {
		String sql = "UPDATE producto SET nombre=?, precio=?, categoria=?, talla=?, color=?, stock=?, imagen=? WHERE id_producto=?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
			ps.setString(3, producto.getCategoria());
			ps.setString(4, producto.getTalla());
			ps.setString(5, producto.getColor());
			ps.setInt(6, producto.getStock());
			ps.setString(7, producto.getImagen());
			ps.setInt(8, producto.getIdProducto());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Eliminar producto.
	 *
	 * @param idProducto the id producto
	 */
	public void eliminarProducto(int idProducto) {
		String sql = "DELETE FROM producto WHERE id_producto=?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, idProducto);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar productos.
	 *
	 * @return the list
	 */
	public List<Producto> listarProductos() {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT * FROM producto";

		try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Producto p = new Producto();
				p.setIdProducto(rs.getInt("id_producto"));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getDouble("precio"));
				p.setCategoria(rs.getString("categoria"));
				p.setTalla(rs.getString("talla"));
				p.setColor(rs.getString("color"));
				p.setStock(rs.getInt("stock"));
				p.setImagen(rs.getString("imagen"));
				productos.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productos;
	}

	/**
	 * Obtener todos.
	 *
	 * @return the list
	 */
	public static List<Producto> obtenerTodos() {
		List<Producto> lista = new ArrayList<>();
		String sql = "SELECT * FROM producto";
		try (Connection con = ConexionBD.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Producto p = new Producto(rs.getInt("id_producto"), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("categoria"), rs.getString("talla"), rs.getString("color"), rs.getInt("stock"),
						rs.getString("imagen"));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Gets the conexion.
	 *
	 * @return the conexion
	 */
	public Connection getConexion() {
		return conexion;
	}

	/**
	 * Sets the conexion.
	 *
	 * @param conexion the new conexion
	 */
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}
