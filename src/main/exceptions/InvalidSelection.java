package exceptions;

// represents exception where user inputs an invalid entry
public class InvalidSelection extends Exception {
    public InvalidSelection(String errorMessage) {
        super(errorMessage);
    }
}
