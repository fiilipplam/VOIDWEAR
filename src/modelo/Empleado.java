package modelo;

public class Empleado extends Usuario {

    // Constructor vacío
    public Empleado() {
        super();
        this.rol = Rol.EMPLEADO;
    }

    // Constructor completo
    public Empleado(int idUsuario, String nombre, String correo, String contrasena) {
        super(idUsuario, nombre, correo, contrasena, Rol.EMPLEADO);
    }

    // toString personalizado
    @Override
    public String toString() {
        return super.toString() + " (Empleado)";
    }
}
