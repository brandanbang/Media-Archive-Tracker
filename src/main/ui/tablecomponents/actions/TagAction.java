package ui.tablecomponents.actions;

import ui.EntertainmentTrackerUI;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

// manages the action of adding a tag from an entry
public class TagAction extends TableActions {

    private JList<String> allTags;
    private DefaultListModel<String> allTagsModel;
    private JList<String> currentTags;
    private DefaultListModel<String> currentTagsModel;
    private JTextField tagEntry;

    // EFFECT: creates and loads parts for the action of adding a tag from an entry
    public TagAction(EntertainmentTrackerUI entertainmentTrackerUI, int selectedRow) {
        super(entertainmentTrackerUI, selectedRow, "Tag", "./images/tag icon.png");
    }

    // MODIFIES: this
    // EFFECTS: initializes the entry boxes for tags
    @Override
    public void initializeEntries() {
        this.mediaPanel.setLayout(new GridLayout(0, 3));
        tagEntry = new JTextField(10);

        mediaPanel.add(new JLabel("All Tags:"));
        mediaPanel.add(new JLabel(" "));
        mediaPanel.add(new JLabel("Current Tags:"));

        tagOptions();
        mediaPanel.add(new JLabel(" "));
        currentTags();

        mediaPanel.add(new JLabel("Tag: "));
        mediaPanel.add(tagEntry);
    }

    // MODIFIES: this;
    // EFFECTS: shows all existing tags in archive
    private void tagOptions() {
        Set<String> tagSet = entertainmentTrackerUI.getArchive().getTags();

        allTagsModel = new DefaultListModel<>();
        allTagsModel.addAll(tagSet);

        allTags = new JList<>(allTagsModel);
        allTags.setSelectionMode(SINGLE_SELECTION);
        allTags.setVisibleRowCount(3);
        allTags.setFixedCellWidth(100);

        mediaPanel.add(new JScrollPane(allTags));
    }

    // MODIFIES:
    // EFFECTS: shows all tags associated with selected entry
    private void currentTags() {
        Set<String> tagSet = selectedMedia.getTags();

        currentTagsModel = new DefaultListModel<>();
        currentTagsModel.addAll(tagSet);

        currentTags = new JList<>(currentTagsModel);
        currentTags.setSelectionMode(SINGLE_SELECTION);
        currentTags.setVisibleRowCount(3);
        currentTags.setFixedCellWidth(100);

        mediaPanel.add(new JScrollPane(currentTags));
    }

    // MODIFIES: archive
    // EFFECTS: adds selected tag to selected entry
    @Override
    void mediaAction() {
        String tag = tagEntry.getText();
        selectedMedia.addTag(tag);
    }
}
