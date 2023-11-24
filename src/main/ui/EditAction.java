package ui;

import exceptions.InvalidSelection;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// represents the action editing entries
public class EditAction extends TableActions {

    private JTextField titleEntry;
    private JTextField endMarkerEntry;
    private JTextField ratingEntry;
    private JTextField progressEntry;

    // EFFECTS: manages the popup to edit action
    public EditAction(GUI gui, int selectedRow) {
        super(gui, selectedRow, "Edit Entry", "./images/edit icon.jpg");
    }

    // MODIFIES: this
    // EFFECTS: initializes the entry boxes for adding a new entry
    @Override
    void initializeEntries() {
        this.mediaPanel.setLayout(new GridLayout(0,4));
        titleEntry = new JTextField(selectedMedia.getTitle(), 10);
        titleEntry.setEditable(false);
        progressEntry = new JTextField(String.valueOf(selectedMedia.getProgress()),10);
        endMarkerEntry = new JTextField(String.valueOf(selectedMedia.getEnd()),10);

        String rating;
        if (Objects.equals(selectedMedia.getRating(), "no rating")) {
            rating = "-1";
        } else {
            rating = String.valueOf(selectedMedia.getRating());
        }
        ratingEntry = new JTextField(rating,10);

        mediaPanel.add(new JLabel("Title:"));
        mediaPanel.add(titleEntry);
        mediaPanel.add(new JLabel("Progress:"));
        mediaPanel.add(progressEntry);
        mediaPanel.add(new JLabel("End Marker:"));
        mediaPanel.add(endMarkerEntry);
        mediaPanel.add(new JLabel("Rating*:"));
        mediaPanel.add(ratingEntry);
        mediaPanel.add(new JLabel("*Enter: '-1' for no rating"));
    }

    // MODIFIES: archive
    // EFFECTS: changes the selected media with given info
    @Override
    void mediaAction() {
        try {
            int progress = Integer.parseInt(progressEntry.getText());
            int end = Integer.parseInt(endMarkerEntry.getText());
            float rating = Float.parseFloat(ratingEntry.getText());

            selectedMedia.updateProgress(progress);
            selectedMedia.updateEnd(end);
            selectedMedia.updateRating(rating);

        } catch (InvalidSelection is) {
            new EntryErrorPopup(gui, is.getMessage());
        } catch (NumberFormatException nfe) {
            new EntryErrorPopup(gui, "End marker not an integer");
        }
    }
}
