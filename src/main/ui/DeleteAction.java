package ui;

import model.Archive;

import javax.swing.*;
import java.awt.*;

// represents teh action of deleting entries
public class DeleteAction extends TableActions {

    // EFFECTS: manages the delete popup
    public DeleteAction(GUI gui, int selectedRow) {
        super(gui, selectedRow, "Delete Entry", "./images/delete icon.jpg");
    }

    // MODIFIES: this
    // EFFECTS: initializes the components for the popup
    @Override
    public void initializeEntries() {
        this.mediaPanel.setLayout(new GridLayout(0,1));
        mediaPanel.add(new JLabel("Are you sure you want to Delete: " + selectedMedia.getTitle() + "?"));
        mediaPanel.add(new JLabel("This cannot be undone."));
    }

    // MODIFIES: archive
    // EFFECTS: deletes the selected archive
    @Override
    void mediaAction() {
        Archive archive = gui.getTracker().archive;
        archive.delEntry(selectedMedia);
    }

}
