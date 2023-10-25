package persistence;

import model.Media;
import model.MediaType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMedia(String title, Set<String> tags, Float rating, int progress, int end, MediaType type, Media m) {
        assertEquals(title, m.getTitle());
        assertEquals(tags, m.getTags());
        assertEquals(Float.toString(rating), m.getRating());
        assertEquals(progress, m.getProgress());
        assertEquals(end, m.getEnd());
        assertEquals(type, m.getType());
    }
}
