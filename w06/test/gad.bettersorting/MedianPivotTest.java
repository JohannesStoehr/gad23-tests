package gad.bettersorting;

import gad.simplesort.PivotFinder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MedianPivotTest {

    @ParameterizedTest
    @MethodSource({"artemisBeispiele", "numberOfConsideredElementsEqualToLength", "numberOfConsideredElementsSmaller", "numberOfConsideredElementsBigger", "deviatingFromAndTo"})
    void medianPivotTest(int[] numbers, int expected, int numberOfConsideredElements, int from, int to) {
        PivotFinder medianPivotFinder = PivotFinder.getMedianPivotFront(numberOfConsideredElements);
        assertEquals(expected, medianPivotFinder.findPivot(numbers, from, to));
    }

    private static Stream<Arguments> artemisBeispiele() {
        return Stream.of(
                Arguments.of(
                        new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                        1,
                        3,
                        0,
                        9
                ),
                Arguments.of(
                        new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7},
                        3,
                        5,
                        0,
                        9
                ),
                Arguments.of(
                        new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7},
                        3,
                        5,
                        2,
                        8
                )
        );
    }

    private static Stream<Arguments> numberOfConsideredElementsEqualToLength() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 5, 5, 5, 5, 5, 5, 7, 8},
                        1,
                        9,
                        0,
                        8
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 9},
                        5,
                        16,
                        0,
                        15
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        1,
                        5,
                        0,
                        4
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        6,
                        13,
                        0,
                        12
                ),
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        10,
                        16,
                        0,
                        15
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24, 18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        4,
                        31,
                        0,
                        30
                )
        );
    }

    private static Stream<Arguments> numberOfConsideredElementsSmaller() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        0,
                        3,
                        0,
                        5
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        1,
                        5,
                        0,
                        12
                ),
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        6,
                        7,
                        0,
                        15
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24, 18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        10,
                        12,
                        0,
                        30
                ),
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        0,
                        16 + Integer.MAX_VALUE,
                        0,
                        15
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24, 18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        0,
                        -31,
                        0,
                        30
                )
        );
    }

    private static Stream<Arguments> numberOfConsideredElementsBigger() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        1,
                        6,
                        0,
                        4
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        6,
                        14,
                        0,
                        12
                )
        );
    }

    private static Stream<Arguments> deviatingFromAndTo() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        0,
                        5,
                        0,
                        3
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        5,
                        13,
                        0,
                        10
                ),
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        10,
                        17,
                        0,
                        17
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24, 18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        19,
                        31,
                        3,
                        30
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        1,
                        5,
                        1,
                        3
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        11,
                        13,
                        10,
                        12
                ),
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        7,
                        5,
                        4,
                        12
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24, 18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        16,
                        8,
                        9,
                        23
                )
        );
    }
}