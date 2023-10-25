package persistence;

import model.Archive;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents a writer that writes the JSON representation of Archive data to file
// CITATION: modeled after JsonSerializationDemo JsonWriter
public class JsonWriter {
    private static final int SPACER = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write into destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer;
    //          if destination file can not be opened for writing, throw FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(this.destination);
    }

    // MODIFIES: this
    // EFFECTS: writes a JSON representation of given archive to file
    public void write(Archive archive) {
        JSONObject jsonObject = archive.convertToJson();
        saveToFile(jsonObject.toString(SPACER));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
