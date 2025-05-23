/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

/**
 * The Class Producto.
 */
public class Producto {
	
	/**
	 * The id producto.
	 */
	private int idProducto;
	
	/**
	 * The nombre.
	 */
	private String nombre;
	
	/**
	 * The precio.
	 */
	private double precio;
	
	/**
	 * The categoria.
	 */
	private String categoria;
	
	/**
	 * The talla.
	 */
	private String talla;
	
	/**
	 * The color.
	 */
	private String color;
	
	/**
	 * The stock.
	 */
	private int stock;
	
	/**
	 * The imagen.
	 */
	private String imagen;

	/**
	 * Instantiates a new producto.
	 */
	public Producto() {
	}

	/**
	 * Instantiates a new producto.
	 *
	 * @param idProducto the id producto
	 * @param nombre     the nombre
	 * @param precio     the precio
	 * @param categoria  the categoria
	 * @param talla      the talla
	 * @param color      the color
	 * @param stock      the stock
	 * @param imagen     the imagen
	 */
	public Producto(int idProducto, String nombre, double precio, String categoria, String talla, String color,
			int stock, String imagen) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.talla = talla;
		this.color = color;
		this.stock = stock;
		this.imagen = imagen;
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
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio the new precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria the new categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the talla.
	 *
	 * @return the talla
	 */
	public String getTalla() {
		return talla;
	}

	/**
	 * Sets the talla.
	 *
	 * @param talla the new talla
	 */
	public void setTalla(String talla) {
		this.talla = talla;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Sets the stock.
	 *
	 * @param stock the new stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Gets the imagen.
	 *
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Sets the imagen.
	 *
	 * @param imagen the new imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
