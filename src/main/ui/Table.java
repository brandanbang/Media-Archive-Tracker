package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

// represents the table that holds the archive contents
public class Table extends JTable {

    // MODIFIES: this
    // EFFECTS: sets up the table display for archive
    public Table(DefaultTableModel initalizeTableModel) {
        super(initalizeTableModel);

        this.setRowHeight(40);
        this.getTableHeader().setReorderingAllowed(false);
        this.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        this.setFillsViewportHeight(true);
        resizeColumnWidth();
    }

    // MODIFIES: this
    // EFFECTS: changes the column widths of table based on how much data is in each
    private void resizeColumnWidth() {
        final TableColumnModel columnModel = this.getColumnModel();
        for (int column = 0; column < this.getColumnCount(); column++) {
            int width = 15;
            for (int row = 0; row < this.getRowCount(); row++) {
                TableCellRenderer renderer = this.getCellRenderer(row, column);
                Component comp = this.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
