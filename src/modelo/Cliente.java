/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

import bd.ClienteDAO;

/**
 * The Class Cliente.
 */
public class Cliente {
	
	/**
	 * The id cliente.
	 */
	private int idCliente;
	
	/**
	 * The direccion.
	 */
	private String direccion;
	
	/**
	 * The telefono.
	 */
	private String telefono;
	
	/**
	 * The id usuario.
	 */
	private int idUsuario;

	/**
	 * Instantiates a new cliente.
	 */
	public Cliente() {
	}

	/**
	 * Instantiates a new cliente.
	 *
	 * @param idCliente the id cliente
	 * @param direccion the direccion
	 * @param telefono  the telefono
	 * @param idUsuario the id usuario
	 */
	public Cliente(int idCliente, String direccion, String telefono, int idUsuario) {
		this.idCliente = idCliente;
		this.direccion = direccion;
		this.telefono = telefono;
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the id cliente.
	 *
	 * @return the id cliente
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the id cliente.
	 *
	 * @param idCliente the new id cliente
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono the new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ClienteDAO.obtenerNombreSQL(idUsuario) + " (ID Cliente: " + idCliente + ")";
	}

}
