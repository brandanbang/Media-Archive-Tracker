package model;

// represents a quote with the line, name and source if applicable
public class Quote {
    String quote;
    String name;
    String source;

    // EFFECT: constructs a completed quote with the quoted line and character that said it
    public Quote(String quote, String name) {
        this.quote = quote;
        this.name = name;
        this.source = "";
    }

    // EFFECT: constructs a completed quote with the quoted line, character that said it and when it was said
    public Quote(String quote, String name, String source) {
        this.quote = quote;
        this.name = name;
        this.source = source;
    }

    @Override
    public String toString() {
        return "\"" + quote + "\"\n"
                + name + "\n"
                + source;
    }
}
