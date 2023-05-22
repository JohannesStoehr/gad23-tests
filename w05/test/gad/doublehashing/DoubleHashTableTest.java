package gad.doublehashing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;

import static gad.doublehashing.DoubleHashIntTest.assertRange;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class DoubleHashTableTest {
    DoubleHashTable<Integer, Integer> hashTable = new DoubleHashTable<Integer, Integer>(49_999,
            new IntHashableFactory());

    @Timeout(1)
    @Order(1)
    @Test
    public void performanceInsert() {
        for (int i = 0; i < 49_999; i++) {
            assertTrue(hashTable.insert(i, i));
        }
    }

    @Timeout(1)
    @Order(2)
    @Test
    public void performanceFind() {
        for (int i = 0; i < 50_000_000; i++) {
            hashTable.find(i % 49999);
        }
        System.out.println(hashTable.collisions());

    }

    @Test
    @DisplayName("For weird reasons this test is also associated with performance.")
    public void overflowTest() {
        var hashTable = new DoubleHashTable<>(49_999, new IntHashableFactory());
        assertRange(0, hashTable.hash(50_000, 42_954), Integer.MAX_VALUE);
    }
}