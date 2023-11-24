package ui;

import model.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// class that manages the GUI app
public class GUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private EntertainmentTracker tracker;
    private JPanel jpanel;
    private Table table;
    private TableModel tableModel;
    private TableManagerPopUp tableManagerPopUp;

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
        TopMenu topMenu = new TopMenu(this);
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
            String media = m.toString();
            String[] parts = media.split("\n");

            String title = parts[1];
            String type = parts[2];
            String tags = parts[3];
            String rating = parts[4];
            String progress = parts[5];
            String end = parts[6];
            String progressPercent = parts[7];

            Object[] data = {title, type, rating, progressPercent, progress, end,
                    tags};
            this.tableModel.addRow(data);

            //todo: change implementation of to String method in MEDIA to not have the label with it
            //      OR add getters that also access the helper methods in the tostring method
        }
    }

    public EntertainmentTracker getTracker() {
        return tracker;
    }
}
