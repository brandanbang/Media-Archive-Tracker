package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.MediaType.*;
import static org.junit.jupiter.api.Assertions.*;

class ArchiveTest {

    Archive a0;
    Archive a1;

    Media m0;
    Media m1;
    Media m2;

    @BeforeEach
    void runBefore() {
        a0 = new Archive();
        a1 = new Archive();
        initializeMedia();
        initializeA1();
    }

    private void initializeA1() {
        a1.addEntry(m0);
        a1.addEntry(m1);
        a1.addEntry(m2);
    }

    private void initializeMedia() {
        m0 = new Media("Book 0", 10, a1, BOOK);
        m1 = new Media("Movie 0", 10, a1, MOVIE);
        m2 = new Media("Series 0", 10, a1, SERIES);

        m0.addTag("thriller");
        m1.addTag("thriller");
        m2.addTag("comedy");

        m0.updateProgress(5);
        m1.updateProgress(9);

        m0.updateRating(3.1f);
        m1.updateRating(9.5f);
        m2.updateRating(7.3f);
    }

    @Test
    void testConstructor() {
        assertTrue(a0.getEntries().isEmpty());
        assertTrue(a0.getTags().isEmpty());
    }

    @Test
    void testAddEntry() {
        assertFalse(a0.getEntries().contains(m0));
        a0.addEntry(m0);
        assertTrue(a0.getEntries().contains(m0));
    }

    @Test
    void testAddEntryMulti() {
        assertFalse(a0.getEntries().contains(m0));
        assertFalse(a0.getEntries().contains(m1));
        a0.addEntry(m0);
        a0.addEntry(m1);
        assertTrue(a0.getEntries().contains(m0));
        assertTrue(a0.getEntries().contains(m1));
    }

    @Test
    void testWhitelistTag() {
        a1.whitelistTag("thriller");
        assertTrue(a1.getDisplayEntries().contains(m0));
        assertTrue(a1.getDisplayEntries().contains(m1));
        assertEquals(2, a1.getDisplayEntries().size());
    }

    @Test
    void testBlacklistTag() {
        a1.blacklistTag("thriller");
        assertTrue(a1.getDisplayEntries().contains(m2));
        assertEquals(1, a1.getDisplayEntries().size());
    }

    @Test
    void testSortProgressAsc() {
        a1.sortProgressAscending();
        assertEquals(m2, a1.getDisplayEntries().get(0));
        assertEquals(m0, a1.getDisplayEntries().get(1));
        assertEquals(m1, a1.getDisplayEntries().get(2));
    }

    @Test
    void testSortProgressDesc() {
        a1.sortProgressDescending();
        assertEquals(m1, a1.getDisplayEntries().get(0));
        assertEquals(m0, a1.getDisplayEntries().get(1));
        assertEquals(m2, a1.getDisplayEntries().get(2));
    }

    @Test
    void testSortRatingAsc() {
        a1.sortRatingAscending();
        assertEquals(m0, a1.getDisplayEntries().get(0));
        assertEquals(m2, a1.getDisplayEntries().get(1));
        assertEquals(m1, a1.getDisplayEntries().get(2));
    }

    @Test
    void testSortRatingDesc() {
        a1.sortRatingDescending();
        assertEquals(m1, a1.getDisplayEntries().get(0));
        assertEquals(m2, a1.getDisplayEntries().get(1));
        assertEquals(m0, a1.getDisplayEntries().get(2));
    }

    @Test
    void testDelEntry() {
        assertTrue(a1.getEntries().contains(m0));
        assertEquals(3, a1.getEntries().size());
        assertTrue(a1.delEntry("Book 0"));
        assertFalse(a1.getEntries().contains(m0));
        assertEquals(2, a1.getEntries().size());
    }

    @Test
    void testDelEntryMulti() {
        assertTrue(a1.getEntries().contains(m0));
        assertTrue(a1.getEntries().contains(m1));
        assertEquals(3, a1.getEntries().size());
        assertTrue(a1.delEntry("Book 0"));
        assertTrue(a1.delEntry("Movie 0"));
        assertFalse(a1.getEntries().contains(m0));
        assertFalse(a1.getEntries().contains(m1));
        assertEquals(1, a1.getEntries().size());
    }

    @Test
    void testDelEntryFalse() {
        assertEquals(3, a1.getEntries().size());
        assertFalse(a1.delEntry("does not exist"));
        assertEquals(3, a1.getEntries().size());
    }
}