/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

import java.time.LocalDate;

/**
 * The Class Pedido.
 */
public class Pedido {
	
	/**
	 * The id pedido.
	 */
	private int idPedido;
	
	/**
	 * The fecha.
	 */
	private LocalDate fecha;
	
	/**
	 * The id cliente.
	 */
	private int idCliente;

	/**
	 * Instantiates a new pedido.
	 */
	public Pedido() {
	}

	/**
	 * Instantiates a new pedido.
	 *
	 * @param idPedido  the id pedido
	 * @param fecha     the fecha
	 * @param idCliente the id cliente
	 */
	public Pedido(int idPedido, LocalDate fecha, int idCliente) {
		this.idPedido = idPedido;
		this.fecha = fecha;
		this.idCliente = idCliente;
	}

	/**
	 * Gets the id pedido.
	 *
	 * @return the id pedido
	 */
	public int getIdPedido() {
		return idPedido;
	}

	/**
	 * Sets the id pedido.
	 *
	 * @param idPedido the new id pedido
	 */
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
}
