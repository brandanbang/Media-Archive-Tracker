package ui;

import model.Archive;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// represents the action of filtering the displayed entries
public class ResetViewAction extends AbstractAction implements ActionListener {

    private Icon icon;
    private GUI gui;
    private JPanel mediaPanel;
    private JComboBox<String> filterTypeSelector;
    private JComboBox<String> tagSelector;


    // EFFECTS: initializes the filter action
    public ResetViewAction(GUI gui) {
        super("Reset");
        this.gui = gui;
        this.mediaPanel = new JPanel();

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
        Archive archive = gui.getTracker().archive;

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
            archive.blacklistTag("");
            gui.refreshTable();
        }
    }
}
