package bd;

import modelo.Log;
import bd.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    public static List<Log> obtenerTodos() {
        List<Log> lista = new ArrayList<>();
        String sql = "SELECT * FROM log ORDER BY fecha_hora DESC";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Log log = new Log(
                        rs.getInt("id_log"),
                        rs.getString("usuario"),
                        rs.getString("tipo_evento"),
                        rs.getString("mensaje"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime()
                );
                lista.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
