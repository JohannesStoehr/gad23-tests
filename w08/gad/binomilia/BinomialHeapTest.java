package gad.binomilia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinomialHeapTest {

    private BinomialHeap heap;
    private StudentResultTest studentResult;

    @BeforeEach
    void init()
    {
        heap = new BinomialHeap();
        studentResult = new StudentResultTest();
    }

    @Test
    void exceptionTest()
    {
        assertThrows(RuntimeException.class, ()-> heap.min(), "Please throw an exception as required by the Artemis quest, " +
                "because there can't be a minimal element in an empty heap.");
        assertThrows(RuntimeException.class, ()-> heap.deleteMin(studentResult),"Please throw an exception as required by the Artemis quest, " +
                "because you can't delete anything from an empty list." );
    }

    @Test
    void minTest()
    {
        for(int i=0;i<500;i++)
        {
            heap.insert(i,studentResult);
            assertEquals(0,heap.min(), "Wrong value for min!");
        }
    }

    @Test
    void testDeleteMin() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            heap.insert(i, studentResult);
            nums.add(i);
        }

        for (int x : nums) {
            assertEquals(x, heap.min(), "Wrong value for min!");
            int deleted = heap.deleteMin(studentResult);
            assertEquals(x, deleted,
                    "Either did not delete the smallest number or the expected number wasnt in the heap");
        }
    }

    @Test
    void testBigNumbers() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int num = new Random().nextInt();
            nums.add(num);
            heap.insert(num, studentResult);
            int minList = nums.stream().min(Integer::compare).get();
            int minHeap = heap.min();
            assertEquals(minList, minHeap, "Wrong minimum " + i);
        }
        for (int i = 0; i < 500; i++) {
            int num = new Random().nextInt();
            nums.add(num);
            heap.insert(num, studentResult);
            Integer min = nums.stream().min(Integer::compare).get();
            nums.remove(min);
            assertEquals(min, heap.deleteMin(studentResult), "Wrong number removed " + i);
        }
    }

    @Test
    void testAmountOFTrees() {
        List<Integer> nums = new ArrayList<>();
        TreeCounterResult myResult = new TreeCounterResult();

        for (int i = 0; i < 500; i++) {
            int num = new Random().nextInt();
            nums.add(num);
            heap.insert(num, myResult);
            int expectedSize = Integer.bitCount(nums.size());
            int trees = myResult.getSize();
            assertEquals(expectedSize, trees, "Wrong amount of Trees in heap. If only this test fails, "
                    + "check if there is an Integer-Overflow, since these Numbers are pretty big.");
        }
    }
}
