package gad.radixsort;

import gad.radix.RadixSort;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RadixSortTest {

    @ParameterizedTest
    @MethodSource("provideMaxDecimalPlaces")
    void testMaxDecimalPlaces(int[] elements, int expectedMax) {
        assertEquals(expectedMax, RadixSort.getMaxDecimalPlaces(elements));
    }

    private static Stream<Arguments> provideMaxDecimalPlaces() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 5675, 222222, 930}, 6),
                Arguments.of(new int[]{1, 2, 5675990, -222222, 930}, 7),
                Arguments.of(new int[]{0, 0, 0, 0}, 1),
                Arguments.of(new int[]{0, -1, 0, 0}, 1),
                Arguments.of(new int[]{0, -889, 0, 0}, 3),
                Arguments.of(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, 10),
                Arguments.of(new int[]{1, 2, 5675, -222222, 930, -1, 999999, 1000000}, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("provideKey")
    void testKey(int element, int decimalPlace, int expected) {
        assertEquals(expected, RadixSort.key(element, decimalPlace));
    }

    private static Stream<Arguments> provideKey() {
        return Stream.of(
                Arguments.of(1234, 3, 1),
                Arguments.of(981739, 2, 7),
                Arguments.of(439183, 0, 3),
                //Edge-Cases
                Arguments.of(0, 99, 0),
                Arguments.of(6, 0, 6),
                Arguments.of(0, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideKeyInvalidIndex")
    void testKeyWithInvalidIndex(int element, int decimalPlace, int expected) {
        assertEquals(expected, RadixSort.key(element, decimalPlace));
    }

    private static Stream<Arguments> provideKeyInvalidIndex() {
        return Stream.of(
                Arguments.of(12345, 5, 0),
                Arguments.of(987654321, 10, 0),
                Arguments.of(0, 0, 0)
        );
    }
}