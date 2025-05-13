package modelo;

public class Gestor extends Usuario {

    // Constructor vacío
    public Gestor() {
        super();
        this.rol = Rol.GESTOR;
    }

    // Constructor completo
    public Gestor(int idUsuario, String nombre, String correo, String contrasena) {
        super(idUsuario, nombre, correo, contrasena, Rol.GESTOR);
    }

    // toString personalizado
    @Override
    public String toString() {
        return super.toString() + " (Gestor)";
    }
}
