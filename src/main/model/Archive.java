package model;

import java.util.Collections;
import java.util.List;
import java.util.Set;

// represents an overall archive
public class Archive {

    private Set<Media> entries;
    private Set<String> tags;

    // EFFECTS: constructs an archive with no used tags and empty entries of media
    public Archive() {
        //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media entries matching the filter
    public List<Media> filter(String type, String specification) {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by progress percentage
    public List<Media> sortProgress(float progressPercent) {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by rating
    public List<Media> sortRating(float rating) {
        return null; //stub
    }

    // REQUIRES: tag not already in list of tags
    // MODIFIES: this
    // EFFECTS: adds given tag to list of tags
    public void addTag(String tag) {
        //stub
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    public Set<Media> getEntries() {
        return Collections.unmodifiableSet(this.entries);
    }
}
