package modelo;

public class Cliente {
    private int idCliente;
    private String direccion;
    private String telefono;
    private int idUsuario;

    public Cliente() {}

    public Cliente(int idCliente, String direccion, String telefono, int idUsuario) {
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
