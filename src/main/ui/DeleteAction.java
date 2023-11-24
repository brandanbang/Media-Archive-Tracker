package ui;

import model.Archive;

import javax.swing.*;
import java.awt.*;

public class DeleteAction extends TableActions {
    public DeleteAction(GUI gui, int selectedRow) {
        super(gui, selectedRow, "Delete Entry", "./images/delete icon.jpg");
    }

    @Override
    void initializeEntries() {
        this.mediaPanel.setLayout(new GridLayout(0,1));
        mediaPanel.add(new JLabel("Are you sure you want to Delete: " + selectedMedia.getTitle() + "?"));
        mediaPanel.add(new JLabel("This cannot be undone."));
    }

    @Override
    void mediaAction() {
        Archive archive = gui.getTracker().archive;
        archive.delEntry(selectedMedia);
    }

}
