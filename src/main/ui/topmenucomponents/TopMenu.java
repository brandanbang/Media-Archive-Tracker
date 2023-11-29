package ui.topmenucomponents;

import ui.EntertainmentTrackerUI;
import ui.topmenucomponents.actions.*;

import javax.swing.*;
import java.awt.*;

// represents the top bar and its associated actions
public class TopMenu extends JMenuBar {
    private EntertainmentTrackerUI entertainmentTrackerUI;
    private JMenu persistenceMenu;
    private static JMenuItem save;
    private JMenuItem load;
    private JMenu viewMenu;
    private JMenuItem filter;
    private JMenuItem sort;
    private JMenuItem reset;

    // EFFECTS: creates the top menu for the tracker with persistence and viewing actions
    public TopMenu(EntertainmentTrackerUI entertainmentTrackerUI) {
        super();
        this.entertainmentTrackerUI = entertainmentTrackerUI;
        persistenceOptions();
        viewOptions();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds persistence actions to the menu
    private void persistenceOptions() {
        persistenceMenu = new JMenu("File");
        persistenceMenu.setMnemonic('P');

        save = new JMenuItem(new SaveAction(entertainmentTrackerUI));
        load = new JMenuItem(new LoadAction(entertainmentTrackerUI));

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

        filter = new JMenuItem(new FilterAction(entertainmentTrackerUI));
        sort = new JMenuItem(new SortAction(entertainmentTrackerUI));
        reset = new JMenuItem(new ResetViewAction(entertainmentTrackerUI));

        filter.setAccelerator(KeyStroke.getKeyStroke("control F"));
        sort.setAccelerator(KeyStroke.getKeyStroke("control O"));
        reset.setAccelerator(KeyStroke.getKeyStroke("control P"));

        viewMenu.add(filter);
        viewMenu.add(sort);
        viewMenu.add(reset);

        this.add(viewMenu, BorderLayout.PAGE_START);
    }

    public static JMenuItem getSave() {
        return save;
    }
}
