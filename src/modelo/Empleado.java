/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

/**
 * The Class Empleado.
 */
public class Empleado {
	
	/**
	 * The id empleado.
	 */
	private int idEmpleado;
	
	/**
	 * The id usuario.
	 */
	private int idUsuario;

	/**
	 * Instantiates a new empleado.
	 */
	public Empleado() {
	}

	/**
	 * Instantiates a new empleado.
	 *
	 * @param idEmpleado the id empleado
	 * @param idUsuario  the id usuario
	 */
	public Empleado(int idEmpleado, int idUsuario) {
		this.idEmpleado = idEmpleado;
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the id empleado.
	 *
	 * @return the id empleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * Sets the id empleado.
	 *
	 * @param idEmpleado the new id empleado
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
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
