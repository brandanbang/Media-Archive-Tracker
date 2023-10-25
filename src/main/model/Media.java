package model;

import org.json.JSONObject;
import persistence.MakeJsonType;

import java.util.*;

// abstract class for various types of media
public class Media implements MakeJsonType {
    protected String title;
    protected Set<String> tags;
    protected float rating;
    protected int progress;
    protected int end;
    protected Archive archive;
    protected MediaType type;

    // REQUIRES: end marker > 0, already existing archive to hold media entry
    // EFFECTS: constructs and generates basic media components with given info
    //          with no tags, rating = -1, and no current progress
    public Media(String title, int end, Archive archive, MediaType type) {
        this.title = title;
        this.tags = new HashSet<>();
        this.rating = -1;
        this.progress = 0;
        this.end = end;
        this.archive = archive;
        this.type = type;
    }

    // EFFECTS: returns progress percentage of this media type
    public double checkProgress() {
        return Math.round((float) progress / end * 100);
    }

    // REQUIRES: 0 < progress < end marker
    public void updateProgress(int progress) {
        this.progress = progress;
    }

    // REQUIRES: 0 < given rating < 10, given rating must be at most 1 decimal digit
    // MODIFIES: this
    // EFFECTS: updates the rating for this media
    public void updateRating(float rating) {
        this.rating = rating;
    }

    // REQUIRES: tag not already assigned
    // MODIFIES: this, archive
    // EFFECTS: if given tag is already exists in archive,
    //              add tag to this
    //          if tag is not being used,
    //              add tag to this and to archive list
    public void addTag(String tag) {
        if (!archive.getTags().contains(tag)) {
            archive.addTag(tag);
        }
        this.tags.add(tag);
    }

    // REQUIRES: tag already assigned
    // MODIFIES: this
    // EFFECTS: removes given tag from this
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public String getRating() {
        return formatRating();
    }

    public int getProgress() {
        return progress;
    }

    public int getEnd() {
        return end;
    }

    public Archive getArchive() {
        return archive;
    }

    public MediaType getType() {
        return type;
    }

    // EFFECT: converts and formats details of info to String
    @Override
    public String toString() {
        return "\nname: " + this.title
                + "\ntype: " + this.type
                + "\ntags: " + tagsToString()
                + "\nrating: " + formatRating()
                + "\nprogress: " + this.progress
                + "\nend: " + this.end
                + "\nprogress percent: " + checkProgress();
    }

    // EFFECTS: formats tags to a comma separate list without []
    private String tagsToString() {
        StringBuilder stb = new StringBuilder();
        for (String tag : this.tags) {
            stb.append(tag).append(", ");
        }
        return stb.toString();
    }

    // EFFECTS: returns the formatted String for -1 as no rating
    private String formatRating() {
        if (rating == -1) {
            return "no rating";
        } else {
            return String.valueOf(rating);
        }
    }

    // MODIFIES: json object
    // EFFECTS: returns a JSON array object with associated Media info
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("tags", new ArrayList<>(this.tags));
        json.put("rating", this.rating);
        json.put("progress", this.progress);
        json.put("end", this.end);
        json.put("type", this.type);

        return json;
    }
}
