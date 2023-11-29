package exceptions;

// represents exception where user inputs an invalid entry
public class InvalidSelection extends Exception {
    // EFFECTS: creates an error with given error message
    public InvalidSelection(String errorMessage) {
        super(errorMessage);
    }
}
