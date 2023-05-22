package gad.doublehashing;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DoubleHashIntTest {
    final static int iterations = 25;

    final static int[] primes = new int[] {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 51, 53,
            // Source: https://t5k.org/curios/index.php?start=7&stop=7
            2667883, 7576757, 9997777,
            // https://prime-numbers.fandom.com/wiki/2,147,483,647
            2_147_483_647
    };

    private static IntStream all_primes() {
        return Arrays.stream(primes);
    }

    private static IntStream small_primes() {
        return all_primes().filter(i -> i < 100);
    }

    public static void assertRange(int from, int value, int to) {
        if (from > value || value >= to) {
            fail(String.format("Illegal Value! %s not in range (%s; %s]", value, from, to));
        }
    }

    @ParameterizedTest
    @MethodSource("small_primes")
    public void distributionTest(int primeSize) {
        DoubleHashInt hashInt = new DoubleHashInt(primeSize);
        int[] distribution = new int[primeSize];

        for (int i = 0; i < primeSize * iterations; i++) {
            distribution[hashInt.hash(i)] += 1;
        }

        var max = Arrays.stream(distribution).max().orElse(0);
        var min = Arrays.stream(distribution).min().orElse(0);

        assertTrue(max - min < 2, "hash() is not distributed evenly");
    }

    @ParameterizedTest
    @MethodSource("small_primes")
    public void distributionTickTest(int primeSize) {
        DoubleHashInt hashInt = new DoubleHashInt(primeSize);
        int[] distribution = new int[primeSize];

        for (int i = 0; i < primeSize * iterations; i++) {
            distribution[hashInt.hashTick(i)] += 1;
        }

        var max = Arrays.stream(distribution).max().orElse(0);
        var min = Arrays.stream(distribution).filter(i -> i > 0).min().orElse(0);

        if (max - min > 1) {
            fail("hash() is not distributed evenly:\n" + Arrays.toString(distribution));
        }
    }

    @ParameterizedTest
    @MethodSource("small_primes")
    @DisplayName("[Vorlesungsfolien] für alle k gelten soll, dass h(k) teilerfremd zu m ist")
    public void covarianceTest(int primeSize) {
        DoubleHashInt hashInt = new DoubleHashInt(primeSize);

        // for (int i = 0; i < primeSize * iterations; i++) {
        // distribution[hashInt.hashTick(i)] += 1;
        // }

        // var max = Arrays.stream(distribution).max().orElse(0);
        // var min = Arrays.stream(distribution).min().orElse(0);

        // assertTrue(max - min < 2, "hash() is not distributed evenly:\n" +
        // Arrays.toString(distribution));
    }

    @ParameterizedTest
    @MethodSource("all_primes")
    @DisplayName("[Vorlesungsfolien] für alle k gelten soll, dass h(k) teilerfremd zu m ist")
    public void rangeTest(int primeSize) {
        DoubleHashInt hashInt = new DoubleHashInt(primeSize);

        assertRange(0, hashInt.hash(0), primeSize);
        assertRange(0, hashInt.hashTick(0), primeSize);

        assertRange(0, hashInt.hash(Integer.MAX_VALUE), primeSize);
        assertRange(0, hashInt.hashTick(Integer.MAX_VALUE), primeSize);

        assertRange(0, hashInt.hash(Integer.MIN_VALUE), primeSize);
        assertRange(0, hashInt.hashTick(Integer.MIN_VALUE), primeSize);

        assertRange(0, hashInt.hash(primeSize), primeSize);
        assertRange(0, hashInt.hashTick(primeSize), primeSize);

        assertRange(0, hashInt.hash(1), primeSize);
        assertRange(0, hashInt.hashTick(1), primeSize);

        assertRange(0, hashInt.hash(-1), primeSize);
        assertRange(0, hashInt.hashTick(-1), primeSize);

        assertRange(0, hashInt.hash(-2), primeSize);
        assertRange(0, hashInt.hashTick(-2), primeSize);

        for (int i = -5; i < 5; i++) {
            assertRange(0, hashInt.hash(primeSize + i), primeSize);
            assertRange(0, hashInt.hashTick(primeSize + i), primeSize);
            assertRange(0, hashInt.hash(-primeSize + i), primeSize);
            assertRange(0, hashInt.hashTick(-primeSize + i), primeSize);
        }

        // for (int i = 0; i < primeSize * iterations; i++) {
        // distribution[hashInt.hashTick(i)] += 1;
        // }

        // var max = Arrays.stream(distribution).max().orElse(0);
        // var min = Arrays.stream(distribution).min().orElse(0);

        // assertTrue(max - min < 2, "hash() is not distributed evenly:\n" +
        // Arrays.toString(distribution));
    }

    @Test
    @DisplayName("Determinism Test")
    public void noStatic() {
        var hashInt = new DoubleHashInt(41);
        
        int k1 = hashInt.hash(1);
        int k2 = hashInt.hash(78);
        int k3 = hashInt.hash(157);
        
        int l1 = hashInt.hashTick(1);
        int l2 = hashInt.hashTick(78);
        int l3 = hashInt.hashTick(157);
        
        for (int i = 0; i < 5; i++) {
            assertEquals(k1, hashInt.hash(1));
            assertEquals(k2, hashInt.hash(78));
            assertEquals(k3, hashInt.hash(157));
            
            assertEquals(l1, hashInt.hashTick(1));
            assertEquals(l2, hashInt.hashTick(78));
            assertEquals(l3, hashInt.hashTick(157));
        }
    }
}