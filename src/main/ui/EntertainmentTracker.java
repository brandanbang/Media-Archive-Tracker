package ui;

import exceptions.InvalidSave;
import model.Archive;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;

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
