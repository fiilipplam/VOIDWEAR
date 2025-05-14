package modelo;

import java.util.Objects;

public class LineaPedido {

    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    // Constructor vacío
    public LineaPedido() {}

    // Constructor completo
    public LineaPedido(Producto producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y setters
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Subtotal calculado automáticamente
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " = " + getSubtotal() + " €";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineaPedido)) return false;
        LineaPedido that = (LineaPedido) o;
        return Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }
}
