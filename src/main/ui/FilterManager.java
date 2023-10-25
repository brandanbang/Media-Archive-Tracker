package ui;

import exceptions.CancelAction;
import exceptions.TagDoesNotExist;
import model.Archive;

import java.util.Scanner;

public class FilterManager {

    private Scanner input;
    private Archive archive;

    // EFFECT: runs filter options
    public FilterManager(Archive archive) {
        this.archive = archive;
        filter();
    }

    // MODIFIES: this
    // EFFECTS: manages user action for filtering
    private void filter() {
        boolean running = true;
        String action;

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (running) {
            filterActions();
            action = input.next().toLowerCase();

            if (action.equals("c")) {
                running = false;
                System.out.print("\nAction Cancelled");
            } else {
                processAction(action);
            }
        }
    }

    // EFFECTS: displays possible actions for filtering
    private void filterActions() {
        System.out.println("\nActions:");
        System.out.println("\tw | whitelist");
        System.out.println("\tb | blacklist");
        System.out.println("\tc | cancel");
    }

    // MODIFIES: this
    // EFFECTS: processes user action input
    private void processAction(String action) {
        if (action.equals("w")) {
            try {
                String tag = tagSelector();
                archive.whitelistTag(tag);
            } catch (CancelAction ca) {
                System.out.print("Cancel Filter");
            }
        } else if (action.equals("b")) {
            try {
                String tag = tagSelector();
                archive.blacklistTag(tag);
            } catch (CancelAction ca) {
                System.out.print("Cancel Filter");
            }
            System.out.println("filtered");
        } else {
            System.out.println("Invalid Action... try again");
        }
    }

    // EFFECTS: checks if tag is valid
    //          throws CancelAction if cancel option indicated
    private String tagSelector() throws CancelAction {
        String tag = "";
        try {
            System.out.print(archive.getTags() + "\n");
            System.out.println("Enter desired tag to filter: ");
            tag = input.next().toLowerCase();
            if (tag.equals("/c")) {
                throw new CancelAction();
            }
            if (!archive.getTags().contains(tag)) {
                throw new TagDoesNotExist();
            }
        } catch (TagDoesNotExist tdne) {
            System.out.println("Invalid Tag... Tag does not exist try again");
            tagSelector();
        }
        return tag;
    }
}
