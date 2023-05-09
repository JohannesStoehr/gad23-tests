package gad.dynamicarray.array;

import gad.dynamicarray.Interval;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import gad.dynamicarray.DynamicArray;
import gad.dynamicarray.Interval.EmptyInterval;
import gad.dynamicarray.Interval.NonEmptyInterval;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DynamicArrayTest {
    @Test
    @DisplayName("Artemis Example")
    void testArtemis() {
        DynamicArray array = new DynamicArray(3, 4);
        assertEquals(Interval.EmptyInterval.getEmptyInterval(), array.reportUsage(EmptyInterval.getEmptyInterval(), 1));
        assertEquals("[0, 0, 0]", array.toString());

        array.set(2, 1);
        assertEquals("[0, 0, 1]", array.toString());
        assertEquals(0, array.get(1));

        array.set(0, 3);
        assertEquals("[3, 0, 1]", array.toString());

        assertEquals(new NonEmptyInterval(0, 1), array.reportUsage(new NonEmptyInterval(1,2), 4));
        assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", array.toString());

        assertEquals(new NonEmptyInterval(3, 6), array.reportUsage(new NonEmptyInterval(3,6), 4));
        assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", array.toString());

        assertEquals(new NonEmptyInterval(0,0), array.reportUsage(new NonEmptyInterval(1,1), 1));
        assertEquals("[1, 0, 0]", array.toString());

        assertEquals(EmptyInterval.getEmptyInterval(), array.reportUsage(EmptyInterval.getEmptyInterval(), 2));
        assertEquals("[1, 0, 0]", array.toString());

        assertEquals(new NonEmptyInterval(0, 1), array.reportUsage(new NonEmptyInterval(2,0), 4));
        assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", array.toString());

        assertEquals(new NonEmptyInterval(5, 1), array.reportUsage(new NonEmptyInterval(5,1), 9));
        assertEquals("[0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", array.toString());

        assertEquals(EmptyInterval.getEmptyInterval(), array.reportUsage(EmptyInterval.getEmptyInterval(), 0));
        assertEquals("[]", array.toString());
    }

    @Test
    @DisplayName("Throws IllegalArgumentException")
    void throwsTest() {
        assertThrows(IllegalArgumentException.class, () -> new DynamicArray(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new DynamicArray(5, -5));
        assertThrows(IllegalArgumentException.class, () -> new DynamicArray(2, 1));
    }
}
