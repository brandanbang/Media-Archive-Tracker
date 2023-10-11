package ui;

import exceptions.InvalidSelection;
import model.Archive;
import model.Media;
import model.MediaType;

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
        } else {
            System.out.println("Invalid Action... try again");
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
                System.out.println("\nEnd Marker (num episodes, chapters etc: ");
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
