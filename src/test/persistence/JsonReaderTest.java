package persistence;

import exceptions.InvalidSave;
import model.Archive;
import model.Media;
import model.MediaType;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/notRealFile.json");
        try {
            reader.read();
            fail("InvalidSave expected");
        } catch (InvalidSave e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyArchive() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyArchive.json");
        try {
            Archive a = reader.read();
            Set<String> tags = new HashSet<>();
            assertTrue(a.getEntries().isEmpty());
            assertEquals(tags, a.getTags());
        } catch (InvalidSave e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderArchive() {
        JsonReader reader = new JsonReader("./data/testReaderArchive.json");
        try {
            Archive a = reader.read();

            List<Media> entries = a.getEntries();
            assertEquals(2, entries.size());

            Set<String> tags = new HashSet<>();
            tags.add("sad");
            tags.add("scary");
            assertEquals(2, tags.size());

            Set<String> tags1 = new HashSet<>();
            tags1.add("sad");
            Set<String> tags2 = new HashSet<>();
            tags2.add("scary");

            checkMedia("Pokemon Movie", tags1, 10f, 1, 1, MediaType.MOVIE, entries.get(0));
            checkMedia("Escape Room", tags2, 5f, 2, 5, MediaType.SERIES, entries.get(1));
        } catch (InvalidSave e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInvalidSaveArchive() {
        JsonReader reader = new JsonReader("./data/testReaderInvalidSaveArchive.json");
        try {
            reader.read();
            fail("Expected Exception not thrown");
        } catch (InvalidSave e) {
            //pass - Progress of first media > end
        }
    }
}
