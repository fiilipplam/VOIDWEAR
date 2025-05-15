package modelo;

import javax.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private int id;

    private String nombre;
    private String correo;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario() {}

    public Usuario(String nombre, String correo, String contrasena, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getter necesario para registrar accesos en el log
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
