package gad.simplehash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTest {
    private static Stream<Arguments> testHash() {
        return Stream.of(
                Arguments.of(1, new int[]{1}, "x", 0),
                Arguments.of(1, new int[]{1}, "y", 0),
                Arguments.of(1, new int[]{1}, "z", 0),

                Arguments.of(2, new int[]{1, 2}, "x", 0),
                Arguments.of(2, new int[]{1, 2}, "y", 1),
                Arguments.of(2, new int[]{1, 2}, "z", 0),

                Arguments.of(4, new int[]{1, 2}, "x", 0),
                Arguments.of(4, new int[]{1, 2}, "y", 1),
                Arguments.of(4, new int[]{1, 2}, "z", 2),

                Arguments.of(123, new int[]{123}, "foo", 44),
                Arguments.of(123, new int[]{123}, "bar", 119),

                Arguments.of(12345678, new int[]{1, 2, 3, 4, 5, 6, 7, 8}, "Lorem ipsum dolor sit amet", 10625)

        );
    }

    @ParameterizedTest
    @MethodSource
    public void testHash(int minSize, int[] a, String k, int expect) {
        Hashtable<String, Integer> hashtable = new Hashtable<>(minSize, a);

        ModuloHelper mH = (i, divisor) -> i % divisor;

        assertEquals(expect, hashtable.h(k, mH));
    }
}
