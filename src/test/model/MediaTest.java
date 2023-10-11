package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MediaType.*;
import static org.junit.jupiter.api.Assertions.*;

public class MediaTest {

    Archive testArchive;
    Media m0;
    Media m1;

    @BeforeEach
    void runBefore() {
        testArchive = new Archive();
        m0 = new Media("Book 0", 10, testArchive, BOOK);
        m1 = new Media("Movie 1", 100, testArchive, MOVIE);
        initializeB1();
    }

    private void initializeB1() {
        m1.addTag("a1");
        m1.addTag("a2");
        m1.addTag("a3");
    }

    @Test
    void testConstructor() {
        assertEquals("Book 0", m0.getTitle());
        assertEquals(10, m0.getEnd());
        assertEquals(testArchive, m0.getArchive());
        assertTrue(m0.getTags().isEmpty());
        assertEquals("", m0.getNotes());
        assertEquals(0, m0.getProgress());
        assertEquals(-1 , m0.getRating());
    }

    @Test
    void testCheckProgress() {
        m0.updateProgress(3);
        assertEquals(30, m0.checkProgress());
    }

    @Test
    void testAddTagAlreadyExists() {
        testArchive.addTag("horror");
        assertTrue(testArchive.getTags().contains("horror"));
        assertEquals(4, testArchive.getTags().size());
        m0.addTag("horror");
        assertTrue(m0.getTags().contains("horror"));
        assertEquals(4, testArchive.getTags().size());
    }

    @Test
    void testAddTagNotExist() {
        assertFalse(testArchive.getTags().contains("horror"));
        m0.addTag("horror");
        assertTrue(m0.getTags().contains("horror"));
        assertTrue(testArchive.getTags().contains("horror"));
        assertEquals(1, m0.getTags().size());
        assertEquals(4, testArchive.getTags().size());
    }

    @Test
    void testAddTagMultiExists() {
        testArchive.addTag("horror");
        testArchive.addTag("action");
        assertTrue(testArchive.getTags().contains("horror"));
        assertTrue(testArchive.getTags().contains("action"));
        assertEquals(5, testArchive.getTags().size());
        m0.addTag("horror");
        m0.addTag("action");
        assertTrue(m0.getTags().contains("horror"));
        assertTrue(m0.getTags().contains("action"));
        assertEquals(5, testArchive.getTags().size());
    }

    @Test
    void testAddTagMultiNotExists() {
        assertFalse(testArchive.getTags().contains("horror"));
        assertFalse(testArchive.getTags().contains("action"));
        m0.addTag("horror");
        m0.addTag("action");
        assertTrue(m0.getTags().contains("horror"));
        assertTrue(m0.getTags().contains("action"));
        assertTrue(testArchive.getTags().contains("horror"));
        assertTrue(testArchive.getTags().contains("action"));
        assertEquals(2, m0.getTags().size());
        assertEquals(5, testArchive.getTags().size());
    }

    @Test
    void testRemoveTag() {
        assertTrue(m1.getTags().contains("a1"));
        assertEquals(3, m1.getTags().size());

        m1.removeTag("a1");

        assertFalse(m1.getTags().contains("a1"));
        assertEquals(2, m1.getTags().size());
    }

    @Test
    void testRemoveTagMulti() {
        assertTrue(m1.getTags().contains("a1"));
        assertTrue(m1.getTags().contains("a2"));
        assertEquals(3, m1.getTags().size());

        m1.removeTag("a1");
        m1.removeTag("a2");

        assertFalse(m1.getTags().contains("a1"));
        assertFalse(m1.getTags().contains("a2"));
        assertEquals(1, m1.getTags().size());
    }
}
