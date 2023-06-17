package gad.bettersorting;

import gad.simplesort.PivotFinder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianPivotFrontTest {

    private static Stream<Arguments> artemisExampleTest()
    {
        return Stream.of(
                Arguments.of(
                        new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                        0,
                        9,
                        3,
                        1
                ),
                Arguments.of(
                        new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7},
                        0,
                        9,
                        5,
                        3
                ),
                Arguments.of(
                        new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7},
                        2,
                        8,
                        5,
                        3
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void artemisExampleTest(int[] numbers, int from, int to, int numbersOfConsideredElements, int expected)
    {
        int actual = PivotFinder.getMedianPivotFront(numbersOfConsideredElements).findPivot(numbers,from,to);
        assertEquals(expected,actual);
    }
}
