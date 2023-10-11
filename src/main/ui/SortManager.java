package ui;

import model.Archive;

import java.util.Scanner;

public class SortManager {

    private Scanner input;
    private Archive archive;

    // EFFECT: runs sort options
    public SortManager(Archive archive) {
        this.archive = archive;
        sort();
    }

    // MODIFIES: this
    // EFFECTS: manages user action for sorting
    private void sort() {
        boolean running = true;
        String action;

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (running) {
            sortActions();
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
    private void sortActions() {
        System.out.println("\nActions:");
        System.out.println("\tpa | progress ascending");
        System.out.println("\tpd | progress descending");
        System.out.println("\tra | rating ascending");
        System.out.println("\trd | rating descending");
        System.out.println("\tc | cancel");
    }

    // MODIFIES: this
    // EFFECTS: processes user action input
    private void processAction(String action) {
        if (action.equals("pa")) {
            archive.sortProgressAscending();
            System.out.println("sorted");
        } else if (action.equals("pd")) {
            archive.sortProgressDescending();
            System.out.println("sorted");
        } else if (action.equals("ra")) {
            archive.sortRatingAscending();
            System.out.println("sorted");
        } else if (action.equals("rd")) {
            archive.sortRatingDescending();
            System.out.println("sorted");
        } else {
            System.out.println("Invalid Action... try again");
        }
    }
}
