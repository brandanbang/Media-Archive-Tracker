package ui;

import javax.swing.*;

public class EntryErrorPopup {

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
