package model;

import java.util.*;

import static model.SortType.PROGRESS;
import static model.SortType.RATING;

// represents an overall archive
public class Archive {

    private Set<Media> entries;
    private Set<String> tags;

    // EFFECTS: constructs an archive with no used tags and empty entries of media
    public Archive() {
        this.entries = new HashSet<>();
        this.tags = new HashSet<>();
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the all media that is a given tag
    public Set<Media> whitelistTag(String tag) {
        return filter(true, tag);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the all media that is NOT a given tag
    public Set<Media> blacklistTag(String tag) {
        return filter(false, tag);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media entries matching the filter
    private Set<Media> filter(boolean whitelist, String tag) {
        Set<Media> filteredSet = new HashSet<>();
        if (whitelist) {
            for (Media m : entries) {
                if (m.getTags().contains(tag)) {
                    filteredSet.add(m);
                }
            }
        } else {
            for (Media m : entries) {
                if (!m.getTags().contains(tag)) {
                    filteredSet.add(m);
                }
            }
        }
        return filteredSet;
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by progress percentage ascended
    public List<Media> sortProgressAscending() {
        return sort(true, PROGRESS);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by progress percentage descended
    public List<Media> sortProgressDescending() {
        return sort(false, PROGRESS);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by rating ascended
    public List<Media> sortRatingAscending() {
        return sort(true, RATING);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by rating descended
    public List<Media> sortRatingDescending() {
        return sort(false, RATING);
    }

    private List<Media> sort(boolean ascending, SortType type) {
        List<Media> filteredSet = new ArrayList<>(entries);
        if (type == PROGRESS) {
            filteredSet.sort(Comparator.comparing(Media::checkProgress));
        } else if (type == RATING) {
            filteredSet.sort(Comparator.comparing(Media::getRating));
        }
        if (!ascending) {
            Collections.reverse(filteredSet);
        }
        return filteredSet;
    }

    // REQUIRES: tag not already in list of tags
    // MODIFIES: this
    // EFFECTS: adds given tag to list of tags
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    // MODIFIES: this
    // EFFECTS: adds given entry to archive
    public void addEntry(Media entry) {
        this.entries.add(entry);
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    public Set<Media> getEntries() {
        return Collections.unmodifiableSet(this.entries);
    }
}
