/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

/**
 * The Class LineaPedido.
 */
public class LineaPedido {
	
	/**
	 * The id pedido.
	 */
	private int idPedido;
	
	/**
	 * The id producto.
	 */
	private int idProducto;
	
	/**
	 * The cantidad.
	 */
	private int cantidad;
	
	/**
	 * The precio unitario.
	 */
	private double precioUnitario;

	/**
	 * Instantiates a new linea pedido.
	 */
	public LineaPedido() {
	}

	/**
	 * Instantiates a new linea pedido.
	 *
	 * @param idPedido       the id pedido
	 * @param idProducto     the id producto
	 * @param cantidad       the cantidad
	 * @param precioUnitario the precio unitario
	 */
	public LineaPedido(int idPedido, int idProducto, int cantidad, double precioUnitario) {
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
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
	 * Gets the id producto.
	 *
	 * @return the id producto
	 */
	public int getIdProducto() {
		return idProducto;
	}

	/**
	 * Sets the id producto.
	 *
	 * @param idProducto the new id producto
	 */
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the precio unitario.
	 *
	 * @return the precio unitario
	 */
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * Sets the precio unitario.
	 *
	 * @param precioUnitario the new precio unitario
	 */
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}
