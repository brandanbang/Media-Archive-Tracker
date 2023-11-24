package ui;

import exceptions.InvalidSelection;
import model.Archive;
import model.Media;
import model.MediaType;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

// represents the action adding entries
public class AddAction extends TableActions {

    private static String[] OPTIONS = Stream.of(MediaType.values()).map(MediaType::name).toArray(String[]::new);
    private JTextField titleEntry;
    private JComboBox<String> typeOptions;
    private JTextField endMarkerEntry;

    // EFFECTS: manages the popup to add action
    public AddAction(GUI gui, int selectedRow) {
        super(gui, -1, "Add Entry", "./images/add icon.jpg");
    }

    // MODIFIES: this
    // EFFECTS: initializes the entry boxes for adding a new entry
    @Override
    void initializeEntries() {
        this.mediaPanel.setLayout(new GridLayout(0,2));
        titleEntry = new JTextField(10);
        typeOptions = new JComboBox<>(OPTIONS);
        endMarkerEntry = new JTextField(10);

        mediaPanel.add(new JLabel("Title:"));
        mediaPanel.add(titleEntry);
        mediaPanel.add(new JLabel("Media Type:"));
        mediaPanel.add(typeOptions);
        mediaPanel.add(new JLabel("End Marker:"));
        mediaPanel.add(endMarkerEntry);
    }

    // MODIFIES: Archive
    // EFFECTS: creates and adds the media entry with given info to archive
    @Override
    void mediaAction() {
        try {
            Archive archive = gui.getTracker().archive;
            String title = titleEntry.getText();
            MediaType type = MediaType.valueOf((String) typeOptions.getSelectedItem());
            int end = Integer.parseInt(endMarkerEntry.getText());

            Media newEntry = new Media(title, end, archive, type);

            for (Media m : archive.getEntries()) {
                if (newEntry.equals(m)) {
                    throw new InvalidSelection("Entry with given title already exists.");
                }
            }

            archive.addEntry(newEntry);
        } catch (InvalidSelection is) {
            new EntryErrorPopup(gui, is.getMessage());
        } catch (NumberFormatException nfe) {
            new EntryErrorPopup(gui, "End marker not an integer");
        }
    }
}
