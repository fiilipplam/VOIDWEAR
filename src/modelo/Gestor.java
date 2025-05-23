/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

/**
 * The Class Gestor.
 */
public class Gestor {
	
	/**
	 * The id gestor.
	 */
	private int idGestor;
	
	/**
	 * The id usuario.
	 */
	private int idUsuario;

	/**
	 * Instantiates a new gestor.
	 */
	public Gestor() {
	}

	/**
	 * Instantiates a new gestor.
	 *
	 * @param idGestor  the id gestor
	 * @param idUsuario the id usuario
	 */
	public Gestor(int idGestor, int idUsuario) {
		this.idGestor = idGestor;
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the id gestor.
	 *
	 * @return the id gestor
	 */
	public int getIdGestor() {
		return idGestor;
	}

	/**
	 * Sets the id gestor.
	 *
	 * @param idGestor the new id gestor
	 */
	public void setIdGestor(int idGestor) {
		this.idGestor = idGestor;
	}

	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Sets the id usuario.
	 *
	 * @param idUsuario the new id usuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
