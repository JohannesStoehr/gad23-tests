package gad.simplehash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindFindAllTest {
    // --- Find --- //

    private static Stream<Arguments> testFind() {
        return Stream.of(
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>()}, null),
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                    }
                }}, "foo"),
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, "foo"),
                Arguments.of(1, new int[]{1}, "y", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, "bar"),

                Arguments.of(1, new int[]{1}, new String("y"), new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, "bar")

        );

    }

    @ParameterizedTest
    @MethodSource
    public void testFind(int minSize, int[] a, String k, List<Pair<String, String>>[] table, String expect)
            throws Exception {
        Hashtable<String, String> hashtable = new Hashtable<>(minSize, a);

        ModuloHelper mH = (i, divisor) -> i % divisor;

        Field tableField = hashtable.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        tableField.set(hashtable, table);

        Optional<String> result = hashtable.find(k, mH);

        if (expect == null) {
            assertEquals(Optional.empty(), result);
        } else {
            assertEquals(Optional.of(expect), result);
        }
    }

    // --- FindAll --- //

    private static Stream<Arguments> testFindAll() {
        return Stream.of(
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>()},
                        new String[]{}),
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                    }
                }}, new String[]{"foo"}),
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, new String[]{"foo"}),
                Arguments.of(1, new int[]{1}, "y", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, new String[]{"bar"}),
                Arguments.of(1, new int[]{1}, "z", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, new String[]{}),
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("x", "bar"));
                    }
                }}, new String[]{"foo", "bar"}),
                Arguments.of(1, new int[]{1}, "x", new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("x", "bar"));
                        add(new Pair<>("y", "baz"));
                    }
                }}, new String[]{"foo", "bar"}),

                Arguments.of(1, new int[]{1}, new String("x"), new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("x", "bar"));
                        add(new Pair<>("y", "baz"));
                    }
                }}, new String[]{"foo", "bar"})

        );

    }

    @ParameterizedTest
    @MethodSource
    public void testFindAll(int minSize, int[] a, String k, List<Pair<String, String>>[] table, String[] expect)
            throws Exception {
        Hashtable<String, String> hashtable = new Hashtable<>(minSize, a);

        ModuloHelper mH = (i, divisor) -> i % divisor;

        Field tableField = hashtable.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        tableField.set(hashtable, table);

        List<String> result = hashtable.findAll(k, mH);

        assertEquals(Arrays.asList(expect), result);
    }
}
