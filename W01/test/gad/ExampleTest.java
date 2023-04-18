package gad;

import org.junit.jupiter.api.*;
import pgdp.maze.Walker;
import pgdp.maze.Maze;
import pgdp.maze.Result;

public class ExampleTest {

    @Test
    public void checkPenguInfoOutNegative() {
        boolean[][] maze = Maze.generateStandardMaze(10, 10);
        ResultImplementation result = new ResultImplementation(solution.length);
        Walker walker = new Walker(maze, result);
        assertTrue(walker.walk(), "Walker should find exit");
        int[] solutionX = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 4, 4, 3, 2, 1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9 };
        int[] solutionY = new int[] { 0, 1, 2, 3, 4, 5, 6, 5, 5, 5, 6, 6, 7, 8, 8, 8, 8, 8, 8, 8, 7, 7, 7, 8, 8, 8, 8 };
        result.testLogging(solutionLength, solutionX, solutionY);
    }
}