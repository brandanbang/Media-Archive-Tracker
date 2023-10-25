package persistence;

import model.Archive;
import model.Media;
import model.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Json Representation of archive from file to program
// CITATION: modeled after JsonSerializationDemo JsonReader
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Archive read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseArchive(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses and returns archive from JSON object
    private Archive parseArchive(JSONObject jsonObject) {
        Archive archive = new Archive();
        addMedias(archive, jsonObject);
        return archive;
    }

    // MODIFIES: archive
    // EFFECTS: parses the medias from JSON and adds to given archive
    private void addMedias(Archive archive, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject next = (JSONObject) json;
            addMedia(archive, next);
        }
    }

    // MODIFIES: archive
    // EFFECTS: parses media from JSON and adds to given archive
    private void addMedia(Archive archive, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        float rating = jsonObject.getFloat("rating");
        int progress = jsonObject.getInt("progress");
        int end = jsonObject.getInt("end");
        MediaType type = MediaType.valueOf(jsonObject.getString("type"));

        Media media = new Media(title, end, archive, type);
        media.updateRating(rating);
        addTags(media,jsonObject);
        media.updateProgress(progress);
        archive.addEntry(media);
    }

    // MODIFIES: media
    // EFFECTS: parses tags from JSON and adds it to the given media
    private void addTags(Media media, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        for (int i = 0; i < jsonArray.length(); i++) {
            media.addTag(jsonArray.getString(i));
        }
    }
}
