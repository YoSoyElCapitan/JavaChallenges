package main;

// Description:
//
//  Write a function that accepts a square matrix (N x N 2D array) and returns the determinant of the matrix.
//
//  How to take the determinant of a matrix -- it is simplest to start with the smallest cases:
//  A 1x1 matrix |a| has determinant a.
//
//  A 2x2 matrix [ [a, b], [c, d] ] or
//
//  |a  b|
//  |c  d|
//  has determinant: a*d - b*c.
//
//  The determinant of an n x n sized matrix is calculated by reducing the problem to the calculation of the determinants of n matrices ofn-1 x n-1 size.
//
//  For the 3x3 case, [ [a, b, c], [d, e, f], [g, h, i] ] or
//
//  |a b c|
//  |d e f|
//  |g h i|
//  the determinant is: a * det(a_minor) - b * det(b_minor) + c * det(c_minor) where det(a_minor) refers to taking the determinant of the 2x2 matrix created by crossing out the row and column in which the element a occurs:
//
//  |- - -|
//  |- e f|
//  |- h i|
//  Note the alternation of signs.
//
//  The determinant of larger matrices are calculated analogously, e.g. if M is a 4x4 matrix with first row [a, b, c, d], then:
//
//  det(M) = a * det(a_minor) - b * det(b_minor) + c * det(c_minor) - d * det(d_minor)
//
// Source: CodeWars
//  https://www.codewars.com/kata/52a382ee44408cea2500074c

public class Matrix {
    private Matrix() {
    }

    private static int[][] minor(final int[][] matrix, final int index) {
        int size = matrix.length;
        int x = 0;
        int y = 0;
        int[][] answer = new int[size - 1][size - 1];


        for (int i = 0; i < size; i++) {
            if (i == 0) {
                continue;
            }

            for (int j = 0; j < size; j++) {
                if (j == index) {
                    continue;
                }

                answer[x][y] = matrix[i][j];
                y++;
            }
            y = 0;
            x++;
        }

        return answer;
    }

    // The function name and parameters were given by CodeWars
    public static int determinant(int[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        } else {
            int sum = 0;

            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[0][i] * determinant(minor(matrix, i)) * (i % 2 == 0 ? 1 : -1);
            }

            return sum;
        }
    }
}
