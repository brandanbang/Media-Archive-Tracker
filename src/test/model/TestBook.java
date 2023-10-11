package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBook {

    Archive testArchive;
    Media b0;
    Media b1;

    @BeforeEach
    void runBefore() {
        testArchive = new Archive();
        b0 = new Book("Book 0", 10, testArchive);
        b1 = new Book("Book 1", 100, testArchive);
        initializeB1();
    }

    private void initializeB1() {
        b1.addTag("a1");
        b1.addTag("a2");
        b1.addTag("a3");
    }

    @Test
    void testConstructor() {
        assertEquals("Book 0", b0.getTitle());
        assertEquals(10, b0.getEnd());
        assertEquals(testArchive, b0.getArchive());
        assertTrue(b0.getTags().isEmpty());
        assertEquals("", b0.getNotes());
        assertEquals(0, b0.getProgress());
        assertEquals(-1 , b0.getRating());
    }

    @Test
    void testCheckProgress() {
        b0.updateProgress(3);
        assertEquals(0.3f, b0.checkProgress());
    }

    @Test
    void testAddTagAlreadyExists() {
        testArchive.addTag("horror");
        assertTrue(testArchive.getTags().contains("horror"));
        assertEquals(1, testArchive.getTags().size());
        b0.addTag("horror");
        assertTrue(b0.getTags().contains("horror"));
        assertEquals(1, testArchive.getTags().size());
    }

    @Test
    void testAddTagNotExist() {
        assertFalse(testArchive.getTags().contains("horror"));
        b0.addTag("horror");
        assertTrue(b0.getTags().contains("horror"));
        assertTrue(testArchive.getTags().contains("horror"));
        assertEquals(1, b0.getTags().size());
        assertEquals(1, testArchive.getTags().size());
    }

    @Test
    void testAddTagMultiExists() {
        testArchive.addTag("horror");
        testArchive.addTag("action");
        assertTrue(testArchive.getTags().contains("horror"));
        assertTrue(testArchive.getTags().contains("action"));
        assertEquals(2, testArchive.getTags().size());
        b0.addTag("horror");
        b0.addTag("action");
        assertTrue(b0.getTags().contains("horror"));
        assertTrue(b0.getTags().contains("action"));
        assertEquals(2, testArchive.getTags().size());
    }

    @Test
    void testAddTagMultiNotExists() {
        assertFalse(testArchive.getTags().contains("horror"));
        assertFalse(testArchive.getTags().contains("action"));
        b0.addTag("horror");
        b0.addTag("action");
        assertTrue(b0.getTags().contains("horror"));
        assertTrue(b0.getTags().contains("action"));
        assertTrue(testArchive.getTags().contains("horror"));
        assertTrue(testArchive.getTags().contains("action"));
        assertEquals(2, b0.getTags().size());
        assertEquals(2, testArchive.getTags().size());
    }

    @Test
    void testRemoveTag() {
        assertTrue(b1.getTags().contains("a1"));
        assertEquals(3, b1.getTags().size());

        b1.removeTag("a1");

        assertFalse(b1.getTags().contains("a1"));
        assertEquals(2, b1.getTags().size());
    }

    @Test
    void testRemoveTagMulti() {
        assertTrue(b1.getTags().contains("a1"));
        assertTrue(b1.getTags().contains("a2"));
        assertEquals(3, b1.getTags().size());

        b1.removeTag("a1");
        b1.removeTag("a2");

        assertFalse(b1.getTags().contains("a1"));
        assertFalse(b1.getTags().contains("a2"));
        assertEquals(1, b1.getTags().size());
    }
}
