package gad;

import org.junit.jupiter.api.*;
import pgdp.maze.Result;

public class ExampleResultImplementation implements Result {
    private int[] pathX;
    private int[] pathY;
    private int pathIndex;

    public ResultImplementation(int expectedSize) {
        path = new Coordinate[expectedSize];
    }

    @Override
    public void addLocation(int x, int y) {
        if (pathIndex < path.length) {
            pathX[pathIndex] = x;
            pathX[pathIndex] = y;
        }
        pathIndex++;
    }

    public void testLogging(int solutionLength, int[] solutionX, int[] solutionY) {
        if (pathIndex != solutionLength) {
            fail("Number of logged steps wrong");
        }

        if (!Arrays.equals(solutionX, pathX) || !Arrays.equals(solutionY, pathY)) {
            fail("Logged wrong path");
        }
    }
}