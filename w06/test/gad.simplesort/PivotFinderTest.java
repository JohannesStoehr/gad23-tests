package gad.bettersorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PivotFinderTest {

    @Test
    void testMidPivot() {
        PivotFinder pivotFinder = PivotFinder.getMidPivot();
        int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(4, pivotFinder.findPivot(numbers, 0, 8));
        assertEquals(1, pivotFinder.findPivot(numbers, 0, 2));
        assertEquals(7, pivotFinder.findPivot(numbers, 6, 8));
        assertEquals(5, pivotFinder.findPivot(numbers, 4, 6));
    }

    @Test
    void testRandomPivot() {
        PivotFinder pivotFinder = PivotFinder.getRandomPivot(420);
        int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(3, pivotFinder.findPivot(numbers, 0, 8));
        assertEquals(6, pivotFinder.findPivot(numbers, 4, 6));
    }

    @Test
    void testMedianPivotFrontArtemis() {
        PivotFinder pivotFinder = PivotFinder.getMedianPivotFront(3);
        int[] numbers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(1, pivotFinder.findPivot(numbers, 0, 9));
        pivotFinder = PivotFinder.getMedianPivotFront(5);
        numbers = new int[] {9, 1, 8, 5, 2, 3, 5, 1, 0, 7};
        assertEquals(3, pivotFinder.findPivot(numbers, 0, 9));
        int pivot = pivotFinder.findPivot(numbers, 2, 8);
        assertTrue(pivot == 3 || pivot == 6, "Expected pivot at index 3 or 6.");
    }

    @Test
    void testMedianPivotFront() {
        PivotFinder pivotFinder = PivotFinder.getMedianPivotFront(5);
        int[] numbers = new int[] {0, 1, 2, 3};
        int pivot = pivotFinder.findPivot(numbers, 0, 4);
        assertTrue(1 == pivot || 2 == pivot, "Expected pivot at index 1 or 2");
    }

    @Test
    void testMedianPivotDistributedArtemis() {
        PivotFinder pivotFinder = PivotFinder.getMedianPivotDistributed(3);
        int[] numbers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(4, pivotFinder.findPivot(numbers, 0, 9));
        pivotFinder = PivotFinder.getMedianPivotDistributed(5);
        numbers = new int[] {9, 1, 8, 5, 2, 3, 5, 1, 0, 7};
        assertEquals(6, pivotFinder.findPivot(numbers, 0, 9));
        assertEquals(5, pivotFinder.findPivot(numbers, 1, 9));
    }

    @Test
    void testMedianPivotDistributed() {
        PivotFinder pivotFinder = PivotFinder.getMedianPivotDistributed(5);
        int[] numbers = new int[] {0, 1, 2, 3};
        assertEquals(1, pivotFinder.findPivot(numbers, 0, 4));
    }
}
