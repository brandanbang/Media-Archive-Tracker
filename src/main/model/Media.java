package model;

import java.util.Collections;
import java.util.Set;

// abstract class for various types of media
public abstract class Media {
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
        //stub
    }

    // EFFECTS: returns progress of this media type
    public abstract float checkProgress();

    // REQUIRES: 0 < progress < end marker
    public void updateProgress(int progress) {
        //stub
    }

    // REQUIRES: 0 < given rating < 10, given rating must be at most 1 decimal digit
    // MODIFIES: this
    // EFFECTS: updates the rating for this media
    public void updateRating(float rating) {
        //stub
    }

    // REQUIRES: tag not already assigned
    // MODIFIES: this, archive
    // EFFECTS: if given tag is already exists in archive,
    //              add tag to this
    //          if tag is not being used,
    //              add tag to this and to archive list
    public void addTag(String tag) {
        //stub
    }

    // REQUIRES: tag already assigned
    // MODIFIES: this
    // EFFECTS: removes given tag from this
    public void removeTag(String tag) {
        //stub
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
