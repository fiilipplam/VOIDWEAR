package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private int idPedido;
    private LocalDate fecha;
    private Cliente cliente;
    private List<LineaPedido> lineas;

    // Constructor vacío
    public Pedido() {
        this.lineas = new ArrayList<>();
    }

    // Constructor completo
    public Pedido(int idPedido, LocalDate fecha, Cliente cliente, List<LineaPedido> lineas) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.cliente = cliente;
        this.lineas = lineas != null ? lineas : new ArrayList<>();
    }

    // Getters y setters
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    // Método para calcular el total del pedido
    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    // equals y hashCode basados en el ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return idPedido == pedido.idPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido);
    }

    // toString simplificado
    @Override
    public String toString() {
        return "Pedido #" + idPedido + " | Cliente: " + cliente.getNombre() + " | Total: " + calcularTotal() + " €";
    }
}
