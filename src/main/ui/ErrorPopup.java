package ui;

import javax.swing.*;

// represents the skeleton of the error popups
public class ErrorPopup {

    // EFFECTS: creates the error popup explaining the issue
    public ErrorPopup(EntertainmentTrackerUI entertainmentTrackerUI, String message) {
        JOptionPane.showOptionDialog(
                entertainmentTrackerUI,
                message,
                "Error",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                null,
                null);
    }
}
