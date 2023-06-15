package gad.simplehash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FastModuloTest {
    private static Stream<Arguments> testFastModulo() {
        return Stream.of(
                Arguments.of(0, 1, 0),
                Arguments.of(1, 1, 0),
                Arguments.of(2, 1, 0),
                Arguments.of(3, 1, 0),
                Arguments.of(2, 2, 0),
                Arguments.of(3, 2, 1),
                Arguments.of(4, 2, 0),
                Arguments.of(5, 2, 1),
                Arguments.of(4, 4, 0),
                Arguments.of(5, 4, 1),
                Arguments.of(6, 4, 2),
                Arguments.of(7, 4, 3),
                Arguments.of(8, 8, 0),
                Arguments.of(9, 8, 1),
                Arguments.of(10, 8, 2),
                Arguments.of(11, 8, 3),
                Arguments.of(12, 8, 4),
                Arguments.of(13, 8, 5),
                Arguments.of(14, 8, 6),
                Arguments.of(15, 8, 7)

        );
    }

    @ParameterizedTest
    @MethodSource
    public void testFastModulo(int i, int divisor, int expect) {
        assertEquals(expect, Hashtable.fastModulo(i, divisor));
    }
}
