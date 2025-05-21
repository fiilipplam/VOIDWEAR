package bd;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import modelo.Usuario;

public class UsuarioDAO {

	private static final String DB_PATH = "recursos/config/usuarios.odb";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_PATH);

	public static List<Usuario> obtenerTodos() {
		EntityManager em = emf.createEntityManager();
		List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
		em.close();
		return usuarios;
	}

	public static void insertar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(usuario);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

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
