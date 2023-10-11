package model;

import static model.MediaType.BOOK;

// represents a book or novel-like media piece
public class Book extends Media {

    public Book(String title, int end, Archive archive) {
        super(title, end, archive, BOOK);
        //stub
    }

    // EFFECTS: returns progress percentage of this media type
    @Override
    public float checkProgress() {
        return 0; //stub
    }

    // REQUIRES: 0 < progress < end marker
    public void updateProgress(int progress) {
        super.updateProgress(progress);
    }

    // REQUIRES: 0 < given rating < 10, given rating must be at most 1 decimal digit
    // MODIFIES: this
    // EFFECTS: updates the rating for this media
    public void updateRating(float rating) {
        super.updateRating(rating);
    }
}
