package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConwaysGameOfLifeTest {
    // A glider moves diagonally (this especially moves south-east) infinitely, every fourth generation is the same with an offset in a diagonal direction.
    // Because the 2D space is infinite in all directions, the algorithm removes all the empty rows over and under, the empty columns to the left and right, thus it centers the objects after every generation.
    // Because of the centering mechanism, you can't see this movement, if all the objects move in the same direction relative to a given point of the space.
    private static final int[][] GLIDER = new int[][]{
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1}
    };

    private static final int[][] GLIDER_1 = new int[][]{
            {1, 0, 1},
            {0, 1, 1},
            {0, 1, 0}
    };

    private static final int[][] GLIDER_2 = new int[][]{
            {0, 0, 1},
            {1, 0, 1},
            {0, 1, 1}
    };

    private static final int[][] GLIDER_3 = new int[][]{
            {1, 0, 0},
            {0, 1, 1},
            {1, 1, 0}
    };

    // It starts with three oscillating objects, two of them collides after about ten generations, this collision crates a chain-reaction.
    // This reaction settles down in the 59th generation, and it never changes again.
    // By definition, if a cell grid does not change in generation 'n', it will not change in generation 'n+1' either.
    private static final int[][] LARGE = new int[][]{
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final int[][] LARGE_59 = new int[][]{
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
    };

    @Test
    void getGenerationGlider() {
        int[][] first = ConwaysGameOfLife.getGeneration(GLIDER, 1);
        int[][] second = ConwaysGameOfLife.getGeneration(first, 1);
        int[][] third = ConwaysGameOfLife.getGeneration(second, 1);
        int[][] fourth = ConwaysGameOfLife.getGeneration(third, 1);

        assertArrayEquals(GLIDER_1, first);
        assertArrayEquals(GLIDER_2, second);
        assertArrayEquals(GLIDER_3, third);
        assertArrayEquals(GLIDER, fourth);
    }

    @Test
    void getGenerationLarge() {
        int[][] fiftyNinth = ConwaysGameOfLife.getGeneration(LARGE, 59);
        int[][] sixtieth = ConwaysGameOfLife.getGeneration(fiftyNinth, 1);

        assertArrayEquals(LARGE_59, fiftyNinth);
        assertArrayEquals(LARGE_59, sixtieth);
    }
}