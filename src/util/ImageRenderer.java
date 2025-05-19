package util;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
    private final int width;
    private final int height;

    public ImageRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof ImageIcon) {
            ImageIcon icon = (ImageIcon) value;
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(img));
            setText(""); // No texto
        } else {
            setIcon(null);
            setText((value != null) ? value.toString() : "");
        }

        return this;
    }
}
