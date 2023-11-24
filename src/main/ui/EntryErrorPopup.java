package ui;

import javax.swing.*;

// represents the skeleton of the error popups
public class EntryErrorPopup {

    // EFFECTS: creates the error popup explaining the issue
    public EntryErrorPopup(GUI gui, String message) {
        JOptionPane.showOptionDialog(
                gui,
                message,
                "Error",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                null,
                null);
    }
}
