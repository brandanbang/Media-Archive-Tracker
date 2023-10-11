package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestArchive {

    Archive a0;
    Archive a1;

    @BeforeEach
    void runBefore() {
        a0 = new Archive();
        a1 = new Archive();
    }

    private void initializeA1() {
        a1.addTag("thriller");
    }

    @Test
    void testConstructor() {
        assertTrue(a0.getEntries().isEmpty());
        assertTrue(a0.getTags().isEmpty());
    }

    @Test
    void testFilter() {

    }

}