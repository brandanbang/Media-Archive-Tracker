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
import java.util.Objects;
import java.util.Set;

// represents the action of filtering the displayed entries
public class FilterAction extends AbstractAction implements ActionListener {

    private Icon icon;
    private EntertainmentTrackerUI entertainmentTrackerUI;
    private JPanel mediaPanel;
    private JComboBox<String> filterTypeSelector;
    private JComboBox<String> tagSelector;


    // EFFECTS: initializes the filter action
    public FilterAction(EntertainmentTrackerUI entertainmentTrackerUI) {
        super("Filter");
        this.entertainmentTrackerUI = entertainmentTrackerUI;
        this.mediaPanel = new JPanel();

        try {
            Image image = ImageIO.read(new File("./images/filter icon.png"));
            icon = new ImageIcon(image);
        } catch (IOException e) {
            System.out.println("Warning: Filter Icon not found");
        }
    }


    // MODIFIES: this, archive
    // EFFECTS: initializes the field components for the popup
    private void initializeComponents() {
        mediaPanel.removeAll();
        mediaPanel.setLayout(new GridLayout(0, 3));

        Set<String> tagSet = entertainmentTrackerUI.getArchive().getTags();

        String[] tags = tagSet.toArray(new String[0]);

        String[] filterType = {"whitelist", "blacklist"};

        filterTypeSelector = new JComboBox<>(filterType);
        tagSelector = new JComboBox<>(tags);

        mediaPanel.add(filterTypeSelector);
        mediaPanel.add(tagSelector);
    }


    // MODIFIES: this
    // EFFECTS: creates a popup for user to select and confirm the filter
    @Override
    public void actionPerformed(ActionEvent e) {
        initializeComponents();
        Archive archive = entertainmentTrackerUI.getArchive();

        int option = JOptionPane.showConfirmDialog(
                null,
                mediaPanel,
                "Filter",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon);
        if (option == JOptionPane.OK_OPTION) {
            String type = String.valueOf(filterTypeSelector.getSelectedItem());
            String tag = String.valueOf(tagSelector.getSelectedItem());

            if (Objects.equals(type, "whitelist")) {
                archive.whitelistTag(tag);
            } else {
                archive.blacklistTag(tag);
            }
            entertainmentTrackerUI.refreshTable();
        }
    }
}
