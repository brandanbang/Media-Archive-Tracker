package ui;

import javax.swing.table.DefaultTableModel;

// represents the model that stores the information for the archive for a table
public class TableModel extends DefaultTableModel {
    private static String[] HEADER = {"Title", "Type", "Rating", "Progress %", "Progress", "End", "tags"};

    // EFFECTS: constructs a table with the header
    public TableModel() {
        super(HEADER, 0);
    }

    // MODIFIES: this
    // EFFECTS: prevents editing of any cell in the table
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
