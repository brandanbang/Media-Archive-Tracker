package model;

// represents a movie
public class Movie extends Media {

    public Movie(String title, int end, Archive archive) {
        super(title, end, archive);
        //stub
    }

    // EFFECTS: returns progress percentage of this media type
    @Override
    public float checkProgress() {
        return 0; //stub
    }
}

