package util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtils {

    /**
     * Carga una imagen desde una ruta relativa, la escala al tamaño deseado y la devuelve como ImageIcon.
     * @param ruta Ruta relativa de la imagen (por ejemplo: "recursos/imagenes/productos/img.jpg")
     * @param ancho Ancho deseado
     * @param alto Alto deseado
     * @return ImageIcon escalado, o un JLabel con texto si no se encuentra
     */
    public static ImageIcon cargarMiniatura(String ruta, int ancho, int alto) {
        try {
            BufferedImage imagenOriginal = ImageIO.read(new File(ruta));
            Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscalada);
        } catch (Exception e) {
            System.err.println("❌ Error al cargar la imagen: " + ruta);
            return new ImageIcon(); // Devuelve un icono vacío
        }
    }
}
