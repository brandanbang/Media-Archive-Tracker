package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteTest {

    Quote q0;
    Quote q1;

    @BeforeEach
    void runBefore() {
        q0 = new Quote("it is what it is", "Some person");
        q1 = new Quote("Summoners War is an E-Sport", "Com2Us", "Every SWC");
    }

    @Test
    void testFirstConstructor() {
        assertEquals("\"it is what it is\""
                + "Some person" + "\n"
                + "",
                q0.toString());
    }

    @Test
    void testSecondConstructor() {
        assertEquals("\"Summoners War is an E-Sport\""
                        + "Com2Us" + "\n"
                        + "Every SWC",
                q0.toString());
    }
}
