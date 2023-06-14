package gad.dynamicarray;

import gad.dynamicarray.RingQueue;
import gad.dynamicarray.StudentResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingQueueTest {


    void checkQueue(RingQueue rq, int[] elementsShould, String... errorMsg) throws NoSuchFieldException,
            IllegalAccessException {
        ArrayHelper.checkUnderlyingArray(elementsShould, rq, errorMsg);
    }

    @Test
    void artemisTest() throws NoSuchFieldException, IllegalAccessException {
        var rq = new RingQueue(3, 4, new StudentResult());

        checkQueue(rq, new int[]{}, "Stack should initially be empty");

        rq.pushBack(1);
        checkQueue(rq, new int[]{1, 0, 0}, "just pushed 1");

        rq.pushBack(2);
        checkQueue(rq, new int[]{1, 2, 0}, "just pushed 2");

        rq.pushBack(3);
        checkQueue(rq, new int[]{1, 2, 3}, "just pushed 3");

        rq.pushBack(4);
        checkQueue(rq, new int[]{1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0}, "just pushed 4; queue should grow");

        assertEquals(1, rq.popFront());
        checkQueue(rq, new int[]{1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0}, "pop should not change the stack");

        assertEquals(2, rq.popFront());
        checkQueue(rq, new int[]{3, 4, 0, 0, 0, 0}, "pop should shrink the stack");

        assertEquals(3, rq.popFront());
        checkQueue(rq, new int[]{4, 0, 0}, "pop should shrink the stack");

        rq.pushBack(5);
        checkQueue(rq, new int[]{4, 5, 0}, "just pushed 5");

        rq.pushBack(6);
        checkQueue(rq, new int[]{4, 5, 6}, "just pushed 6");

        assertEquals(4, rq.popFront());
        checkQueue(rq, new int[]{4, 5, 6}, "pop should not change the stack");

        rq.pushBack(7);
        checkQueue(rq, new int[]{7, 5, 6}, "just pushed 7");

        rq.pushBack(8);
        checkQueue(rq, new int[]{5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0,
                0}, "just pushed 8; stack should grow, and be relocated to the beginning");

    }


    @Test
    void addAndRemove() throws NoSuchFieldException, IllegalAccessException {
        var rq = new RingQueue(3, 4, new StudentResult());

        for (int i = 0; i < 10; i++) {
            rq.pushBack(i);
        }

        checkQueue(rq, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0}, "just pushed 10 elements");

        for (int i = 0; i < 10; i++) {
            assertEquals(i, rq.popFront());
        }

        checkQueue(rq, new int[]{}, "removed all elements");
    }


    @Test
    void fillAndEmptyAgain() throws NoSuchFieldException, IllegalAccessException {
        var rq = new RingQueue(3, 4, new StudentResult());

        //fill the queue
        rq.pushBack(4);
        rq.pushBack(5);
        rq.pushBack(6);

        checkQueue(rq, new int[]{4, 5, 6}, "just pushed 3 elements");

        //begin to empty the queue
        assertEquals(4, rq.popFront());
        checkQueue(rq, new int[]{4, 5, 6}, "no resize; array should stay the same");

        assertEquals(5, rq.popFront());
        checkQueue(rq, new int[]{4, 5, 6}, "no resize; array should stays the same");

        assertEquals(6, rq.popFront());
        checkQueue(rq, new int[]{}, "resized; array should be empty");
    }

    @Test
    void wrapAround() throws NoSuchFieldException, IllegalAccessException {
        var rq = new RingQueue(3, 4, new StudentResult());

        rq.pushBack(1);
        checkQueue(rq, new int[]{1, 0, 0});

        rq.pushBack(2);
        checkQueue(rq, new int[]{1, 2, 0});

        assertEquals(1, rq.popFront());
        checkQueue(rq, new int[]{1, 2, 0});

        rq.pushBack(3);
        checkQueue(rq, new int[]{1, 2, 3});

        rq.pushBack(4);
        checkQueue(rq, new int[]{4, 2, 3}, "pushing should wrap around");

        assertEquals(2, rq.popFront());
        checkQueue(rq, new int[]{4, 2, 3});

        rq.pushBack(5);
        checkQueue(rq, new int[]{4, 5, 3});

        assertEquals(3, rq.popFront());
        checkQueue(rq, new int[]{4, 5, 3});

        rq.pushBack(6);
        checkQueue(rq, new int[]{4, 5, 6});

        assertEquals(4, rq.popFront(),"popping should wrap around");
        checkQueue(rq, new int[]{4, 5, 6});
    }
}