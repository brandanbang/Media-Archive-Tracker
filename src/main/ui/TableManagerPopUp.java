package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the popup for managing the entries of the archive
public class TableManagerPopUp extends JPopupMenu implements ActionListener {

    private GUI gui;
    private Table table;

    private JMenuItem add;
    private JMenuItem edit;
    private JMenuItem delete;
    private JMenuItem tag;
    private JMenuItem untag;

    // EFFECTS: manages the popup
    public TableManagerPopUp(GUI gui, Table table) {
        super();
        this.table = table;
        this.gui = gui;

        this.add = new JMenuItem("Add");
        this.edit = new JMenuItem("Edit");
        this.delete = new JMenuItem("Delete");
        this.tag = new JMenuItem("Tag");
        this.untag = new JMenuItem("UnTag");

        this.add.addActionListener(this);
        this.edit.addActionListener(this);
        this.delete.addActionListener(this);
        this.tag.addActionListener(this);
        this.untag.addActionListener(this);

        this.add(add);
        this.add(edit);
        this.add(delete);
        this.add(tag);
        this.add(untag);

        this.table.setComponentPopupMenu(this);

        this.table.addMouseListener(new TableMouseListener(this.table));
    }

    // MODIFIES: archive
    // EFFECTS: dispatches the actions according to what is selected
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        int selectedRow = table.getSelectedRow();
        if (menuItem == add) {
            new AddAction(gui, selectedRow);
        } else if (menuItem == edit) {
            new EditAction(gui, selectedRow);
        } else if (menuItem == delete) {
            new DeleteAction(gui, selectedRow);
        } else if (menuItem == tag) {
            new TagAction(gui, selectedRow);
        } else if (menuItem == untag) {
            new UnTagAction(gui, selectedRow);
        }
        gui.refreshTable();
    }
}
