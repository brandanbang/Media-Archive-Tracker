package persistence;

import exceptions.InvalidSelection;
import model.Archive;
import model.Media;
import model.MediaType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidPortfolioFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyArchive() {
        try {
            Archive a = new Archive();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyArchive.json");
            writer.open();
            writer.write(a);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyArchive.json");
            a = reader.read();

            Set<String> tags = new HashSet<>();
            assertTrue(a.getEntries().isEmpty());
            assertEquals(tags, a.getTags());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralArchive() {
        try {
            Archive a = new Archive();
            Media Saw = new Media("Saw", 2, a, MediaType.MOVIE);
            Saw.updateRating(3.14f);
            Saw.addTag("scary");
            Saw.updateProgress(1);
            a.addEntry(Saw);

            Media bocchi = new Media("Bocchi the Rock", 12, a, MediaType.SERIES);
            bocchi.updateRating(10);
            bocchi.addTag("fun");
            bocchi.updateProgress(12);
            a.addEntry(bocchi);

            JsonWriter writer = new JsonWriter("./data/testWriterArchive.json");
            writer.open();
            writer.write(a);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterArchive.json");
            a = reader.read();

            List<Media> entries = a.getEntries();
            assertEquals(2, entries.size());

            Set<String> tags = new HashSet<>();
            tags.add("scary");
            tags.add("fun");
            assertEquals(2, tags.size());

            Set<String> tags1 = new HashSet<>();
            tags1.add("scary");
            Set<String> tags2 = new HashSet<>();
            tags2.add("fun");

            checkMedia("Saw", tags1, 3.14f, 1, 2, MediaType.MOVIE, entries.get(0));
            checkMedia("Bocchi the Rock", tags2, 10f, 12, 12, MediaType.SERIES, entries.get(1));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (InvalidSelection is) {
            fail("InvalidSelection should not have been thrown");
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
