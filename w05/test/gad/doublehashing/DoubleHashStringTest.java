package gad.doublehashing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DoubleHashStringTest {
    private static final int primeSize = 31;

    private static Stream<String> words() throws IOException {
        return Files.lines(Path.of("test/gad/doublehashing/words.txt"), StandardCharsets.UTF_8);
    }

    @Nested
    class SameNumberTest {
        DoubleHashString hashString = new DoubleHashString(primeSize);

        @RepeatedTest(100)
        public void sameNumberTest(RepetitionInfo info) {
            var i = "" + info.getCurrentRepetition();

            assertEquals(hashString.hash(i), hashString.hash(i));
            assertEquals(hashString.hashTick(i), hashString.hashTick(i));
        }
    }

    @Nested
    public class DistributionTest {
        DoubleHashString hashString = new DoubleHashString(17);
        int[] distribution = new int[17];

        @Test
        public void distributionTest() throws IOException {
            words().forEach(word -> distribution[hashString.hash(word)]++);
            
            var max = Arrays.stream(distribution).max().orElse(0);
            var min = Arrays.stream(distribution).min().orElse(0);

            assertTrue(max - min < 100, "hash() is not distributed evenly");

        }
    }
}