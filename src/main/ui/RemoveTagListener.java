package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveTagListener implements ActionListener {

    private JList<String> currentTags;
    private JButton button;
    private DefaultListModel model;

    public RemoveTagListener(JButton button, JList<String> currentTags, DefaultListModel<String> model) {
        this.currentTags = currentTags;
        this.button = button;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        int index = currentTags.getSelectedIndex();
        model.remove(index);

        int size = model.getSize();

        if (size == 0) { //Nobody's left, disable firing.
            button.setEnabled(false);

        } else {
            if (index == model.getSize()) {
                index--;
            }

            currentTags.setSelectedIndex(index);
            currentTags.ensureIndexIsVisible(index);
        }
    }
}
