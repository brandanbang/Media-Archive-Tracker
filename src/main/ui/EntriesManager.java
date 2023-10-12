package ui;

import exceptions.InvalidSelection;
import model.Archive;
import model.Media;
import model.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EntriesManager {

    private Scanner input;
    private Archive archive;

    // EFFECT: runs sort options
    public EntriesManager(Archive archive) {
        this.archive = archive;
        manageEntries();
    }

    // MODIFIES: this
    // EFFECTS: manages user action for sorting
    private void manageEntries() {
        boolean running = true;
        String action;

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (running) {
            entriesActions();
            action = input.next().toLowerCase();

            if (action.equals("c")) {
                running = false;
                System.out.print("\nAction Cancelled");
            } else {
                processAction(action);
            }
        }
    }

    // EFFECTS: displays possible actions for sorting
    private void entriesActions() {
        System.out.println("\nActions:");
        System.out.println("\ta | add entry");
        System.out.println("\td | delete entry");
        System.out.println("\tt | tag entry");
        System.out.println("\tr | change rating");
        System.out.println("\tp | change progress");
        System.out.println("\tc | cancel");
    }

    // MODIFIES: this
    // EFFECTS: processes user action input
    private void processAction(String action) {
        if (action.equals("a")) {
            addEntry();
            System.out.println("added");
        } else if (action.equals("d")) {
            delEntry();
        } else if (action.equals("t")) {
            tagEntry();
            System.out.println("added tag");
        } else if (action.equals("r")) {
            changeRatingManager();
        } else if (action.equals("p")) {
            changeProgressManager();
        } else {
            System.out.println("Invalid Action... try again");
        }
    }

    // EFFECT: manages updating progress of selected a media
    private void changeProgressManager() {
        String title;
        List<String> entryNames = new ArrayList<>();

        while (true) {
            try {
                System.out.println("\nSelect entry: ");
                for (Media m : archive.getDisplayEntries()) {
                    System.out.printf(m.getTitle() + "\n");
                    entryNames.add(m.getTitle());
                }
                title = input.next().toLowerCase();

                if (!entryNames.contains(title)) {
                    throw new InvalidSelection();
                }

                Media matchMedia = matchTitle(title);
                int newProgress = getNewProgress(matchMedia.getEnd());
                matchMedia.updateProgress(newProgress);
                break;
            } catch (NumberFormatException | InvalidSelection e) {
                System.out.println("Invalid input... the entered title does not exist... try again");
            }
        }
    }

    // EFFECT: returns int representing the new progress
    private int getNewProgress(int end) {
        while (true) {
            try {
                System.out.println("\nUpdated Progress: ");
                int choice = Integer.parseInt(input.next());
                if ((choice <= 0) || (choice > end)) {
                    throw new InvalidSelection();
                }
                return choice;
            } catch (NumberFormatException | InvalidSelection e) {
                System.out.println("Invalid input... try again");
            }
        }
    }

    // EFFECTS: changes progress to media with given title
    private Media matchTitle(String title) {
        Media match = null;
        for (Media m : archive.getDisplayEntries()) {
            if (m.getTitle().equals(title)) {
                match = m;
            }
        }
        return match;
    }



    // EFFECTS: manages the process to change rating of media
    private void changeRatingManager() {
        float newRating = getNewRating();

        String title;
        List<String> entryNames = new ArrayList<>();

        while (true) {
            try {
                System.out.println("\nSelect entry: ");
                for (Media m : archive.getDisplayEntries()) {
                    System.out.printf(m.getTitle() + "\n");
                    entryNames.add(m.getTitle());
                }
                title = input.next().toLowerCase();

                if (!entryNames.contains(title)) {
                    throw new InvalidSelection();
                }
                changeRating(newRating, title);
                break;
            } catch (NumberFormatException | InvalidSelection e) {
                System.out.println("Invalid input... the entered title does not exist... try again");
            }
        }
    }

    // EFFECTS: adds given tag to media with given title
    private void changeRating(float rating, String title) {
        for (Media m : archive.getDisplayEntries()) {
            if (m.getTitle().equals(title)) {
                m.updateRating(rating);
            }
        }
    }

    // EFFECT: returns float representing the new rating
    private float getNewRating() {
        while (true) {
            try {
                System.out.println("\nNew Rating (0 - 10): ");
                float choice = Float.parseFloat(input.next());
                if (choice <= 0) {
                    throw new InvalidSelection();
                }
                return choice;
            } catch (NumberFormatException | InvalidSelection e) {
                System.out.println("Invalid input... try again");
            }
        }
    }

    // MODIFIES: this, archive
    // EFFECT: manages and adds tags to entries
    private void tagEntry() {
        System.out.print(archive.getTags() + "\n");
        System.out.print("Enter Tag\n");
        String tag = input.next().toLowerCase();
        String title;
        List<String> entryNames = new ArrayList<>();

        while (true) {
            try {
                System.out.println("\nSelect entry: ");
                for (Media m : archive.getDisplayEntries()) {
                    System.out.print(m.getTitle() + ", ");
                    entryNames.add(m.getTitle());
                }
                title = input.next().toLowerCase();

                if (!entryNames.contains(title)) {
                    throw new InvalidSelection();
                }
                addTag(tag, title);
                break;
            } catch (NumberFormatException | InvalidSelection e) {
                System.out.println("Invalid input... the entered title does not exist... try again");
            }
        }
    }

    // EFFECTS: adds given tag to media with given title
    private void addTag(String tag, String title) {
        for (Media m : archive.getDisplayEntries()) {
            if (m.getTitle().equals(title)) {
                m.addTag(tag);
            }
        }
    }

    // MODIFIES: this, archive
    // EFFECTS: manages the steps for making a new entry
    private void addEntry() {
        String title = getTitle();
        int end = getEndMarker();
        MediaType type = getMediaType();
        archive.addEntry(new Media(title, end, archive, type));
    }

    // MODIFIES: this, archive
    // EFFECTS: manages the steps for making a new entry
    private void delEntry() {
        String title = getTitle();
        if (archive.delEntry(title)) {
            System.out.print("successfully deleted");
        } else {
            System.out.print("entry with " + title + " does not exist");
        }
    }

    // EFFECTS: gets the title for entry
    private String getTitle() {
        System.out.println("\nTitle: ");
        return input.next().toLowerCase();
    }

    // EFFECTS: gets the end marker for the new entry
    private int getEndMarker() {
        while (true) {
            try {
                System.out.println("\nEnd Marker (num episodes, chapters etc): ");
                int choice = Integer.parseInt(input.next());
                if (choice <= 0) {
                    throw new InvalidSelection();
                }
                return choice;
            } catch (NumberFormatException | InvalidSelection e) {
                System.out.println("Invalid input... try again");
            }
        }
    }

    private MediaType getMediaType() {
        while (true) {
            try {
                System.out.println("\nMedia Type |b: BOOK||m: MOVIE||s: SERIES|: ");
                String choice = input.next().toLowerCase();
                if (Objects.equals(choice, "b")) {
                    return MediaType.BOOK;
                } else if (Objects.equals(choice, "m")) {
                    return MediaType.MOVIE;
                } else if (Objects.equals(choice, "s")) {
                    return MediaType.SERIES;
                } else {
                    throw new InvalidSelection();
                }
            } catch (InvalidSelection e) {
                System.out.println("Invalid input... try again");
            }
        }
    }
}
