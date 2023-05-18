package gad.simplehash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertRemoveTest {

    // --- Insert --- //

    private static Stream<Arguments> testInsert() {
        return Stream.of(
                Arguments.of(1, new int[]{1}, new String[]{"x"}, new String[]{"foo"}, new List[]{
                        new ArrayList<Pair<String, String>>() {
                            {
                                add(new Pair<String, String>("x", "foo"));
                            }
                        }}),
                Arguments.of(1, new int[]{1}, new String[]{"x", "y"}, new String[]{"foo", "bar"},
                        new List[]{new ArrayList<Pair<String, String>>() {
                            {
                                add(new Pair<String, String>("x", "foo"));
                                add(new Pair<String, String>("y", "bar"));
                            }
                        }})

        );

    }

    @ParameterizedTest
    @MethodSource
    public void testInsert(int minSize, int[] a, String[] k, String[] v, List<Pair<String, String>>[] expect) {
        Hashtable<String, String> hashtable = new Hashtable<>(minSize, a);

        ModuloHelper mH = (i, divisor) -> i % divisor;

        for (int i = 0; i < k.length; i++) {
            hashtable.insert(k[i], v[i], mH);
        }

        List<Pair<String, String>>[] table = hashtable.getTable();

        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], table[i]);
        }

    }

    // --- Remove --- //

    private static Stream<Arguments> testRemove() {
        return Stream.of(
                Arguments.of(1, new int[]{1}, "x", false, new List[]{new ArrayList<Pair<String, String>>()},
                        new List[]{new ArrayList<Pair<String, String>>()}),
                Arguments.of(1, new int[]{1}, "x", true, new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                    }
                }}, new List[]{new ArrayList<Pair<String, String>>()}),
                Arguments.of(1, new int[]{1}, "x", true, new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("y", "bar"));
                    }
                }}),
                Arguments.of(1, new int[]{1}, new String("x"), true, new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("x", "foo"));
                        add(new Pair<>("y", "bar"));
                    }
                }}, new List[]{new ArrayList<Pair<String, String>>() {
                    {
                        add(new Pair<>("y", "bar"));
                    }
                }})
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testRemove(int minSize, int[] a, String k, boolean expect, List<Pair<String, String>>[] before,
                           List<Pair<String, String>>[] after) throws Exception {
        Hashtable<String, String> hashtable = new Hashtable<>(minSize, a);

        ModuloHelper mH = (i, divisor) -> i % divisor;

        Field tableField = hashtable.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        tableField.set(hashtable, before);

        assertEquals(expect, hashtable.remove(k, mH));

        List<Pair<String, String>>[] table = hashtable.getTable();

        for (int i = 0; i < after.length; i++) {
            assertEquals(after[i], table[i]);
        }

    }

}
