/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Class ConexionBD.
 */
public class ConexionBD {
	
	/**
	 * The Constant URL.
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/voidwear";
	
	/**
	 * The Constant USUARIO.
	 */
	private static final String USUARIO = "root";
	
	/**
	 * The Constant CONTRASENA.
	 */
	private static final String CONTRASENA = "";

	/**
	 * The conexion.
	 */
	private static Connection conexion;

	/**
	 * Gets the conexion.
	 *
	 * @return the conexion
	 */
	// Método para obtener la conexión
	public static Connection getConexion() {
		try {
			if (conexion == null || conexion.isClosed()) {
				conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
				System.out.println("Conexión establecida con la base de datos.");
			}
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos.");
			e.printStackTrace();
		}
		return conexion;
	}

	/**
	 * Cerrar conexion.
	 */
	// Método para cerrar la conexión
	public static void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
				System.out.println("Conexión cerrada.");
			} catch (SQLException e) {
				System.err.println("Error al cerrar la conexión.");
				e.printStackTrace();
			}
		}
	}
}
