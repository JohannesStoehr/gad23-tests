package gad.radixsort;

import gad.radix.BinaryBucket;
import gad.radix.BinaryRadixSort;
import gad.radix.StudentResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryRadixSortTest {


    @ParameterizedTest
    @MethodSource("provideTestDataBinaryKey")
    @DisplayName("Binary Key")
    @Order(1)
    void testBinaryKey(int element, int binPlace, int expected) {
        assertEquals(expected, BinaryRadixSort.key(element, binPlace));
    }

    private static Stream<Arguments> provideTestDataBinaryKey() {
        return Stream.of(
                Arguments.of(0b00000000000000000000000000000001, 0, 1),
                Arguments.of(0b00000000000000000000010000000010, 1, 1),
                Arguments.of(0b00000000000000000000010000000100, 2, 1),
                Arguments.of(0b10000000000000000000000000000001, 31, 1),
                Arguments.of(0b01000000000000000000000000000000, 30, 1),
                Arguments.of(0b1111011111111111111111111111111, 26, 0),
                Arguments.of(0b00000000000000000000010000000000, 10, 1)
        );
    }

    @Test
    @Order(1)
    @DisplayName("Binary Bucket")
    void testBinaryBucket() {
        BinaryBucket binaryBucket = new BinaryBucket(10);
        binaryBucket.insertLeft(2);
        binaryBucket.insertLeft(5);
        binaryBucket.insertRight(10);
        assertEquals(2, binaryBucket.getMid());
        binaryBucket = new BinaryBucket(100);
        for (int i = 0; i < 99; i++) {
            binaryBucket.insertLeft(1);
        }
        assertEquals(99, binaryBucket.getMid());
    }

    private void sortTest(int[] elements, int[] expects) {
        Arrays.sort(expects);
        BinaryRadixSort.sort(elements, new StudentResult());
        assertArrayEquals(expects, elements);
    }

    @Test
    @DisplayName("Only negativ Numbers as elements")
    @Order(3)
    void sortOnlyNegativTest() {
        Random random = new Random(100);
        int[] numbers = new int[500];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(Integer.MIN_VALUE, -1);
        }
        int[] expects = Arrays.copyOf(numbers, numbers.length);
        sortTest(numbers, expects);
    }

    @Test
    @DisplayName("Only positive Numbers as elements")
    @Order(2)
    void sortOnlyPositiveTest() {
        Random random = new Random(100);
        int[] numbers = new int[500];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(0, Integer.MAX_VALUE);
        }
        int[] expects = Arrays.copyOf(numbers, numbers.length);
        sortTest(numbers, expects);
    }

    @Test
    @DisplayName("Usual elements")
    @Order(4)
    void sortUsualTest() {
        Random random = new Random(100);
        int[] numbers = new int[500];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        int[] expects = Arrays.copyOf(numbers, numbers.length);
        sortTest(numbers, expects);
    }

    /**
     * <div>
     * This test may not pass on every device.
     * It also doesn't reflect the requirement of the performance description on Artemis.
     * The timeout only serves as a barrier if the test should run too long on a device.
     * I need an average of 1.8 seconds for this test.
     * </div>
     */
    @Test
    @DisplayName("Big Test")
    @Order(5)
    void sortBigTest() {
        Random random = new Random(100);
        int[] numbers = new int[250_000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        int[] expects = Arrays.copyOf(numbers, numbers.length);
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> sortTest(numbers, expects));
    }
}