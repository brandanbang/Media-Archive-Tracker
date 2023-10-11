package ui;

import model.Archive;
import model.Media;

import java.util.List;
import java.util.Scanner;

// represents the Tracker application
public class EntertainmentTracker {
    private Scanner input;
    private Archive archive;

    // EFFECT: runs Tracker Application
    public EntertainmentTracker() {
        archive = new Archive();
        runTracker();
    }

    private void runTracker() {
        boolean running = true;
        String action;

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (running) {
            displayEntries();
            displayMenu();
            action = input.next().toLowerCase();

            if (action.equals("q")) {
                running = false;
                System.out.print("\nSession Ended");
            } else {
                actionManager(action);
            }
        }
    }

    // EFFECTS: displays the current display type of entries
    private void displayEntries() {
        List<Media> displayEntry = archive.getDisplayEntries();
        StringBuilder stb = new StringBuilder();
        for (Media m : displayEntry) {
            stb.append(m).append("\n");
        }
        System.out.println(stb);
    }

    // EFFECTS: displays possible actions to user
    private void displayMenu() {
        System.out.println("\nActions:");
        System.out.println("\tm | manage entries");
        System.out.println("\tf | filter");
        System.out.println("\ts | sort");
        System.out.println("\tq | quit");
    }

    // MODIFIES; this
    // EFFECTS: processes user action input
    private void actionManager(String action) {
        if (action.equals("f")) {
            new ui.FilterManager(archive);
        } else if (action.equals("s")) {
            new ui.SortManager(archive);
        } else if (action.equals("m")) {
            new ui.EntriesManager(archive);
        } else {
            System.out.println("Invalid Action... Try Again");
        }
    }
}
