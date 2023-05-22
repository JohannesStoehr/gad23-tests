// package gad.doublehashing;

// import static org.junit.jupiter.api.Assertions.*;

// import java.lang.reflect.Field;
// import java.nio.charset.StandardCharsets;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.Arrays;
// import java.util.stream.IntStream;
// import java.util.stream.Stream;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.RepeatedTest;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.RepetitionInfo;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.MethodSource;

// public class DoubleHashStringTest {
//     private static final int primeSize = 31;

//     DoubleHashString hashString = new DoubleHashString(primeSize);

//     private static Stream<String> words() {
//         return Files.lines(Path.of("test/gad/doublehashing/words.txt"), StandardCharsets.UTF_8)
//     }

//     private static void assertRange(int from, int value, int to) {
//         if (from > value || value >= to) {
//             fail(String.format("Illegal Value! %s not in range (%s; %s]", value, from, to));
//         }
//     }

//     @Nested
//     class SameNumberTest {
//         DoubleHashString hashString = new DoubleHashString(primeSize);

//         @RepeatedTest(100)
//         public void sameNumberTest(RepetitionInfo info) {
//             var i = "" + info.getCurrentRepetition();

//             assertEquals(hashString.hash(i), hashString.hash(i));
//             assertEquals(hashString.hashTick(i), hashString.hashTick(i));
//         }
//     }

//     @ParameterizedTest
//     @MethodSource("words")
//     public void distributionTest(String word) {
//         int[] distribution = new int[primeSize];

//         for (int i = 0; i < primeSize * iterations; i++) {
//             distribution[hashString.hash(i)] += 1;
//         }

//         var max = Arrays.stream(distribution).max().orElse(0);
//         var min = Arrays.stream(distribution).min().orElse(0);

//         assertTrue(max - min < 2, "hash() is not distributed evenly");
//     }

//     @ParameterizedTest
//     @MethodSource("small_primes")
//     public void distributionTickTest(int primeSize) {
//         DoubleHashString hashString = new DoubleHashString(primeSize);
//         int[] distribution = new int[primeSize];

//         for (int i = 0; i < primeSize * iterations; i++) {
//             distribution[hashString.hashTick(i)] += 1;
//         }

//         var max = Arrays.stream(distribution).max().orElse(0);
//         var min = Arrays.stream(distribution).filter(i -> i > 0).min().orElse(0);

//         if (max - min > 1) {
//             fail("hash() is not distributed evenly:\n" + Arrays.toString(distribution));
//         }
//     }

//     @ParameterizedTest
//     @MethodSource("small_primes")
//     @DisplayName("[Vorlesungsfolien] für alle k gelten soll, dass h(k) teilerfremd zu m ist")
//     public void covarianceTest(int primeSize) {
//         DoubleHashString hashString = new DoubleHashString(primeSize);

//         // for (int i = 0; i < primeSize * iterations; i++) {
//         // distribution[hashString.hashTick(i)] += 1;
//         // }

//         // var max = Arrays.stream(distribution).max().orElse(0);
//         // var min = Arrays.stream(distribution).min().orElse(0);

//         // assertTrue(max - min < 2, "hash() is not distributed evenly:\n" +
//         // Arrays.toString(distribution));
//     }

//     @ParameterizedTest
//     @MethodSource("all_primes")
//     @DisplayName("[Vorlesungsfolien] für alle k gelten soll, dass h(k) teilerfremd zu m ist")
//     public void rangeTest(int primeSize) {
//         DoubleHashString hashString = new DoubleHashString(primeSize);

//         assertRange(0, hashString.hash(0), primeSize);
//         assertRange(0, hashString.hashTick(0), primeSize);

//         assertRange(0, hashString.hash(Integer.MAX_VALUE), primeSize);
//         assertRange(0, hashString.hashTick(Integer.MAX_VALUE), primeSize);

//         assertRange(0, hashString.hash(Integer.MIN_VALUE), primeSize);
//         assertRange(0, hashString.hashTick(Integer.MIN_VALUE), primeSize);

//         assertRange(0, hashString.hash(primeSize), primeSize);
//         assertRange(0, hashString.hashTick(primeSize), primeSize);

//         assertRange(0, hashString.hash(1), primeSize);
//         assertRange(0, hashString.hashTick(1), primeSize);

//         assertRange(0, hashString.hash(-1), primeSize);
//         assertRange(0, hashString.hashTick(-1), primeSize);

//         assertRange(0, hashString.hash(-2), primeSize);
//         assertRange(0, hashString.hashTick(-2), primeSize);

//         for (int i = -5; i < 5; i++) {
//             assertRange(0, hashString.hash(primeSize + i), primeSize);
//             assertRange(0, hashString.hashTick(primeSize + i), primeSize);
//             assertRange(0, hashString.hash(-primeSize + i), primeSize);
//             assertRange(0, hashString.hashTick(-primeSize + i), primeSize);
//         }

//         // for (int i = 0; i < primeSize * iterations; i++) {
//         // distribution[hashString.hashTick(i)] += 1;
//         // }

//         // var max = Arrays.stream(distribution).max().orElse(0);
//         // var min = Arrays.stream(distribution).min().orElse(0);

//         // assertTrue(max - min < 2, "hash() is not distributed evenly:\n" +
//         // Arrays.toString(distribution));
//     }

//     @Test
//     @DisplayName("Determinism Test")
//     public void noStatic() {
//         var hashString = new DoubleHashString(41);

//         int k1 = hashString.hash(1);
//         int k2 = hashString.hash(78);
//         int k3 = hashString.hash(157);

//         int l1 = hashString.hashTick(1);
//         int l2 = hashString.hashTick(78);
//         int l3 = hashString.hashTick(157);

//         for (int i = 0; i < 5; i++) {
//             assertEquals(k1, hashString.hash(1));
//             assertEquals(k2, hashString.hash(78));
//             assertEquals(k3, hashString.hash(157));

//             assertEquals(l1, hashString.hashTick(1));
//             assertEquals(l2, hashString.hashTick(78));
//             assertEquals(l3, hashString.hashTick(157));
//         }
//     }
// }