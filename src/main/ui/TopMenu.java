package ui;

import javax.swing.*;
import java.awt.*;

// represents the top bar and its associated actions
public class TopMenu extends JMenuBar {
    private GUI gui;

    public TopMenu(GUI gui) {
        super();
        this.gui = gui;
        persistenceOptions();
        viewOptions();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds persistence actions to the menu
    private void persistenceOptions() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('P');
        addMenuItem(fileMenu, new SaveAction(gui), KeyStroke.getKeyStroke("control S"));
        addMenuItem(fileMenu, new LoadAction(gui), KeyStroke.getKeyStroke("control L"));
        this.add(fileMenu, BorderLayout.PAGE_START);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds filter actions to the menu
    private void viewOptions() {
        JMenu fileMenu = new JMenu("View");
        fileMenu.setMnemonic('V');
        addMenuItem(fileMenu, new FilterAction(gui), KeyStroke.getKeyStroke("control F"));
        addMenuItem(fileMenu, new SortAction(gui), KeyStroke.getKeyStroke("control O"));
        addMenuItem(fileMenu, new ResetViewAction(gui), KeyStroke.getKeyStroke("control P"));
        this.add(fileMenu, BorderLayout.PAGE_START);
    }

    // MODIFIES: this
    // EFFECTS: adds an item with given handler to the given menu
    //          theMenu     : menu to which the item is added
    //          action      : handler for item
    //          accelerator : keystroke accelerator/ shortcut for this item
    // CITATION: AlarmController
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }
}
