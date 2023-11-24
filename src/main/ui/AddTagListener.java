package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTagListener implements ActionListener {

    private JButton button;
    private JList<String> currentTags;
    private DefaultListModel<String> model;
    private JList<String> allTags;
    private DefaultListModel<String> allModel;

    public AddTagListener(JButton button, JList<String> currentTags, DefaultListModel<String> model,
                          JList<String> allTags, DefaultListModel<String> allModel) {
        this.button = button;
        this.currentTags = currentTags;
        this.model = model;
        this.allTags = allTags;
        this.allModel = allModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tag = allTags.getSelectedValue();
        System.out.println(tag + "a");

        if (alreadyInList(tag)) {
            return;
        }

        model.addElement(tag);

        //Select the new item and make it visible.
        currentTags.ensureIndexIsVisible(model.size() - 1);
    }

    protected boolean alreadyInList(String name) {
        return model.contains(name);
    }
}
