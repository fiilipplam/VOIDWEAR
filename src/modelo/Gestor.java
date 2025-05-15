package modelo;

public class Gestor {
    private int idGestor;
    private int idUsuario;

    public Gestor() {}

    public Gestor(int idGestor, int idUsuario) {
        this.idGestor = idGestor;
        this.idUsuario = idUsuario;
    }

    public int getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(int idGestor) {
        this.idGestor = idGestor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
