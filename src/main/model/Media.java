package model;

import exceptions.InvalidSelection;
import exceptions.TagDoesNotExist;
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

    // REQUIRES: already existing archive to hold media entry
    // EFFECTS: if end marker <= 0, throw InvalidSelection
    //          constructs and generates basic media components with given info
    //          with no tags, rating = -1, and no current progress
    public Media(String title, int end, Archive archive, MediaType type) throws InvalidSelection {
        if (end <= 0) {
            throw new InvalidSelection("End Marker cannot be negative");
        }
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

    // EFFECTS: if progress < 0 or progress > end marker, throw InvalidSelection
    //          otherwise, change progress
    public void updateProgress(int progress) throws InvalidSelection {
        if (progress < 0) {
            throw new InvalidSelection("Progress cannot be negative.");
        } else if (progress > this.end) {
            throw new InvalidSelection("Progress cannot exceed the end marker.");
        } else {
            this.progress = progress;
        }
    }

    // MODIFIES: this
    // EFFECTS: if given rating < 0 or given rating > 10, throw InvalidSelection
    //          otherwise, updates the rating for this media
    public void updateRating(float rating) throws InvalidSelection {
        if ((rating < 0 || rating > 10) && (rating != -1)) {
            throw new InvalidSelection("Given Rating is outside the acceptable range [0, 10]");
        }
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECTS: if given end point <= 0, throw InvalidSelection
    //          otherwise, updates the end point
    public void updateEnd(int end) throws InvalidSelection {
        if (end <= 0) {
            throw new InvalidSelection("End Marker must be non positive");
        }

        this.end = end;
    }

    // MODIFIES: this, archive
    // EFFECTS: if given tag is already exists in archive,
    //              add tag to this media
    //          if tag is not being used,
    //              add tag to this media and to archive list
    public void addTag(String tag) {
        if (!archive.getTags().contains(tag)) {
            archive.addTag(tag);
        }
        this.tags.add(tag);
    }

    // MODIFIES: this
    // EFFECTS: if tag not assigned to this media, throw TagDoesNotExist
    //          otherwise, removes given tag from this media
    public void removeTag(String tag) throws TagDoesNotExist {
        if (!this.tags.contains(tag)) {
            throw new TagDoesNotExist();
        }
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

    // EFFECTS: returns true if the titles of medias match
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Media media = (Media) o;
        return Objects.equals(title, media.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
