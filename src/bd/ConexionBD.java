package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/voidwear";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = ""; // ⚠️ Cambia si tienes contraseña

    private static Connection conexion;

    // Método para obtener la conexión
    public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println("✅ Conexión establecida con la base de datos.");
            } catch (SQLException e) {
                System.err.println("❌ Error al conectar con la base de datos.");
                e.printStackTrace();
            }
        }
        return conexion;
    }

    // Método opcional para cerrar la conexión
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("🔒 Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
