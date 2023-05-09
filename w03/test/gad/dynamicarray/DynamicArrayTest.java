package gad.dynamicarray;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    static DynamicArray getArtemisArray() {
        DynamicArray array = new DynamicArray(3, 4);
        array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 1);
        array.set(2, 1);
        array.set(0, 3);
        return array;
    }

    static Stream<Arguments> artemisTestArguments() {
        DynamicArray array = getArtemisArray();
        return Stream.of(
                Arguments.of(array, new Interval.NonEmptyInterval(1, 2), 4, new Interval.NonEmptyInterval(0, 1), new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
                Arguments.of(array, new Interval.NonEmptyInterval(3, 6), 4, new Interval.NonEmptyInterval(3, 6), new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
                Arguments.of(array, new Interval.NonEmptyInterval(1, 1), 1, new Interval.NonEmptyInterval(0, 0), new int[]{1, 0, 0}),
                Arguments.of(array, Interval.EmptyInterval.getEmptyInterval(), 2, Interval.EmptyInterval.getEmptyInterval(), new int[]{1, 0, 0}),
                Arguments.of(array, new Interval.NonEmptyInterval(2, 0), 4, new Interval.NonEmptyInterval(0, 1), new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
                Arguments.of(array, new Interval.NonEmptyInterval(5, 1), 9, new Interval.NonEmptyInterval(5, 1), new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
                Arguments.of(array, Interval.EmptyInterval.getEmptyInterval(), 0, Interval.EmptyInterval.getEmptyInterval(), new int[0])
        );
    }
    static Stream<Arguments> illegalArgumentsExceptionTest() {
        return Stream.of(
                Arguments.of(0,1, IllegalArgumentException.class),
                Arguments.of(4,-3, IllegalArgumentException.class),
                Arguments.of(9,2, IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @MethodSource({"artemisTestArguments"})
    void artemisTest(DynamicArray array, Interval interval, int minSize, Interval expectedInterval, int[] exptectedArray) {
        assertEquals(expectedInterval, array.reportUsage(interval, minSize));
        assertEquals(Arrays.toString(exptectedArray), array.toString());
    }

    @ParameterizedTest
    @MethodSource({"illegalArgumentsExceptionTest"})
    void illegalArgumentExceptionTest(int growthFactor, int maxOverhead, Class exception)
    {
        assertThrows(exception, () -> new DynamicArray(growthFactor,maxOverhead));
    }
}