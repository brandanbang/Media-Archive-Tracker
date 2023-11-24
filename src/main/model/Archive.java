package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.MakeJsonType;

import java.util.*;

import static model.SortType.*;

// represents an overall archive
public class Archive implements MakeJsonType {
    private List<Media> entries;
    private Set<String> tags;
    private List<Media> displayEntries;

    // EFFECTS: constructs an archive with given name, no used tags and empty entries of media
    public Archive() {
        this.entries = new ArrayList<>();
        this.tags = new HashSet<>();
        this.displayEntries = new ArrayList<>();
    }

    // EFFECTS: returns the all media that is a given tag
    public void whitelistTag(String tag) {
        this.displayEntries = filter(true, tag);
    }

    // EFFECTS: returns the all media that is NOT a given tag
    public void blacklistTag(String tag) {
        this.displayEntries = filter(false, tag);
    }

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

    // EFFECTS: returns the list of media sorted by progress percentage ascended
    public void sortProgressAscending() {
        this.displayEntries = sort(true, PROGRESS);
    }

    // EFFECTS: returns the list of media sorted by progress percentage descended
    public void sortProgressDescending() {
        this.displayEntries = sort(false, PROGRESS);
    }

    // EFFECTS: returns the list of media sorted by rating ascended
    public void sortRatingAscending() {
        this.displayEntries = sort(true, RATING);
    }

    // EFFECTS: returns the list of media sorted by rating descended
    public void sortRatingDescending() {
        this.displayEntries = sort(false, RATING);
    }

    // EFFECTS: returns the list of media sorted by rating ascended
    public void sortTitleAscending() {
        this.displayEntries = sort(true, TITLE);
    }

    // EFFECTS: returns the list of media sorted by rating descended
    public void sortTitleDescending() {
        this.displayEntries = sort(false, TITLE);
    }

    // REQUIRES: sort type modifier must be an existing category
    // EFFECTS: sorts and returns a sorted list based off modifier
    private List<Media> sort(boolean ascending, SortType type) {
        List<Media> sortedSet = new ArrayList<>(entries);
        if (type == PROGRESS) {
            sortedSet.sort(Comparator.comparing(Media::checkProgress));
        }
        if (type == RATING) {
            sortedSet.sort(Comparator.comparing(Media::getRating));
        }
        if (type == TITLE) {
            sortedSet.sort(Comparator.comparing(Media::getTitle));
        }
        if (!ascending) {
            Collections.reverse(sortedSet);
        }
        return sortedSet;
    }

    // MODIFIES: this
    // EFFECTS: adds given tag to set of tags, if tag already exists, do nothing
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    // MODIFIES: this
    // EFFECTS: adds given entry to archive
    public void addEntry(Media entry) {
        this.entries.add(entry);
        this.displayEntries.add(entry);
    }

    // MODIFIES: this
    // EFFECTS: removes entry from archive based off given name and returns true
    //          if not exists, return false
    public boolean delEntry(Media entry) {
        for (Media m : entries) {
            if (m.equals(entry)) {
                entries.remove(m);
                displayEntries.remove(m);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: json object
    // EFFECTS: returns a JSON object with all accounts
    @Override
    public JSONObject convertToJson() {
        JSONObject object = new JSONObject();
        object.put("entries", mediasToJson());
        object.put("tags", tagsToJson());
        return object;
    }

    // EFFECTS: returns media entries in this archive as a JSON array
    private JSONArray mediasToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Media m : entries) {
            jsonArray.put(m.convertToJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns tags in this archive as a JSON array
    private JSONArray tagsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String tag : tags) {
            jsonArray.put(tag);
        }

        return jsonArray;
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    public List<Media> getEntries() {
        return Collections.unmodifiableList(this.entries);
    }

    public List<Media> getDisplayEntries() {
        return this.displayEntries;
    }
}
