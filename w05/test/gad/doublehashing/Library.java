package gad.doublehashing;

import static org.junit.jupiter.api.Assertions.fail;

public class Library {
    public static void assertRange(int from, int value, int to) {
        if (from > value || value >= to) {
            fail(String.format("Illegal Value! %s not in range (%s; %s]", value, from, to));
        }
    }
}
