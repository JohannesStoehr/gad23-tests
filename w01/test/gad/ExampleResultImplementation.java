package gad;

import java.util.Arrays;
import gad.maze.Result;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleResultImplementation implements Result {
    private int[] pathX;
    private int[] pathY;
    private int pathIndex;

    public ExampleResultImplementation(int expectedSize) {
        pathX = new int[expectedSize];
        pathY = new int[expectedSize];
    }

    @Override
    public void addLocation(int x, int y) {
        if (pathIndex < pathX.length) {
            pathX[pathIndex] = x;
            pathY[pathIndex] = y;
        }
        pathIndex++;
    }

    public void testLogging(int[] solutionX, int[] solutionY) {
        assertEquals(solutionX.length, pathIndex, "Number of logged steps wrong");

        assertArrayEquals(solutionX, pathX, "Logged wrong path");
        assertArrayEquals(solutionY, pathY, "Logged wrong path");
    }
}