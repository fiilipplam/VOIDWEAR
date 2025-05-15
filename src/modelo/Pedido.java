package modelo;

import java.time.LocalDate;

public class Pedido {
    private int idPedido;
    private LocalDate fecha;
    private int idCliente;

    public Pedido() {}

    public Pedido(int idPedido, LocalDate fecha, int idCliente) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
