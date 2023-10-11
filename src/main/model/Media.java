package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// abstract class for various types of media
public class Media {
    protected String title;
    protected Set<String> tags;
    protected float rating;
    protected int progress;
    protected int end;
    protected String notes;
    protected Archive archive;
    protected MediaType type;

    // REQUIRES: end marker > 0, already existing archive to hold media entry
    // EFFECTS: constructs and generates basic media components with given info
    //          with no tags, no notes, rating = -1, and no current progress
    public Media(String title, int end, Archive archive, MediaType type) {
        this.title = title;
        this.tags = new HashSet<>();
        this.rating = -1;
        this.progress = 0;
        this.end = end;
        this.notes = "";
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

    public float getRating() {
        return rating;
    }

    public int getProgress() {
        return progress;
    }

    public int getEnd() {
        return end;
    }

    public String getNotes() {
        return notes;
    }

    public Archive getArchive() {
        return archive;
    }
}
