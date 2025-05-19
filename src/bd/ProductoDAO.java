package bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Producto;

public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.setConexion(conexion);
    }

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

    public void eliminarProducto(int idProducto) {
        String sql = "DELETE FROM producto WHERE id_producto=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
