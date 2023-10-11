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
    // EFFECTS: returns the all media that is a given tag
    public Set<Media> whitelistTag(String tag) {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the all media that is NOT a given tag
    public Set<Media> blacklistTag(String tag) {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media entries matching the filter
    private Set<Media> filter(FilterListType listType, String tag) {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by progress percentage ascended
    public List<Media> sortProgressAscending() {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by progress percentage descended
    public List<Media> sortProgressDescending() {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by rating ascended
    public List<Media> sortRatingAscending() {
        return null; //stub
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by rating descended
    public List<Media> sortRatingDescending() {
        return null; //stub
    }

    private List<Media> sort(SortType type) {
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
