package bd;

import modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Producto";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getString("categoria"),
                    rs.getString("talla"),
                    rs.getString("color"),
                    rs.getInt("stock"),
                    rs.getString("imagen")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Insertar nuevo producto
    public boolean insertar(Producto p) {
        String sql = "INSERT INTO Producto (nombre, precio, categoria, talla, color, stock, imagen) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setString(3, p.getCategoria());
            stmt.setString(4, p.getTalla());
            stmt.setString(5, p.getColor());
            stmt.setInt(6, p.getStock());
            stmt.setString(7, p.getImagen());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar producto
    public boolean actualizar(Producto p) {
        String sql = "UPDATE Producto SET nombre = ?, precio = ?, categoria = ?, talla = ?, color = ?, stock = ?, imagen = ? " +
                     "WHERE id_producto = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setString(3, p.getCategoria());
            stmt.setString(4, p.getTalla());
            stmt.setString(5, p.getColor());
            stmt.setInt(6, p.getStock());
            stmt.setString(7, p.getImagen());
            stmt.setInt(8, p.getIdProducto());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar producto por ID
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM Producto WHERE id_producto = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar producto por ID
    public Producto buscarPorId(int idProducto) {
        String sql = "SELECT * FROM Producto WHERE id_producto = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("categoria"),
                        rs.getString("talla"),
                        rs.getString("color"),
                        rs.getInt("stock"),
                        rs.getString("imagen")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
