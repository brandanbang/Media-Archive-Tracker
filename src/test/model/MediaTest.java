package model;

import exceptions.InvalidSelection;
import exceptions.TagDoesNotExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MediaType.*;
import static org.junit.jupiter.api.Assertions.*;

public class MediaTest {

    Archive testArchive;
    Media m0;
    Media m1;
    Media m2;

    @BeforeEach
    void runBefore() {
        try {
            testArchive = new Archive();
            m0 = new Media("Book 0", 10, testArchive, BOOK);
            m1 = new Media("Movie 1", 100, testArchive, MOVIE);
            initializeM1();
        } catch (InvalidSelection is) {
            fail("Invalid Selection in Media init");
        }
    }

    private void initializeM1() {
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
        assertEquals(0, m0.getProgress());
        assertEquals("no rating", m0.getRating());
    }

    @Test
    void testConstructorInvalidEnd() {
        try {
            m2 = new Media("Book 2", 0, testArchive, BOOK);
            fail("Invalid end; exception not thrown");
        } catch (InvalidSelection is) {
            //pass
        }
    }

    @Test
    void testCheckProgress() {
        try {
            m0.updateProgress(3);
            assertEquals(30, m0.checkProgress());
        } catch (InvalidSelection is) {
            fail("Unexpected InvalidSelection thrown");
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    void testCheckProgressInvalidLow() {
        try {
            m0.updateProgress(-1);
            fail("InvalidSelection NOT thrown");
        } catch (InvalidSelection is) {
            //pass
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    void testCheckProgressInvalidHigh() {
        try {
            m0.updateProgress(11);
            fail("InvalidSelection NOT thrown");
        } catch (InvalidSelection is) {
            //pass
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
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
        try {
            assertTrue(m1.getTags().contains("a1"));
            assertEquals(3, m1.getTags().size());

            m1.removeTag("a1");

            assertFalse(m1.getTags().contains("a1"));
            assertEquals(2, m1.getTags().size());
        } catch (TagDoesNotExist tdne) {
            fail("Unexpected TagDoesNotExist thrown");
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    void testRemoveTagDNE() {
        try {
            assertTrue(m1.getTags().contains("a1"));
            assertEquals(3, m1.getTags().size());

            m1.removeTag("Fake Tag");
            fail("TagDoesNotExist NOT thrown");
        } catch (TagDoesNotExist tdne) {
            //pass
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    void testRemoveTagMulti() {
        try {
            assertTrue(m1.getTags().contains("a1"));
            assertTrue(m1.getTags().contains("a2"));
            assertEquals(3, m1.getTags().size());

            m1.removeTag("a1");
            m1.removeTag("a2");

            assertFalse(m1.getTags().contains("a1"));
            assertFalse(m1.getTags().contains("a2"));
            assertEquals(1, m1.getTags().size());
        } catch (TagDoesNotExist tdne) {
            fail("Unexpected TagDoesNotExist thrown");
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    void testToStringNoRating() {
        assertEquals(stringBuilderHelperNoRating(), m1.toString());
    }

    private String stringBuilderHelperNoRating() {
        return "\nname: " + m1.title + "\ntype: " + m1.type + "\ntags: " +
                "a1, a2, a3, " + "\nrating: " + "no rating" + "\nprogress: " +
                m1.progress + "\nend: " + m1.end + "\nprogress percent: " +
                m1.checkProgress();
    }

    @Test
    void testUpdateRatingUnder() {
        try {
            m1.updateRating(-1);
            fail("Rating out of range, expect InvalidSelection");
        } catch (InvalidSelection is) {
            // pass
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    void testUpdateRatingOver() {
        try {
            m1.updateRating(11);
            fail("Rating out of range, expect InvalidSelection");
        } catch (InvalidSelection is) {
            //pass
        } catch (Exception e) {
            fail("Unexpected Exception thrown");
        }
    }

    @Test
    void testToStringRating() {
        try {
            m1.updateRating(2);
            assertEquals(stringBuilderHelperRating(), m1.toString());
        } catch (InvalidSelection is) {
            fail("Exception not expected");
        }
    }

    private String stringBuilderHelperRating() {
        return "\nname: " + m1.title + "\ntype: " + m1.type + "\ntags: " +
                "a1, a2, a3, " + "\nrating: " + 2f + "\nprogress: " +
                m1.progress + "\nend: " + m1.end + "\nprogress percent: " +
                m1.checkProgress();
    }
}
