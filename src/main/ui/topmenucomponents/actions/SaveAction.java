package ui.topmenucomponents.actions;

import ui.EntertainmentTrackerUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// represents the action of saving the archive to file
public class SaveAction extends AbstractAction {

    private Icon icon;
    private EntertainmentTrackerUI entertainmentTrackerUI;

    // EFFECTS: initializes the save action
    public SaveAction(EntertainmentTrackerUI entertainmentTrackerUI) {
        super("Save");
        this.entertainmentTrackerUI = entertainmentTrackerUI;
        try {
            Image image = ImageIO.read(new File("./images/save icon.jpg"));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("Icon not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a popup for user to confirm the save
    //          if yes, save the archive to file
    @Override
    public void actionPerformed(ActionEvent evt) {
        int option = JOptionPane.showOptionDialog(
                null,
                "Would you like to save?\nThe previous save will be overwritten.",
                "Save",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                null,
                null);
        if (option == JOptionPane.YES_OPTION) {
            this.entertainmentTrackerUI.save();
        }
    }
}
