package ui;

import exceptions.InvalidSave;
import model.Archive;
import model.Media;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.List;

// represents the Tracker application
public class EntertainmentTracker {
    private static final String JSON_STORE = "./data/archive.json";
    protected Archive archive;
    protected JsonWriter writer;
    protected JsonReader reader;

    // EFFECT: runs Tracker Application
    public EntertainmentTracker() {
        archive = new Archive();
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
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


//    // MODIFIES; this
//    // EFFECTS: processes user action input
//    public void actionManager(String action) {
//        if (action.equals("f")) {
//            new FilterManager(archive);
//        } else if (action.equals("s")) {
//            new SortManager(archive);
//        } else if (action.equals("m")) {
//            new EntriesManager(archive);
//        } else if (action.equals("sa")) {
//            save();
//        } else if (action.equals("lo")) {
//            load();
//        } else {
//            System.out.println("Invalid Action... Try Again");
//        }
//    }

    // EFFECTS: saves archive to file
    public void save() {
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
    public void load() {
        try {
            archive = reader.read();
            System.out.println("Loaded archive from save");
        } catch (InvalidSave is) {
            System.out.println("Unable to read... check indicated file location and content info");
        }
    }
}
