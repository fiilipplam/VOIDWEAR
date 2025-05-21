package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtils {

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
