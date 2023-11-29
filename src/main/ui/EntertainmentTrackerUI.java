package ui;

import exceptions.InvalidSave;
import model.Archive;
import model.Event;
import model.EventLog;
import model.Media;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tablecomponents.Table;
import ui.tablecomponents.TableManagerPopUp;
import ui.tablecomponents.TableModel;
import ui.topmenucomponents.TopMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

// class that manages the GUI app
public class EntertainmentTrackerUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/archive.json";
    private Archive archive;
    private JsonWriter writer;
    private JsonReader reader;

    private TableModel tableModel;

    // EFFECTS: creates the entertainment tracker and corresponding gui components
    public EntertainmentTrackerUI() {
        archive = new Archive();
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);

        JPanel jpanel = new JPanel();
        jpanel.addMouseListener(new DesktopFocusAction());
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
            EntertainmentTrackerUI.this.requestFocusInWindow();
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
        Table table = new Table(tableModel);
        TableManagerPopUp tableManagerPopUp = new TableManagerPopUp(this, table);
        JScrollPane tableScrollPane = new JScrollPane(table);
        this.add(tableScrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: loads info from archive to ui table
    public void refreshTable() {
        this.tableModel.setRowCount(0);
        for (Media m : archive.getDisplayEntries()) {

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

    // EFFECTS: saves archive to file
    public void save() {
        try {
            writer.open();
            writer.write(archive);
            writer.close();
        } catch (FileNotFoundException e) {
            new ErrorPopup(this, "Unable to save... check indicated file location");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads archive from file
    public void load() {
        try {
            archive = reader.read();
        } catch (InvalidSave is) {
            new ErrorPopup(this, "Unable to read... "
                    + "\ncheck indicated file location and content info");
        }
    }

    // MODIFIES: JSON file (archive.save)
    // EFFECTS: saves all current archive data to file
    // CITATION: idea from : https://stackoverflow.com/questions/15198549/popup-for-jframe-close-button
    private class SaveOnClose extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            TopMenu.getSave().doClick();
            printLog();
        }
    }

    public Archive getArchive() {
        return archive;
    }

    // EFFECTS: prints event log of archive to console/terminal
    private void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println("\n" + e.toString());
        }
    }
}
