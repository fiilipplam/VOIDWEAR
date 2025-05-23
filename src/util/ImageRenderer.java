/**
 * ==================================================
 * Proyecto: LPB Basketball
 * 
 * @author ${author}
 * ==================================================
 */
package util;

import java.awt.Component;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * The Class ImageRenderer.
 */
public class ImageRenderer extends DefaultTableCellRenderer {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1692596156430126746L;

	/**
	 * Gets the table cell renderer component.
	 *
	 * @param table      the table
	 * @param value      the value
	 * @param isSelected the is selected
	 * @param hasFocus   the has focus
	 * @param row        the row
	 * @param column     the column
	 * @return the table cell renderer component
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (value != null && value instanceof String) {
			String ruta = (String) value;

			File file = new File(ruta);

			// Si la imagen no se encuentra, intenta con rutas alternativas
			if (!file.exists()) {
				file = new File("recursos/" + ruta);
			}

			if (!file.exists()) {
				file = new File("VOIDWEAR/" + ruta);
			}

			if (file.exists()) {
				ImageIcon icono = new ImageIcon(file.getAbsolutePath());
				Image imagenEscalada = icono.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
				return new JLabel(new ImageIcon(imagenEscalada));
			}
		}

		// Si no hay imagen válida, se muestra texto por defecto
		return new JLabel("Sin imagen");
	}
}
