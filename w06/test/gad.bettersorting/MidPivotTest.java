package gad.bettersorting;

import gad.simplesort.PivotFinder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MidPivotTest {

    private static Stream<Arguments> midPivotTest() {
        return Stream.of(
                Arguments.of(
                        new int[]{0, 1, 2, 3, 4},
                        0,
                        4,
                        2
                ),
                Arguments.of(
                        new int[]{0, 1, 2, 3},
                        0,
                        3,
                        1
                ),
                Arguments.of(
                        new int[]{0, 1, 2, 3},
                        1,
                        3,
                        2
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void midPivotTest(int[] numbers, int from, int to, int expected) {
        int actual = PivotFinder.getMidPivot().findPivot(numbers, from, to);
        assertEquals(expected, actual);
    }
}
