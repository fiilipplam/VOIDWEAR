package modelo;

import modelo.Usuario;
import modelo.Cliente;

public class Sesion {

    public static Usuario usuarioActual = null;
    public static Cliente clienteActual = null;

    public static void cerrarSesion() {
        usuarioActual = null;
        clienteActual = null;
    }

    public static boolean esCliente() {
        return usuarioActual != null && "cliente".equals(usuarioActual.getRol());
    }
}
