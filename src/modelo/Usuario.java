/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * The Class Usuario.
 */
@Entity
public class Usuario implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -6236610328319541172L;

	/**
	 * The correo.
	 */
	@Id
	private String correo;

	/**
	 * The nombre.
	 */
	private String nombre;
	
	/**
	 * The contrasena.
	 */
	private String contrasena;

	/**
	 * The rol.
	 */
	@Enumerated(EnumType.STRING)
	private Rol rol;

	/**
	 * Instantiates a new usuario.
	 */
	public Usuario() {
	}

	/**
	 * Instantiates a new usuario.
	 *
	 * @param nombre     the nombre
	 * @param correo     the correo
	 * @param contrasena the contrasena
	 * @param rol        the rol
	 */
	public Usuario(String nombre, String correo, String contrasena, Rol rol) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	/**
	 * Gets the correo.
	 *
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
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
	 * Gets the contrasena.
	 *
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Gets the rol.
	 *
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * Sets the correo.
	 *
	 * @param correo the new correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
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
	 * Sets the contrasena.
	 *
	 * @param contrasena the new contrasena
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Sets the rol.
	 *
	 * @param rol the new rol
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Usuario))
			return false;
		Usuario usuario = (Usuario) o;
		return correo != null && correo.equals(usuario.correo);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}
}
