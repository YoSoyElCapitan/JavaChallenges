package main;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmileFacesTest {
    private static final List<String> FIRST = List.of(":)", ";(", ";}", ":-D");
    private static final int FIRST_COUNT = 2;

    private static final List<String> SECOND = List.of(";D", ":-(", ":-)", ";~)");
    private static final int SECOND_COUNT = 3;

    private static final List<String> THIRD = List.of(";]", ":[", ";*", ":$", ";-D");
    private static final int THIRD_COUNT = 1;

    @Test
    void countSmileys() {
        assertEquals(FIRST_COUNT, SmileFaces.countSmileys(FIRST));
        assertEquals(SECOND_COUNT, SmileFaces.countSmileys(SECOND));
        assertEquals(THIRD_COUNT, SmileFaces.countSmileys(THIRD));
    }

}