package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private static final int[][] ONE_BY_ON = new int[][]{
            {1}
    };

    private static final int ONE_BY_ON_DETERMINANT = 1;

    private static final int[][] TWO_BY_TWO = new int[][]{
            {1, 2},
            {3, 4}
    };

    private static final int TWO_BY_TWO_DETERMINANT = -2;

    private static final int[][] THREE_BY_THREE = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    private static final int THREE_BY_THREE_DETERMINANT = 0;

    private static final int[][] FOUR_BY_FOUR = new int[][]{
            {1, 3, 5, 9},
            {1, 3, 1, 7},
            {4, 3, 9, 7},
            {5, 2, 0, 9}
    };

    private static final int FOUR_BY_FOUR_DETERMINANT = -376;

    @Test
    void determinantOneByOne() {
        assertEquals(ONE_BY_ON_DETERMINANT, Matrix.determinant(ONE_BY_ON));
    }

    @Test
    void determinantTwoByTwo() {
        assertEquals(TWO_BY_TWO_DETERMINANT, Matrix.determinant(TWO_BY_TWO));
    }

    @Test
    void determinantThreeByThree() {
        assertEquals(THREE_BY_THREE_DETERMINANT, Matrix.determinant(THREE_BY_THREE));
    }

    @Test
    void determinantFourByFour() {
        assertEquals(FOUR_BY_FOUR_DETERMINANT, Matrix.determinant(FOUR_BY_FOUR));
    }
}