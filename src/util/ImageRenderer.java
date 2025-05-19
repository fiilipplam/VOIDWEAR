package util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;

public class ImageRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
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
