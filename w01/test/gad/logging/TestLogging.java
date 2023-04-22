package gad.logging;

import gad.ExampleResultImplementation;
import gad.maze.Maze;
import gad.maze.Walker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogging {

    @ParameterizedTest
    @MethodSource
    public void checkLogging(int width, int height, int seed, int[] solutionX, int[] solutionY, boolean expect) {
        boolean[][] maze = Maze.generateMaze(width, height, seed);

        ExampleResultImplementation result = new ExampleResultImplementation(solutionX.length);
        Walker walker = new Walker(maze, result);
        assertEquals(expect, walker.walk(), "Walker should" + (expect ? "" : " not") + " find exit");
        result.testLogging(solutionX, solutionY);
    }

    private static Stream<Arguments> checkLogging() {
        return Stream.of(
                Arguments.of(
                        10, 10, 0,
                        new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 4, 4, 3, 2, 1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9 },
                        new int[] { 0, 1, 2, 3, 4, 5, 6, 5, 5, 5, 6, 6, 7, 8, 8, 8, 8, 8, 8, 8, 7, 7, 7, 8, 8, 8, 8 },
                        true
                ),
                Arguments.of(
                        3,3,0,
                        new int[] { 1, 1, 2 },
                        new int[] { 0, 1, 1 },
                        true
                ),
                Arguments.of(
                        4,4,1,
                        new int[] { 1, 1, 1, 2, 3 },
                        new int[] { 0, 1, 2, 2, 2 },
                        true
                ),
                Arguments.of(
                        10,10,1,
                        new int[] { 1, 1, 1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 1 },
                        new int[] { 0, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0 },
                        false
                ),
                Arguments.of(
                        10,10,7,
                        new int[] { 1, 1, 1 },
                        new int[] { 0, 1, 0 },
                        false
                )
        //, More Arguments here
        );}
}
