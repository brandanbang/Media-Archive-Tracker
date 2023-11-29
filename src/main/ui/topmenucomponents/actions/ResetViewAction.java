package ui.topmenucomponents.actions;

import model.Archive;
import ui.EntertainmentTrackerUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// represents the action of resetting the filters of the displayed entries
public class ResetViewAction extends AbstractAction implements ActionListener {

    private Icon icon;
    private EntertainmentTrackerUI entertainmentTrackerUI;


    // EFFECTS: initializes the filter action
    public ResetViewAction(EntertainmentTrackerUI entertainmentTrackerUI) {
        super("Reset");
        this.entertainmentTrackerUI = entertainmentTrackerUI;

        try {
            Image image = ImageIO.read(new File("./images/reset icon.jpg"));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("reset Icon not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a popup for user to select and confirm the filter
    @Override
    public void actionPerformed(ActionEvent e) {
        Archive archive = entertainmentTrackerUI.getArchive();

        int option = JOptionPane.showOptionDialog(
                null,
                "Are you sure you want to reset the filters?",
                "Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                null,
                null);
        if (option == JOptionPane.YES_OPTION) {
            archive.resetFilters();
            entertainmentTrackerUI.refreshTable();
        }
    }
}
