package model;

import java.util.*;

import static model.SortType.PROGRESS;
import static model.SortType.RATING;

// represents an overall archive
public class Archive {

    private Set<Media> entries;
    private Set<String> tags;
    private List<Media> displayEntries;

    // EFFECTS: constructs an archive with no used tags and empty entries of media
    public Archive() {
        this.entries = new HashSet<>();
        this.tags = new HashSet<>();
        this.displayEntries = new ArrayList<>();
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the all media that is a given tag
    public void whitelistTag(String tag) {
        this.displayEntries = filter(true, tag);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the all media that is NOT a given tag
    public void blacklistTag(String tag) {
        this.displayEntries = filter(false, tag);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media entries matching the filter
    private List<Media> filter(boolean whitelist, String tag) {
        List<Media> filteredList = new ArrayList<>();
        if (whitelist) {
            for (Media m : entries) {
                if (m.getTags().contains(tag)) {
                    filteredList.add(m);
                }
            }
        } else {
            for (Media m : entries) {
                if (!m.getTags().contains(tag)) {
                    filteredList.add(m);
                }
            }
        }
        return filteredList;
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by progress percentage ascended
    public void sortProgressAscending() {
        this.displayEntries = sort(true, PROGRESS);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by progress percentage descended
    public void sortProgressDescending() {
        this.displayEntries = sort(false, PROGRESS);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by rating ascended
    public void sortRatingAscending() {
        this.displayEntries = sort(true, RATING);
    }

    // REQUIRES: entries must have at least one entry
    // EFFECTS: returns the list of media sorted by rating descended
    public void sortRatingDescending() {
        this.displayEntries = sort(false, RATING);
    }

    private List<Media> sort(boolean ascending, SortType type) {
        List<Media> sortedSet = new ArrayList<>(entries);
        if (type == PROGRESS) {
            sortedSet.sort(Comparator.comparing(Media::checkProgress));
        } else if (type == RATING) {
            sortedSet.sort(Comparator.comparing(Media::getRating));
        }
        if (!ascending) {
            Collections.reverse(sortedSet);
        }
        return sortedSet;
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

    public List<Media> getDisplayEntries() {
        return this.displayEntries;
    }

    //todo
    @Override
    public String toString() {
        return super.toString();
    }
}
