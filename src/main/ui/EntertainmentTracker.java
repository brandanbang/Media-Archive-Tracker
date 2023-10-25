package ui;

import model.Archive;
import model.Media;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// represents the Tracker application
public class EntertainmentTracker {
    private static final String JSON_STORE = "./data/archive.json";
    private Scanner input;
    private Archive archive;
    private JsonWriter writer;
    private JsonReader reader;

    // EFFECT: runs Tracker Application
    public EntertainmentTracker() {
        archive = new Archive();
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
        runTracker();
    }

    // EFFECTS: prompts user action and menu
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
                savePrompt();
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
        System.out.println("\tsa | save");
        System.out.println("\tlo | load");
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
        } else if (action.equals("sa")) {
            save();
        } else if (action.equals("lo")) {
            load();
        } else {
            System.out.println("Invalid Action... Try Again");
        }
    }

    // EFFECTS: saves archive to file
    private void save() {
        try {
            writer.open();
            writer.write(archive);
            writer.close();
            System.out.println("Archive Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save... check indicated file location");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads archive from file
    private void load() {
        try {
            archive = reader.read();
            System.out.println("Loaded archive from save");
        } catch (IOException e) {
            System.out.println("Unable to read... check indicated file location");
        }
    }

    // EFFECTS: prompts user on whether they want to save
    private void savePrompt() {
        System.out.println("Would you like to save your changes?\n Any unsaved progress will be lost. (y|n)\n");
        String action = input.next().toLowerCase();
        if (action.equals("y") || action.equals("yes")) {
            save();
        }
    }
}
