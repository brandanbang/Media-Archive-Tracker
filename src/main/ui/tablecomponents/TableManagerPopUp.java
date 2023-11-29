package ui.tablecomponents;

import ui.EntertainmentTrackerUI;
import ui.ErrorPopup;
import ui.tablecomponents.actions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the popup for managing the entries of the archive
public class TableManagerPopUp extends JPopupMenu implements ActionListener {

    private EntertainmentTrackerUI entertainmentTrackerUI;
    private Table table;

    private JMenuItem add;
    private JMenuItem edit;
    private JMenuItem delete;
    private JMenuItem tag;
    private JMenuItem untag;

    // EFFECTS: manages the popup
    public TableManagerPopUp(EntertainmentTrackerUI entertainmentTrackerUI, Table table) {
        super();
        this.table = table;
        this.entertainmentTrackerUI = entertainmentTrackerUI;

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
        try {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            int selectedRow = table.getSelectedRow();
            if (menuItem == add) {
                new AddAction(entertainmentTrackerUI, selectedRow);
            } else if (menuItem == edit) {
                new EditAction(entertainmentTrackerUI, selectedRow);
            } else if (menuItem == delete) {
                new DeleteAction(entertainmentTrackerUI, selectedRow);
            } else if (menuItem == tag) {
                new TagAction(entertainmentTrackerUI, selectedRow);
            } else if (menuItem == untag) {
                new UnTagAction(entertainmentTrackerUI, selectedRow);
            }
        } catch (NullPointerException npe) {
            new ErrorPopup(entertainmentTrackerUI, "No entry was selected. \nSelect an entry with Left Click.");
        }
        entertainmentTrackerUI.refreshTable();
    }
}
