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

// represents the action of sorting displayed entries
public class SortAction extends AbstractAction implements ActionListener {

    private Icon icon;
    private EntertainmentTrackerUI entertainmentTrackerUI;
    private JPanel mediaPanel;
    private JComboBox<String> sortTypeSelector;
    private JComboBox<String> sortOrderSelector;


    // EFFECTS: initializes the sort action
    public SortAction(EntertainmentTrackerUI entertainmentTrackerUI) {
        super("Sort");
        this.entertainmentTrackerUI = entertainmentTrackerUI;
        this.mediaPanel = new JPanel();

        try {
            Image image = ImageIO.read(new File("./images/sort icon.png"));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("sort Icon not found");
        }
    }


    // MODIFIES: this, archive
    // EFFECTS: initializes the field components for the popup
    private void initializeComponents() {
        mediaPanel.removeAll();
        mediaPanel.setLayout(new GridLayout(0, 3));

        String[] sortOrder = {"Ascending", "Descending"};
        String[] sortType = {"Title", "Rating", "Progress Percent"};

        sortOrderSelector = new JComboBox<>(sortOrder);
        sortTypeSelector = new JComboBox<>(sortType);

        mediaPanel.add(sortOrderSelector);
        mediaPanel.add(sortTypeSelector);
    }


    // MODIFIES: this
    // EFFECTS: creates a popup for user to select and confirm the sort
    @Override
    public void actionPerformed(ActionEvent e) {
        initializeComponents();

        int option = JOptionPane.showConfirmDialog(
                null,
                mediaPanel,
                "Filter",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon);
        if (option == JOptionPane.OK_OPTION) {
            sortOptions();
            entertainmentTrackerUI.refreshTable();
        }
    }

    // MODIFIES: archive
    // EFFECTS: sorts the archive based on selected instructions
    private void sortOptions() {
        Archive archive = entertainmentTrackerUI.getArchive();
        String order = String.valueOf(sortOrderSelector.getSelectedItem());
        String type = String.valueOf(sortTypeSelector.getSelectedItem());
        if (order.equals("Ascending")) {
            if (type.equals("Title")) {
                archive.sortTitleAscending();
            } else if (type.equals("Rating")) {
                archive.sortRatingAscending();
            } else {
                archive.sortProgressAscending();
            }
        } else {
            if (type.equals("Title")) {
                archive.sortTitleDescending();
            } else if (type.equals("Rating")) {
                archive.sortRatingDescending();
            } else {
                archive.sortProgressDescending();
            }
        }
    }
}
