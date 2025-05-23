/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package modelo;

import modelo.Usuario;
import modelo.Cliente;

/**
 * The Class Sesion.
 */
public class Sesion {

    /**
	 * The usuario actual.
	 */
    public static Usuario usuarioActual = null;
    
    /**
	 * The cliente actual.
	 */
    public static Cliente clienteActual = null;

    /**
	 * Cerrar sesion.
	 */
    public static void cerrarSesion() {
        usuarioActual = null;
        clienteActual = null;
    }

    /**
	 * Es cliente.
	 *
	 * @return true, if successful
	 */
    public static boolean esCliente() {
        return usuarioActual != null && "cliente".equals(usuarioActual.getRol());
    }
}
