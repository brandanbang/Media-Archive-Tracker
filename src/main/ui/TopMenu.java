package ui;

import javax.swing.*;
import java.awt.*;

// represents the top bar and its associated actions
public class TopMenu extends JMenuBar {
    private GUI gui;
    JMenu persistenceMenu;
    static JMenuItem save;
    JMenuItem load;
    JMenu viewMenu;
    JMenuItem filter;
    JMenuItem sort;
    JMenuItem reset;


    public TopMenu(GUI gui) {
        super();
        this.gui = gui;
        persistenceOptions();
        viewOptions();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds persistence actions to the menu
    private void persistenceOptions() {
        persistenceMenu = new JMenu("File");
        persistenceMenu.setMnemonic('P');

        save = new JMenuItem(new SaveAction(gui));
        load = new JMenuItem(new LoadAction(gui));

        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        load.setAccelerator(KeyStroke.getKeyStroke("control L"));

        persistenceMenu.add(save);
        persistenceMenu.add(load);
        this.add(persistenceMenu, BorderLayout.PAGE_START);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds filter actions to the menu
    private void viewOptions() {
        viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');

        filter = new JMenuItem(new FilterAction(gui));
        sort = new JMenuItem(new SortAction(gui));
        reset = new JMenuItem(new ResetViewAction(gui));

        filter.setAccelerator(KeyStroke.getKeyStroke("control F"));
        sort.setAccelerator(KeyStroke.getKeyStroke("control O"));
        reset.setAccelerator(KeyStroke.getKeyStroke("control P"));

        viewMenu.add(filter);
        viewMenu.add(sort);
        viewMenu.add(reset);

        this.add(viewMenu, BorderLayout.PAGE_START);
    }
}
