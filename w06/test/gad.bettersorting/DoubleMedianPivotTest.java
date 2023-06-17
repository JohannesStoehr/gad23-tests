package gad.bettersorting;

import gad.simplesort.DualPivotFinder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleMedianPivotTest {

    @ParameterizedTest
    @MethodSource("deviatingNumbersOfConsideredElements")
    @DisplayName("Based on Artemis")
    void doubleMedianPivotTest(int[] numbers, int[] expected, int numbersOfConsideredElements, int from, int to) {
        DualPivotFinder dualPivotFinder = DualPivotFinder.getMedianPivotFront(numbersOfConsideredElements);
        assertArrayEquals(expected, dualPivotFinder.findPivot(numbers, from, to));
    }

    @ParameterizedTest
    @MethodSource({"notRequire", "hard"})
    @DisplayName("General Tests not necessary")
    void doubleMedianPivotTest2(int[] numbers, int[] expected, int numbersOfConsideredElements, int from, int to) {
        DualPivotFinder dualPivotFinder = DualPivotFinder.getMedianPivotFront(numbersOfConsideredElements);
        assertArrayEquals(expected, dualPivotFinder.findPivot(numbers, from, to));
    }



    private static Stream<Arguments> deviatingNumbersOfConsideredElements() {
        return Stream.of(
                Arguments.of(
                        new int[] { 4, 9, 1, 10, 2 },
                        new int[] { 1, 4 },
                        5,
                        0,
                        4),
                Arguments.of(
                        new int[] { 4, 9, 1, 10, 2, 5, 99, 23, 3, 35, 6 },
                        new int[] { 1, 2 },
                        5,
                        1,
                        4),
                Arguments.of(
                        new int[] { 4, 9, 1, 10, 2 },
                        new int[] { 1, 4 },
                        5,
                        0,
                        4),
                Arguments.of(
                        new int[] { 4, 9, 1, 10, 2 },
                        new int[] { 1, 4 },
                        5,
                        0,
                        4));
    }

    private static Stream<Arguments> notRequire()
    {
        return Stream.of(
                Arguments.of(
                        new int[] { 4, 9, 1, 10, 2, 5, 99, 23, 3, 35, 6 },
                        new int[] { 0, 3 },
                        11,
                        0,
                        10),
                Arguments.of(
                        new int[] { 8, 42, 10, 75, 29, 77, 38, 57, 24 },
                        new int[] { 1, 8 },
                        9,
                        0,
                        8),
                Arguments.of(
                        new int[] { 24, 8, 76, 10, 75, 29, 77, 38, 57 },
                        new int[] { 0, 8 },
                        9,
                        0,
                        8),
                Arguments.of(
                        new int[] { 11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24,
                                18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0 },
                        new int[] { 27, 28 },
                        31,
                        0,
                        30),
                Arguments.of(
                        new int[] { 5, 6, 5, 6, 55, 6, 5, 6, 55, 6, 5, 6, 5 },
                        new int[] { 0, 1 },
                        13,
                        0,
                        12),
                Arguments.of(
                        new int[] { 2, 4, 8, 8, 8, 12, 14, 26, 26, 28, 30, 30, 30, 30, 34, 36,
                                36 },
                        new int[] { 5, 10 },
                        17,
                        0,
                        16),
                Arguments.of(
                        new int[] { 5, 6, 7, 44, 44, 44, 44, 44, 44, 44, 44 },
                        new int[] { 3, 4 },
                        11,
                        0,
                        10),
                Arguments.of(
                        new int[] { 16, 15, 14, 12, 12, 12, 11, 10, 10, 10, 10, 4, 2 },
                        new int[] { 3, 7 },
                        13,
                        0,
                        12),
                Arguments.of(
                        new int[] { 16, 15, 14, 12, 12, 12, 10, 10, 10, 10, 4, 2 },
                        new int[] { 3, 6 },
                        12,
                        0,
                        11),
                Arguments.of(
                        new int[] { 10, 10, 10, 15, 10, 20, 10, 10 },
                        new int[] { 0, 1 },
                        8,
                        0,
                        7),
                Arguments.of(
                        new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
                        new int[] { 0, 1 },
                        10,
                        0,
                        9),
                Arguments.of(
                        new int[] { 11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24,
                                18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0 },
                        new int[] { 1, 3 },
                        31,
                        0,
                        9),
                Arguments.of(
                        new int[] { 11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24,
                                18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0 },
                        new int[] { 0, 1 },
                        31,
                        0,
                        2),
                Arguments.of(
                        new int[] { 11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24,
                                18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0 },
                        new int[] { 10, 15 },
                        31,
                        5,
                        15),
                Arguments.of(
                        new int[] { 11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24,
                                18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0 },
                        new int[] { 3, 4 },
                        6,
                        2,
                        30),
                Arguments.of(
                        new int[] { 11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24,
                                18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0 },
                        new int[] { 4, 8 },
                        7,
                        4,
                        30),
                Arguments.of(
                        new int[] { 11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24,
                                18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0 },
                        new int[] { 10, 13 },
                        8,
                        6,
                        30)
        );
    }
    /**
     * <div>
     * This Testcase is a bitch. It's nearly impossible to pass it.
     * </div>
     * <div>
     * Kudos to you if you can pass this testcase. It took me all night getting the
     * algorithm right.
     * </div>
     */
    private static Stream<Arguments> hard() {
        return Stream.of(
                Arguments.of(
                        new int[] { 5, 6, 5, 6, 55, 6, 5, 6, 5, 6, 55, 6, 5, 6, 5, 6, 55 },
                        new int[] { 0, 1 },
                        17,
                        0,
                        16),
                Arguments.of(
                        new int[] { 44, 44, 44, 5, 6, 44, 44, 7, 44, 44, 44 },
                        new int[] { 0, 1 },
                        11,
                        0,
                        10));
    }
}