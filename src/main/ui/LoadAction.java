package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

// represents the action of load the archive from file
public class LoadAction extends AbstractAction {

    Icon icon;

    // EFFECTS: initializes the load action
    public LoadAction() {
        super("Load");
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
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                null,
                null);
        if (option == JOptionPane.YES_OPTION) {
            //save
            System.out.println("saved data");
        } else {
            System.out.println("data NOT saved");
        }
    }
}
