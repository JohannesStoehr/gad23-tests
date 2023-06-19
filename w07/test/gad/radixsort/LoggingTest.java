package gad.radixsort;

import gad.radix.BinaryRadixSort;
import gad.radix.StudentResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggingTest {

    private static StudentResult studentResult;
    private static ByteArrayOutputStream outputStream;

    private static String PATH = "test/gad/radixsort/loggingCases.txt";

    @BeforeEach
    void init() {
        studentResult = new StudentResult();
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
    }

    /**
     * <h>CASE FORMATE</h>
     * <p>
     * ````<br>
     * numbers<br>
     * Log:<br>
     * logging<br>
     * ````
     * </p>
     *
     * @return Arguments.of(int[],String)
     */
    private static Arguments getTestData(String data) {
        String[] data_a = data.split("\nLog:\n");

        int[] numbers = Arrays.stream(data_a[0].split(" ")).mapToInt(s -> {
            if (s.startsWith("-")) {
                return (Integer.parseInt(s.substring(1)) * -1);
            } else {
                return Integer.parseInt(s);
            }
        }).toArray();
        String logging = data_a[1].replaceAll("\n", "\r\n") + "\r\n";
        return Arguments.of(numbers, logging);
    }

    private static Stream<Arguments> loggingTest() throws IOException {
        return Arrays.stream(Files.lines(Paths.get(PATH))
                .filter(i -> !i.startsWith("#"))
                .filter(i -> i.length() > 0)
                .collect(Collectors.joining("\n"))
                .split("\n====\n")).map(LoggingTest::getTestData);
    }

    @ParameterizedTest
    @MethodSource("loggingTest")
    void loggingTest(int[] numbers, String loggingException) {
        int[] sorted = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sorted);
        BinaryRadixSort.sort(numbers, studentResult);
        String consoleOutput = outputStream.toString();
        assertArrayEquals(sorted, numbers, "Array isn't sorted correctly!");
        assertEquals(loggingException, consoleOutput, "Log isn't as expected!");
    }
}
