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
    public void resetFilters() {
        EventLog.getInstance().logEvent(new Event("Filters reset"));
        this.displayEntries = filter(false, "");
    }

    // EFFECTS: returns the all media that is a given tag
    public void whitelistTag(String tag) {
        EventLog.getInstance().logEvent(new Event("Whitelisted for " + tag));
        this.displayEntries = filter(true, tag);
    }

    // EFFECTS: returns the all media that is NOT a given tag
    public void blacklistTag(String tag) {
        EventLog.getInstance().logEvent(new Event("Blacklisted for " + tag));
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
        EventLog.getInstance().logEvent(new Event("Sorted by Progress Percent - Ascending"));
        this.displayEntries = sort(true, PROGRESS);
    }

    // EFFECTS: returns the list of media sorted by progress percentage descended
    public void sortProgressDescending() {
        EventLog.getInstance().logEvent(new Event("Sorted by Progress Percent - Descending"));
        this.displayEntries = sort(false, PROGRESS);
    }

    // EFFECTS: returns the list of media sorted by rating ascended
    public void sortRatingAscending() {
        EventLog.getInstance().logEvent(new Event("Sorted by Rating - Ascending"));
        this.displayEntries = sort(true, RATING);
    }

    // EFFECTS: returns the list of media sorted by rating descended
    public void sortRatingDescending() {
        EventLog.getInstance().logEvent(new Event("Sorted by Rating - Descending"));
        this.displayEntries = sort(false, RATING);
    }

    // EFFECTS: returns the list of media sorted by rating ascended
    public void sortTitleAscending() {
        EventLog.getInstance().logEvent(new Event("Sorted by Title - Ascending"));
        this.displayEntries = sort(true, TITLE);
    }

    // EFFECTS: returns the list of media sorted by rating descended
    public void sortTitleDescending() {
        EventLog.getInstance().logEvent(new Event("Sorted by Title - Descending"));
        this.displayEntries = sort(false, TITLE);
    }

    // REQUIRES: sort type modifier must be an existing category
    // EFFECTS: sorts and returns a sorted list based off modifier
    private List<Media> sort(boolean ascending, SortType type) {
        List<Media> sortedSet = new ArrayList<>(this.displayEntries);
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
        EventLog.getInstance().logEvent(new Event("Tag: " + tag + " added"));
        this.tags.add(tag);
    }

    // MODIFIES: this
    // EFFECTS: adds given entry to archive
    public void addEntry(Media entry) {
        EventLog.getInstance().logEvent(new Event("Added " + entry.getType() + " Entry: "
                + entry.getTitle()
                + " with end marker " + entry.getEnd()));
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
                EventLog.getInstance().logEvent(new Event("Removed Entry: " + entry.getTitle()));
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

    // EFFECTS: returns an unmodifiable set of the used tags
    public Set<String> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    // EFFECTS: returns an unmodifiable list of entries
    public List<Media> getEntries() {
        return Collections.unmodifiableList(this.entries);
    }

    public List<Media> getDisplayEntries() {
        return this.displayEntries;
    }
}
