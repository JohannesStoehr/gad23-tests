package gad.maze;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestExecutor {
    public static String PATH = "test/gad/maze/cases.txt";

    private static class TestResult implements Result {
        public void addLocation(int x, int y) {
            // TODO USE THIS
        }
    }

    @Test
    @DisplayName("This is a green test to motivate you for the semester! <3")
    public void greenTest() throws IOException {
        // Green Test
    }

    @ParameterizedTest
    @MethodSource
    void testMaze(int[] maze_data, boolean exit_expect) {
        var maze = Maze.generateMaze(maze_data[0], maze_data[1], maze_data[2]);
        var result = new TestResult();
        var walker = new Walker(maze, result);

        assertEquals(exit_expect, walker.walk(), exit_expect
                ? "Expected to find exit, found none."
                : "Expected not to find any exit, but apparently you found one!?");
    }

    /**
     * # TEST CASE FORMAT
     * 
     * ```
     * width height seed
     * should fine exit?
     * ```
     * 
     * @return
     * @throws IOException
     */
    private static Arguments getTestData(String data) {
        var match = Pattern.compile("(\\d+ \\d+ \\d+)\nexit: (true|false)").matcher(data);

        // Fix "No Match Found"
        // Citation: https://stackoverflow.com/a/5674321/16002144
        match.matches();

        var maze_data = Arrays.stream(match.group(1).split(" ")).mapToInt(Integer::parseInt).toArray();
        var can_exit = Boolean.parseBoolean(match.group(2));

        return arguments(maze_data, can_exit);
    }

    private static Stream<Arguments> testMaze() throws IOException {
        // The following gets the test case file, erases comments, then splits with the
        // special test case separator. Then it is converted to a stream and the test
        // data is mapped.

        return Arrays.stream(Files.lines(Paths.get(PATH))
                .filter(i -> !i.startsWith("#"))
                .filter(i -> i.length() > 0)
                .collect(Collectors.joining("\n"))
                .split("\n=====\n")).map(TestExecutor::getTestData);
    }
}
