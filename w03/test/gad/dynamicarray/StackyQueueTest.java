package gad.dynamicarray;

import gad.dynamicarray.StackyQueue;
import gad.dynamicarray.StudentResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackyQueueTest {

    void checkQueueFirst(StackyQueue sq, int[] elementsShould1, String... errorMsg) throws NoSuchFieldException,
            IllegalAccessException {
        ArrayHelper.checkUnderlyingArrayFirst(elementsShould1, sq, errorMsg);
    }
    void checkQueueSecond(StackyQueue sq, int[] elementsShould1, String... errorMsg) throws NoSuchFieldException,
            IllegalAccessException {
        ArrayHelper.checkUnderlyingArraySecond(elementsShould1, sq, errorMsg);
    }

    @Test
    void artemisTest() throws NoSuchFieldException, IllegalAccessException {
        var sq = new StackyQueue(3,4,new StudentResult(),new StudentResult());

        checkQueueFirst(sq, new int[]{}, "First stack should initially be empty");
        checkQueueSecond(sq, new int[]{}, "Second stack should initially be empty");

        sq.pushBack(1);
        checkQueueFirst(sq, new int[]{1, 0, 0},"just pushed 1");
        checkQueueSecond(sq, new int[]{}, "Second stack should be empty");

        sq.pushBack(2);
        checkQueueFirst(sq, new int[]{1, 2, 0},"just pushed 2");
        checkQueueSecond(sq, new int[]{}, "Second stack should be empty");

        sq.pushBack(3);
        checkQueueFirst(sq, new int[]{1, 2, 3},"just pushed 3");
        checkQueueSecond(sq, new int[]{}, "Second stack should be empty");

        sq.pushBack(4);
        checkQueueFirst(sq, new int[]{1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0},"just pushed 4; queue should grow");
        checkQueueSecond(sq, new int[]{}, "Second stack should be empty");

        assertEquals(1, sq.popFront());
        checkQueueFirst(sq, new int[]{},"first stack should be empty");
        checkQueueSecond(sq, new int[]{4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0}, "pop should copy first stack into second and revers intervall");

        assertEquals(2, sq.popFront());
        checkQueueFirst(sq, new int[]{},"first stack should be empty");
        checkQueueSecond(sq, new int[]{4, 3, 0, 0, 0, 0}, "pop should shrink the second stack");

        assertEquals(3, sq.popFront());
        checkQueueFirst(sq, new int[]{},"first stack should be empty");
        checkQueueSecond(sq, new int[]{4, 0, 0}, "pop should shrink the second stack");

        sq.pushBack(5);
        checkQueueFirst(sq, new int[]{5, 0, 0},"just pushed 5");
        checkQueueSecond(sq, new int[]{4, 0, 0}, "push should not change the second stack");

        sq.pushBack(6);
        checkQueueFirst(sq, new int[]{5, 6, 0},"just pushed 6");
        checkQueueSecond(sq, new int[]{4, 0, 0}, "push should not change the second stack");

        assertEquals(4, sq.popFront());
        checkQueueFirst(sq, new int[]{5, 6, 0},"pop should not change the first stack");
        checkQueueSecond(sq, new int[]{}, "pop should shrink the second stack, to be empty");

        assertEquals(5, sq.popFront());
        checkQueueFirst(sq, new int[]{},"first stack should be empty");
        checkQueueSecond(sq, new int[]{6, 5, 0}, "pop should copy first stack into second and revers intervall");
    }
}
