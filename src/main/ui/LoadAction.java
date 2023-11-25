package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// represents the action of load the archive from file
public class LoadAction extends AbstractAction {

    private Icon icon;
    private GUI gui;

    // EFFECTS: initializes the load action
    public LoadAction(GUI gui) {
        super("Load");
        this.gui = gui;
        try {
            Image image = ImageIO.read(new File("./images/load icon.jpg"));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("Icon not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a popup for user to confirm the load
    //          if yes, load the archive from file
    @Override
    public void actionPerformed(ActionEvent evt) {
        int option = JOptionPane.showOptionDialog(
                null,
                "Would you like to load?\nThe current progress will be overwritten.",
                "Load",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                null,
                null);
        if (option == JOptionPane.YES_OPTION) {
            this.gui.getTracker().load();
            this.gui.refreshTable();
            System.out.println("data loaded");
        } else {
            System.out.println("data no loaded");
        }
    }
}
