package gad.logging;

import gad.ExampleResultImplementation;
import gad.maze.Maze;
import gad.maze.Walker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogging {

    @Test
    @DisplayName("Logging - Artemis Example")
    public void checkLoggingArtemisExample() {
        boolean[][] maze = Maze.generateStandardMaze(10, 10);
        int[] solutionX = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 4, 4, 3, 2, 1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9 };
        int[] solutionY = new int[] { 0, 1, 2, 3, 4, 5, 6, 5, 5, 5, 6, 6, 7, 8, 8, 8, 8, 8, 8, 8, 7, 7, 7, 8, 8, 8, 8 };
        ExampleResultImplementation result = new ExampleResultImplementation(solutionX.length);
        Walker walker = new Walker(maze, result);
        assertTrue(walker.walk(), "Walker should find exit");
        result.testLogging(solutionX, solutionY);
    }

    @Test
    @DisplayName("Logging - Small Maze")
    public void checkLoggingSmallMaze() {
        boolean[][] maze = Maze.generateMaze(3,3,0);
        int[] solutionX = new int[] { 1, 1, 2 };
        int[] solutionY = new int[] { 0, 1, 1 };
        ExampleResultImplementation result = new ExampleResultImplementation(solutionX.length);
        Walker walker = new Walker(maze, result);
        assertTrue(walker.walk(), "Walker should find exit");
        result.testLogging(solutionX, solutionY);
    }

    @Test
    @DisplayName("Logging - Example Test Case")
    public void checkLoggingExampleTestCase() {
        boolean[][] maze = Maze.generateMaze(4,4,1);
        int[] solutionX = new int[] { 1, 1, 1, 2, 3 };
        int[] solutionY = new int[] { 0, 1, 2, 2, 2 };
        ExampleResultImplementation result = new ExampleResultImplementation(solutionX.length);
        Walker walker = new Walker(maze, result);
        assertTrue(walker.walk(), "Walker should find exit");
        result.testLogging(solutionX, solutionY);
    }

    @Test
    @DisplayName("Logging - Test Case no exit1")
    public void checkLoggingTestCaseNoExit1() {
        boolean[][] maze = Maze.generateMaze(10,10,1);
        int[] solutionX = new int[] { 1, 1, 1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 1 };
        int[] solutionY = new int[] { 0, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0 };
        ExampleResultImplementation result = new ExampleResultImplementation(solutionX.length);
        Walker walker = new Walker(maze, result);
        assertFalse(walker.walk(), "Walker should not find exit");
        result.testLogging(solutionX, solutionY);
    }

    @Test
    @DisplayName("Logging - Test Case no exit2")
    public void checkLoggingTestCaseNoExit2() {
        boolean[][] maze = Maze.generateMaze(10,10,7);
        int[] solutionX = new int[] { 1, 1, 1 };
        int[] solutionY = new int[] { 0, 1, 0 };
        ExampleResultImplementation result = new ExampleResultImplementation(solutionX.length);
        Walker walker = new Walker(maze, result);
        assertFalse(walker.walk(), "Walker should not find exit");
        result.testLogging(solutionX, solutionY);
    }
}
