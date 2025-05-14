package modelo;

import java.util.Objects;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private String categoria;
    private String talla;
    private String color;
    private int stock;
    private String imagen;

    // Constructor vacío
    public Producto() {}

    // Constructor completo
    public Producto(int idProducto, String nombre, double precio, String categoria,
                    String talla, String color, int stock, String imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.talla = talla;
        this.color = color;
        this.stock = stock;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // toString: útil para mostrar en listas o comboBoxes
    @Override
    public String toString() {
        return nombre + " (" + talla + ", " + color + ") - " + precio + "€";
    }

    // equals y hashCode: importantes si usas listas, tablas o comparaciones
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto p = (Producto) o;
        return idProducto == p.idProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }
}
