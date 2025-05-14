package modelo;

public class Cliente extends Usuario {

    private String direccion;
    private String telefono;

    // Constructor vacío
    public Cliente() {
        super();
        this.rol = Rol.CLIENTE;
    }

    // Constructor completo
    public Cliente(int idUsuario, String nombre, String correo, String contrasena, String direccion, String telefono) {
        super(idUsuario, nombre, correo, contrasena, Rol.CLIENTE);
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // toString personalizado
    @Override
    public String toString() {
        return super.toString() + " - " + direccion + " | " + telefono;
    }
}
