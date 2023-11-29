package ui.tablecomponents;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// represents the handler for when the table is clicked
// CITATION: AlarmController
public class TableMouseListener extends MouseAdapter {

    private Table table;

    // EFFECTS: constructs the handler with the associated table
    public TableMouseListener(Table table) {
        this.table = table;
    }

    // EFFECTS: gets the table row associated with the point of the mouse when clicked
    @Override
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int selectedRow = this.table.rowAtPoint(point);
        if (selectedRow >= 0) {
            this.table.setRowSelectionInterval(selectedRow, selectedRow);
        }
    }
}
