package gad.binomilia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    /**
     * This test is just an indicator if the code can be fast enough.
     * The task on Artemis states that the code must complete 5,000 operations in under 2 seconds.
     * The 700 millisec comes from <a href = "https://zulip.in.tum.de/#narrow/stream/1727-GAD-W08-Binomial-Heap/topic/Timeout/near/1057435">this Zulip</a> message.
     * That's why I'm testing here with these 700 millisec.
     *
     */

    @Test
    @Timeout(value = 700, unit = TimeUnit.MILLISECONDS)
    void performanceTest1()
    {
        Random random = new Random(400);
        for(int i=0;i<5_000;i++)
        {
            heap.insert(random.nextInt(),new StudentResult());
        }
    }

    @Test
    @Timeout(value = 700, unit = TimeUnit.MILLISECONDS)
    void performanceTest2()
    {
        Random random = new Random(400);
        for(int i=0;i<2_500;i++)
        {
            heap.insert(random.nextInt(),new StudentResult());
        }

        for(int i=2_500;i>0;i--)
        {
            heap.deleteMin(new StudentResult());
        }
    }

    @Test
    @Timeout(value = 700, unit = TimeUnit.MILLISECONDS)
    void performanceTest3()
    {
        Random random = new Random(400);
        for(int i=0;i<1_250;i++)
        {
            heap.insert(random.nextInt(),new StudentResult());
        }

        for(int i=1_000;i>0;i--)
        {
            heap.deleteMin(new StudentResult());
        }

        for(int i=0;i<1_250;i++)
        {
            heap.insert(random.nextInt(),new StudentResult());
        }

        for(int i=1_500;i>0;i--)
        {
            heap.deleteMin(new StudentResult());
        }
    }
}
