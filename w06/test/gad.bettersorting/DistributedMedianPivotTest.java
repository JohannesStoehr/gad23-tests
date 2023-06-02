package gad.bettersorting;

import gad.simplesort.PivotFinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DistributedMedianPivotTest {

    @ParameterizedTest
    @MethodSource({"artemisExample", "fromEquals0", "allParameterDiffer"})
    void distributedMedianPivotTest(int[] numbers, int expected, int numberOfConsideredElements, int from, int to) {
        PivotFinder medianPivotFinder = PivotFinder.getMedianPivotDistributed(numberOfConsideredElements);
        assertEquals(expected, medianPivotFinder.findPivot(numbers, from, to));
    }

    private static Stream<Arguments> artemisExample() {
        return Stream.of(
                Arguments.of(
                        new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                        4,
                        3,
                        0,
                        9
                ),
                Arguments.of(
                        new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7},
                        6,
                        5,
                        0,
                        9
                ),
                Arguments.of(
                        new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7},
                        5,
                        5,
                        1,
                        9
                )
        );
    }

    private static Stream<Arguments> fromEquals0() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 5, 5, 5, 5, 5, 5, 7, 8},
                        0,
                        2,
                        0,
                        8
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 9},
                        5,
                        4,
                        0,
                        15
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 9},
                        6,
                        5,
                        0,
                        15
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        0,
                        3,
                        0,
                        4
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        6,
                        7,
                        0,
                        12
                ),
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        14,
                        3,
                        0,
                        14
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 23, 18, 28, 6, 14, 16, 19, 0, 3, 5, 7, 27, 8, 9, 20, 24},
                        15,
                        3,
                        0,
                        30
                )
        );
    }

    private static Stream<Arguments> allParameterDiffer() {
        return Stream.of(
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        6,
                        6,
                        2,
                        12
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12,
                                10, 17, 22, 25, 24, 18, 28, 6, 14, 16,
                                19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        26,
                        4,
                        20,
                        30
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        1,
                        2,
                        1,
                        3
                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        1,
                        2,
                        1,
                        11
                )
        );
    }
}