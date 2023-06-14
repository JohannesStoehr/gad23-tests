import gad.simplesort.DualPivotFinder;
import gad.simplesort.DualPivotQuicksort;
import gad.simplesort.StudentResult;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DualPivotQuicksortTest {
    private static Stream<Arguments> dualPivotQuickSortTest() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10},
                        new int[]{1, 2, 4, 9, 10}

                ),
                Arguments.of(
                        new int[]{2, 4, 1, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        new int[]{1, 2, 4, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45}
                ),
                Arguments.of(
                        new int[]{44, 23, 2, 4, 1, 99, 9, 10, 11, 12, 13, 14, 15, 26, 37, 45},
                        new int[]{1, 2, 4, 9, 10, 11, 12, 13, 14, 15, 23, 26, 37, 44, 45, 99}
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24, 18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}
                ),
                Arguments.of(
                        new int[]{11, 21, 30, 4, 15, 1, 26, 13, 2, 29, 12, 10, 17, 22, 25, 24, 18, 28, 6, 14, 16, 19, 3, 5, 7, 27, 8, 9, 20, 23, 0},
                        new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}
                ),
                Arguments.of(
                        new int[]{5, 6, 5, 6, 5, 6, 55, 6, 5, 6, 5, 6, 55, 6, 5, 6, 5, 6, 55, 6, 5, 6, 5, 6, 5},
                        new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 55, 55, 55}
                ),
                Arguments.of(
                        new int[]{30, 25, 20, 15, 14, 10, 5, 1, 0, -5, -20, -100},
                        new int[]{-100, -20, -5, 0, 1, 5, 10, 14, 15, 20, 25, 30}
                ),
                Arguments.of(
                        new int[]{},
                        new int[]{}

                ),
                Arguments.of(
                        new int[]{5},
                        new int[]{5}
                ),
                Arguments.of(
                        new int[]{8, 3},
                        new int[]{3, 8}
                ),
                Arguments.of(
                        new int[]{7, 10},
                        new int[]{7, 10}
                ),
                Arguments.of(
                        new int[]{4, 2, 7, 2, 9, 4, 1},
                        new int[]{1, 2, 2, 4, 4, 7, 9}
                ),
                Arguments.of(
                        new int[]{9, 7, 5, 3, 1},
                        new int[]{1, 3, 5, 7, 9}
                ),
                Arguments.of(
                        new int[]{2, 4, 6, 8, 10},
                        new int[]{2, 4, 6, 8, 10}
                ),
                Arguments.of(
                        new int[]{9, 3, 7, 1, 5, 2, 6, 8, 4},
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void dualPivotQuickSortTest(int[] numbers, int[] expected) {
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        DualPivotQuicksort dualPivotQuicksort = new DualPivotQuicksort(DualPivotFinder.getFirstLastPivot(), 0);
        StudentResult result = new StudentResult();

        dualPivotQuicksort.sort(numbersCopy, result, 0, numbersCopy.length - 1);
        assertArrayEquals(expected, numbersCopy);
    }

    @Test
    public void dualPivotQuickSortTestLarge() {
        Random random = new Random();
        int[] numbers = new int[1_000_000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(Integer.MAX_VALUE);
        }
        int[] expected = Arrays.copyOf(numbers, numbers.length);

        DualPivotQuicksort dualPivotQuicksort = new DualPivotQuicksort(DualPivotFinder.getRandomPivot(10), 0);
        StudentResult result = new StudentResult();

        long start = System.nanoTime();
        Arrays.sort(expected);
        long binaryTime = System.nanoTime() - start;

        start = System.nanoTime();
        dualPivotQuicksort.sort(numbers, result, 0, numbers.length - 1);
        long decimalTime = System.nanoTime() - start;

        assertArrayEquals(expected, numbers);
        System.out.println("Your DualPivotQuicksort: " + binaryTime / 1_000_000);
        System.out.println("Java sort: " + decimalTime / 1_000_000);
    }

    @RepeatedTest(1000)
    public void dualPivotQuickSortTestPartial() {
        int[] numbers = new int[]{5, 12, 8, 3, 9, 7, 2, 1, 6, 4, 10, 15, 11, 13, 14, 17, 19, 18, 20, 16, 23, 22, 21, 25, 24, 26, 29, 27, 30, 28, 33, 31, 34, 36, 32, 35, 38, 40, 37, 39, 44, 42, 41, 47, 45, 50, 46, 43, 49, 48};
        int[] expected = Arrays.copyOf(numbers, numbers.length);
        int[] expectedCopy = Arrays.copyOf(expected, expected.length);
        int[] expectedCopy3 = new int[expectedCopy.length];

        Random random = new Random();

        int from = random.nextInt(numbers.length);
        int to = random.nextInt(numbers.length);

        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
        }

        int[] expectedSortedPart = new int[to - from + 1];

        int j = 0;
        for (int i = from; i <= to; i++) {
            expectedSortedPart[j++] = expectedCopy[i];
        }
        Arrays.sort(expectedSortedPart);

        for (int i = 0; i < expected.length; i++) {
            if (i >= from && i <= to) {
                expectedCopy3[i] = expectedSortedPart[i - from];
            } else {
                expectedCopy3[i] = expected[i];
            }
        }

        DualPivotQuicksort dualPivotQuicksort = new DualPivotQuicksort(DualPivotFinder.getRandomPivot(10), 0);
        StudentResult result = new StudentResult();

        dualPivotQuicksort.sort(numbers, result, from, to);
        System.out.println("from: " + from + " to: " + to);
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(expectedCopy3));
        assertArrayEquals(expectedCopy3, numbers);
    }
}
