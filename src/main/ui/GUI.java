package ui;

import model.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// class that manages the GUI app
public class GUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private EntertainmentTracker tracker;
    private JPanel jpanel;
    private Table table;
    private TableModel tableModel;
    private TableManagerPopUp tableManagerPopUp;
    private TopMenu topMenu;

    // EFFECTS: creates the entertainment tracker and corresponding gui components
    public GUI() {
        this.tracker = new EntertainmentTracker();

        this.jpanel = new JPanel();
        this.jpanel.addMouseListener(new DesktopFocusAction());
        setContentPane(jpanel);
        setTitle("Entertainment Tracker");
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        initializeTopMenu();
        initializeTable();
        addWindowListener(new SaveOnClose());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // represents an action to be taken when user clicks
    // CITATION: AlarmController
    private class DesktopFocusAction extends MouseAdapter {
        // EFFECTS: sets the window focus to the mouse event
        @Override
        public void mouseClicked(MouseEvent e) {
            GUI.this.requestFocusInWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS: centres main window to desktop
    // CITATION: AlarmController
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: adds actions as a dropdown to the top of the window
    private void initializeTopMenu() {
        topMenu = new TopMenu(this);
        setJMenuBar(topMenu);
    }

    // MODIFIES: this
    // EFFECTS: sets up the table display for archive
    private void initializeTable() {
        tableModel = new TableModel();
        table = new Table(tableModel);
        tableManagerPopUp = new TableManagerPopUp(this, table);
        JScrollPane tableScrollPane = new JScrollPane(table);
        this.add(tableScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: loads info from archive to ui table
    void refreshTable() {
        this.tableModel.setRowCount(0);
        for (Media m : tracker.archive.getDisplayEntries()) {

            String title = m.getTitle();
            String type = String.valueOf(getType());
            String tags = m.getTags().toString();
            String rating = m.getRating();
            String progress = String.valueOf(m.getProgress());
            String end = String.valueOf(m.getEnd());
            String progressPercent = String.valueOf(m.checkProgress());


            Object[] data = {title, type, rating, progressPercent, progress, end,
                    tags};
            this.tableModel.addRow(data);
        }
    }

    public EntertainmentTracker getTracker() {
        return tracker;
    }


    // MODIFIES: JSON file (archive.save)
    // EFFECTS: saves all current archive data to file
    // CITATION: idea from : https://stackoverflow.com/questions/15198549/popup-for-jframe-close-button
    private class SaveOnClose extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            TopMenu.save.doClick();
        }
    }
}
