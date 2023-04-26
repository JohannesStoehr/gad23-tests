package gad.binarysearch;

import gad.binarysearch.BinSea;
import gad.binarysearch.StudentResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;


@Nested
class SimpleSearchTest {

	static Stream<Arguments> simple() {
		return Stream.of(
				Arguments.of(new int[]{1, 2, 3, 4}, 1, 0),
				Arguments.of(new int[]{1, 2, 3, 4}, 2, 1),
				Arguments.of(new int[]{1, 2, 3, 4}, 3, 2),
				Arguments.of(new int[]{1, 2, 3, 4}, 4, 3),
				Arguments.of(new int[]{6, 23, 45, 67, 89, 90, 100}, 23, 1),
				Arguments.of(new int[]{6, 23, 45, 67, 89, 90, 100}, 90, 5)
		);
	}

	static Stream<Arguments> valuesNotInArray() {
		return Stream.of(
				Arguments.of(new int[]{1, 2, 3, 4}, 5, 3),
				Arguments.of(new int[]{1, 2, 3, 4}, 0, 0),
				Arguments.of(new int[]{1, 2, 3, 4}, -1, 0),
				Arguments.of(new int[]{1, 2, 3, 4}, -5, 0),
				Arguments.of(new int[]{1, 2}, 0, 0)
		);
	}

	static Stream<Arguments> duplicatesInArray() {
		return Stream.of(
				Arguments.of(new int[]{1, 2, 2, 2, 6, 7, 8, 9, 10}, 2, 1),
				Arguments.of(new int[]{1, 2, 2, 2, 6, 7, 8, 9, 10}, 6, 4),
				Arguments.of(new int[]{1, 2, 2, 2, 2, 2, 2, 9, 10}, 2, 4)
		);
	}

	@ParameterizedTest
	@MethodSource({"simple", "valuesNotInArray", "duplicatesInArray"})
	void testSearch(int[] arr, int value, int expectedIdx) {
		Assertions.assertEquals(expectedIdx, BinSea.search(arr, value, new StudentResult()));
	}
}


