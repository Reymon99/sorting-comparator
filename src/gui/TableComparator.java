package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.Serializable;
import java.util.stream.Stream;

public class TableComparator extends JTable {
    private final Color transparente;

    {
        transparente = new Color(255, 255, 255, 0);
    }

    public TableComparator() {
        ComparatorModel model = new ComparatorModel();
        model.setColumnIdentifiers(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        model.addRow(
                Stream.of(
                        new String[]{""},
                        Methods.values()
                )
                        .flatMap(Stream::of)
                        .toArray(Serializable[]::new)
        );
        model.addRow(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        model.addRow(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        model.addRow(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        setModel(model);
        setBackground(transparente);
        setSelectionBackground(transparente);
        setGridColor(transparente);
        setEnabled(false);
        setDragEnabled(false);
        for (int i = 1; i <= model.getColumnCount(); i++) columns(getColumn(String.valueOf(i)));
    }

    private void columns(TableColumn column) {
        column.setPreferredWidth(column.getIdentifier().equals("1") ? 70: 133);
        column.setCellRenderer(new ComparatorRender());
    }

    private static class ComparatorModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Object.class;
        }
    }

    private static class ComparatorRender extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(CENTER);
            if (row == 0) label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
            return label;
        }
    }
}
