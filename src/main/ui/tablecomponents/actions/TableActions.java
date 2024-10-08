package ui.tablecomponents.actions;

import model.Media;
import ui.EntertainmentTrackerUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// represents the abstract class associated with the table
public abstract class TableActions {

    protected EntertainmentTrackerUI entertainmentTrackerUI;
    protected Icon icon;
    protected JPanel mediaPanel;
    protected Media selectedMedia;

    // EFFECTS: manages the popup and action
    public TableActions(EntertainmentTrackerUI ui, int selectedRow, String action, String pathName) {
        this.entertainmentTrackerUI = ui;
        this.mediaPanel = new JPanel();
        if (selectedRow >= 0) {
            this.selectedMedia = entertainmentTrackerUI.getArchive().getDisplayEntries().get(selectedRow);
        }

        initializeIcon(pathName);
        initializeEntries();
        makePopup(action);
    }

    // MODIFIES: this
    // EFFECTS: initializes the icon
    private void initializeIcon(String pathName) {
        try {
            Image image = ImageIO.read(new File(pathName));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("Warning: Icon not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the entry boxes
    protected abstract void initializeEntries();

    // MODIFIES: this
    // EFFECTS: creates the popup menu
    private void makePopup(String title) {
        int option = JOptionPane.showConfirmDialog(
                null,
                mediaPanel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon);
        if (option == JOptionPane.OK_OPTION) {
            mediaAction();
        }
    }

    // EFFECTS: abstract method to interact with the archive
    abstract void mediaAction();
}
