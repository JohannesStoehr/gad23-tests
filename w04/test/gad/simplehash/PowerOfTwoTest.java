package gad.simplehash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerOfTwoTest {

    private static Stream<Arguments> testPowerOfTwo() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(2, 2),
                Arguments.of(33, 64),
                Arguments.of(63, 64),
                Arguments.of(536870913, 1073741824),
                Arguments.of(1073741823, 1073741824),
                Arguments.of(1073741824, 1073741824)

        );
    }

    @ParameterizedTest
    @MethodSource
    public void testPowerOfTwo(int i, int expect) {
        assertEquals(expect, Hashtable.getNextPowerOfTwo(i));
    }
}
