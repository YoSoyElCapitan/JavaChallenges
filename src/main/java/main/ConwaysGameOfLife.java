package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Description:
//
//  Given a 2D array and a number of generations, compute n timesteps of Conway's Game of Life.
//
//  The rules of the game are:
//      Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
//      Any live cell with more than three live neighbours dies, as if by overcrowding.
//      Any live cell with two or three live neighbours lives on to the next generation.
//      Any dead cell with exactly three live neighbours becomes a live cell.
//      Each cell's neighborhood is the 8 cells immediately around it (i.e. Moore Neighborhood). The universe is infinite in both the x and y dimensions and all cells are initially dead - except for those specified in the arguments. The return value should be a 2d array cropped around all of the living cells. (If there are no living cells, then return [[]].)
//
//  For illustration purposes, 0 and 1 will be represented as ░░ and ▓▓ blocks respectively (PHP, C: plain black and white squares). You can take advantage of the htmlize function to get a text representation of the universe, e.g.:
//  System.out.println(LifeDebug.htmlize(cells));
//
// Source: CodeWars
//  https://www.codewars.com/kata/52423db9add6f6fc39000354

class ConwaysGameOfLife {

    private ConwaysGameOfLife() {
    }

    private static class Points {
        private List<Point> pointList = new ArrayList<>();

        private Points(int[][] cells) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j] == 1) {
                        pointList.add(new Point(j, i));
                    }
                }
            }
        }

        private int[][] getArray() {
            final int minX = pointList.stream().min(Comparator.comparingInt(Point::getX)).orElse(new Point(0, 0)).getX();
            final int minY = pointList.stream().min(Comparator.comparingInt(Point::getY)).orElse(new Point(0, 0)).getY();

            final int maxX = pointList.stream().max(Comparator.comparingInt(Point::getX)).orElse(new Point(0, 0)).getX();
            final int maxY = pointList.stream().max(Comparator.comparingInt(Point::getY)).orElse(new Point(0, 0)).getY();

            int[][] array = new int[maxY - minY + 1][maxX - minX + 1];

            pointList.forEach(p -> array[p.getY() - minY][p.getX() - minX] = p.isAlive() ? 1 : 0);

            return array;
        }

        private int livingNeighboursCount(Point point) {
            return (int) pointList.stream().filter(p -> p.distance(point) < 1.5 && p.distance(point) > 0.0 && p.isAlive()).count();
        }

        private void extendWithNeighbours() {
            List<Point> alteredPointList = new ArrayList<>(this.pointList);
            this.pointList.forEach((Point point) -> {
                final int x = point.getX();
                final int y = point.getY();

                for (int xOff = -1; xOff <= 1; xOff++) {
                    for (int yOff = -1; yOff <= 1; yOff++) {
                        final Point possible = new Point(x + xOff, y + yOff, false);

                        if (alteredPointList.stream().noneMatch(p -> p.equalByCoordinates(possible))) {
                            alteredPointList.add(possible);
                        }
                    }
                }
            });

            this.pointList = alteredPointList;
        }

        private void removeZeroes() {
            pointList = pointList.stream().filter(Point::isAlive).toList();
        }

        private void nextGeneration() {
            extendWithNeighbours();
            pointList.forEach(p -> {
                final int count = livingNeighboursCount(p);
                if (p.isAlive() && (count > 3 || count < 2)) {
                    p.die();
                } else if (!p.isAlive() && count == 3) {
                    p.live();
                }
            });

            pointList.forEach(Point::setAlive);

            removeZeroes();
        }

    }

    private static class Point {

        private final int x;
        private final int y;
        private boolean isAlive;
        private boolean nextState;

        private Point(int x, int y, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.isAlive = isAlive;
            this.nextState = isAlive;
        }

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
            isAlive = true;
            nextState = true;
        }

        private int getX() {
            return x;
        }

        private int getY() {
            return y;
        }

        private boolean isAlive() {
            return isAlive;
        }

        private void live() {
            nextState = true;
        }

        private void die() {
            nextState = false;
        }

        private void setAlive() {
            isAlive = nextState;
        }

        private double distance(Point point) {
            return Math.abs(Math.sqrt(Math.pow((double) point.x - (double) this.x, 2) + Math.pow((double) point.y - (double) this.y, 2)));
        }

        private boolean equalByCoordinates(Point point) {
            return x == point.x && y == point.y;
        }
    }

    // The function name and parameters were given by CodeWars
    public static int[][] getGeneration(int[][] cells, int generations) {
        Points points = new Points(cells);

        for (int i = 0; i < generations; i++) {
            points.nextGeneration();
        }

        return points.getArray();
    }


}
