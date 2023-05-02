package gad.logging;

import gad.binarysearch.BinSea;
import gad.binarysearch.Interval;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class loggingTest {

        // ---- SimpleSearch ---- //
        private static Stream<Arguments> testLoggingSimpleSearch() {
                return Stream.of(
                                // normal
                                Arguments.of(new int[] { 1, 2, 3, 4 }, 2, new int[] { 1 }),
                                Arguments.of(new int[] { 6, 23, 45, 67, 89, 90, 100 }, 90, new int[] { 3, 5 }),
                                // values not in array
                                Arguments.of(new int[] { 1, 2, 3, 4 }, 5, new int[] { 1, 2 }),
                                Arguments.of(new int[] { 1, 2 }, 0, new int[] { 0 }),
                                // duplicates in array
                                Arguments.of(new int[] { 1, 2, 2, 2, 6, 7, 8, 9, 10 }, 2, new int[] { 4, 1 }),
                                Arguments.of(new int[] { 1, 2, 2, 2, 6, 7, 8, 9, 10 }, 6, new int[] { 4 }));
        }

        @ParameterizedTest
        @MethodSource
        public void testLoggingSimpleSearch(int[] sortedData, int value, int[] expect) {
                TestResultImplementation result = new TestResultImplementation(expect.length);
                BinSea.search(sortedData, value, result);
                result.testLogging(expect);
        }

        // ---- BoundSearch ---- //
        private static Stream<Arguments> testLoggingBoundSearch() {
                return Stream.of(
                                // normal
                                Arguments.of(new int[] { 1, 2, 3, 4 }, 1, true, new int[] { 1 }),
                                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 2, false, new int[] { 3, 1 }),
                                // values not in array
                                Arguments.of(new int[] { 1, 2, 2, 2, 6, 7, 8, 9, 10 }, -1, true, new int[] { 4, 1 }),
                                Arguments.of(new int[] { 1, 2, 2, 4, 4, 4, 4, 8, 8, 27, 89 }, 3, false,
                                                new int[] { 5, 2, 3 }),
                                // duplicates in array
                                Arguments.of(new int[] { 1, 2, 2, 4, 4, 4, 4, 8, 8, 27, 89 }, 4, true, new int[] { 5 }),
                                Arguments.of(new int[] { 1, 2, 2, 4, 4, 4, 4, 8, 8, 27, 89 }, 4, false,
                                                new int[] { 5 }),
                                // random values
                                Arguments.of(new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8, 10, 23 }, 6, false,
                                                new int[] { 5, 8, 6 }),
                                Arguments.of(new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8 }, 9, true, new int[] { 4, 6, 7 }),
                                Arguments.of(new int[] { 1, 2, 2, 4, 4, 4, 4, 8, 8, 27, 89 }, 25, true,
                                                new int[] { 5, 8, 9 }));
        }

        @ParameterizedTest
        @MethodSource
        public void testLoggingBoundSearch(int[] sortedData, int value, boolean lowerBound, int[] expect) {
                TestResultImplementation result = new TestResultImplementation(expect.length);
                BinSea.search(sortedData, value, lowerBound, result);
                result.testLogging(expect);
        }

        // ---- IntervalSearch ---- //
        private static Stream<Arguments> testLoggingIntervalSearch() {
                return Stream.of(
                                // random
                                Arguments.of(
                                                new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8 },
                                                Interval.NonEmptyInterval.fromArrayIndices(1, 4),
                                                new int[] { 4, 1 },
                                                new int[] { 4, 1 }),
                                Arguments.of(
                                                new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8 },
                                                Interval.NonEmptyInterval.fromArrayIndices(2, 5),
                                                new int[] { 4, 1 },
                                                new int[] { 4 }),
                                Arguments.of(
                                                new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8, 10, 23 },
                                                Interval.NonEmptyInterval.fromArrayIndices(3, 9),
                                                new int[] { 5, 2, 0 },
                                                new int[] { 5, 8, 9 }),
                                Arguments.of(
                                                new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8, 10, 23 },
                                                Interval.NonEmptyInterval.fromArrayIndices(8, 8),
                                                new int[] { 5, 8 },
                                                new int[] { 5, 8 }),
                                // empty
                                Arguments.of(
                                                new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8 },
                                                Interval.NonEmptyInterval.fromArrayIndices(6, 7),
                                                new int[] { 4, 6, 7 },
                                                new int[] { 4, 6, 7 }),
                                Arguments.of(
                                                new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8, 2325 },
                                                Interval.NonEmptyInterval.fromArrayIndices(235, 2311),
                                                new int[] { 4, 7, 8 },
                                                new int[] { 4, 7, 8 }),
                                // only lower logging
                                Arguments.of(
                                                new int[] { 1, 2, 3 },
                                                Interval.NonEmptyInterval.fromArrayIndices(4, 5),
                                                new int[] { 1 },
                                                new int[] {}),
                                Arguments.of(
                                                new int[] { 1, 4, 4, 4, 5, 5, 5, 5, 8 },
                                                Interval.NonEmptyInterval.fromArrayIndices(10, 16),
                                                new int[] { 4, 6, 7 },
                                                new int[] {}));
        }

        @ParameterizedTest
        @MethodSource
        public void testLoggingIntervalSearch(int[] sortedData, Interval.NonEmptyInterval interval, int[] expectLower,
                        int[] expectHigher) {
                TestResultImplementation resultLower = new TestResultImplementation(expectLower.length);
                TestResultImplementation resultHigher = new TestResultImplementation(expectHigher.length);
                BinSea.search(sortedData, interval, resultLower, resultHigher);
                resultLower.testLogging(expectLower);
                resultHigher.testLogging(expectHigher);
        }
}
