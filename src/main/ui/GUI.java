package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private EntertainmentTracker et;
    private JPanel jpanel;

    public GUI() {
//        this.et = new EntertainmentTracker();
        this.jpanel = new JPanel();
        this.jpanel.addMouseListener(new DesktopFocusAction());

        setContentPane(jpanel);
        setTitle("Entertainment Tracker");
        setSize(WIDTH, HEIGHT);



        //todo: add elements here

        addMenu();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // represents an action to be taken when user clicks
    // CITATION: AlarmController
    private class DesktopFocusAction extends MouseAdapter {
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
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('S');
        addMenuItem(fileMenu, new SaveAction(),
                KeyStroke.getKeyStroke("control S"));
        addMenuItem(fileMenu, new LoadAction(), KeyStroke.getKeyStroke("control L"));
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Adds an item with given handler to the given menu
     * @param theMenu  menu to which new item is added
     * @param action   handler for new menu item
     * @param accelerator    keystroke accelerator for this menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }
}
