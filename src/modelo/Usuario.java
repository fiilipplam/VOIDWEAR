package modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6236610328319541172L;

	@Id
	private String correo;

	private String nombre;
	private String contrasena;

	@Enumerated(EnumType.STRING)
	private Rol rol;

	public Usuario() {
	}

	public Usuario(String nombre, String correo, String contrasena, Rol rol) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public Rol getRol() {
		return rol;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Usuario))
			return false;
		Usuario usuario = (Usuario) o;
		return correo != null && correo.equals(usuario.correo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}
}
