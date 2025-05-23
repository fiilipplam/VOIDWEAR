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
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import modelo.Cliente;
import modelo.Rol;
import modelo.Usuario;

/**
 * The Class UsuarioDAO.
 */
public class UsuarioDAO {

	/**
	 * The Constant DB_PATH.
	 */
	private static final String DB_PATH = "recursos/config/usuarios.odb";
	
	/**
	 * The Constant emf.
	 */
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_PATH);

	/**
	 * Obtener todos.
	 *
	 * @return the list
	 */
	public static List<Usuario> obtenerTodos() {
		EntityManager em = emf.createEntityManager();
		List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
		em.close();
		return usuarios;
	}

	/**
	 * Insertar.
	 *
	 * @param usuario the usuario
	 */
	public static void insertar(Usuario usuario) {
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();

	    try {
	        tx.begin();
	        em.persist(usuario);
	        tx.commit();
	    } catch (Exception e) {
	        if (tx.isActive()) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }

	    // Insertar también en SQL
	    int idUsuarioSQL = insertarEnSQL(usuario);

	    // Si es cliente, también insertar en la tabla cliente
	    if (usuario.getRol() == Rol.CLIENTE && idUsuarioSQL != -1) {
	        Cliente cliente = new Cliente();
	        cliente.setIdUsuario(idUsuarioSQL);
	        cliente.setDireccion("Sin definir");
	        cliente.setTelefono("000000000");

	        ClienteDAO.insertar(cliente);
	    }
	}

	
	/**
	 * Insertar en SQL.
	 *
	 * @param usuario the usuario
	 * @return the int
	 */
	public static int insertarEnSQL(Usuario usuario) {
	    int idUsuarioSQL = -1;
	    String sql = "INSERT INTO usuario (nombre, correo, rol) VALUES (?, ?, ?)";

	    try (Connection con = ConexionBD.getConexion();
	         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, usuario.getNombre());
	        ps.setString(2, usuario.getCorreo());
	        ps.setString(3, usuario.getRol().name().toLowerCase());
	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            idUsuarioSQL = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al insertar usuario en SQL: " + e.getMessage());
	    }

	    return idUsuarioSQL;
	}


	/**
	 * Actualizar.
	 *
	 * @param usuario the usuario
	 */
	public static void actualizar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(usuario);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	/**
	 * Eliminar.
	 *
	 * @param usuario the usuario
	 */
	public static void eliminar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Usuario u = em.find(Usuario.class, usuario.getCorreo());
			if (u != null) {
				em.remove(u);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
