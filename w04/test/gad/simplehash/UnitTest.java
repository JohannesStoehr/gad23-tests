package gad.dynamicarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

// import java.util.Arrays;

import org.junit.jupiter.api.Test;

//
// HALT STOPP! LESEN!
//
// TESTS SIND GE'SPEEDRUN'T UND OHNE QUALITÄT VERFASST
// DISABLED = ICH [=VERFASSER] PASSE ARTEMIS TESTS [=VIELLEICHT] ABER TESTS NICHT [=SICHER]
// KEINE GARANTIE!!!!
//

// Personal Unit Tests

public class UnitTest {
    //
    // <region help>
    //

    private static TDA Arr(int growthFactor, int maxOverhead) {
        return new TDA(growthFactor, maxOverhead);
    }

    private static TDS Stck(int growthFactor, int maxOverhead) {
        return new TDS(growthFactor, maxOverhead);
    }

    private static TDQ Queue(int growthFactor, int maxOverhead) {
        return new TDQ(growthFactor, maxOverhead);
    }

    private static TDSQ StkQu(int growthFactor, int maxOverhead) {
        return new TDSQ(growthFactor, maxOverhead);
    }

    private static int[] Ints(int... args) {
        return args;
    }

    private static Interval.EmptyInterval Ival() {
        return Interval.EmptyInterval.getEmptyInterval();
    }

    private static Interval.NonEmptyInterval Ival(int from, int to) {
        return new Interval.NonEmptyInterval(from, to);
    }

    // Test Dynamic Array Test Result
    private static class TDATR implements Result {
        public int[] array;

        public void logArray(int[] array) {
            this.array = array;
        }
    }

    // Test Dynamic Array
    private static class TDA extends DynamicArray {

        // Test Dynamic Array
        public TDA(int growthFactor, int maxOverhead) {
            super(growthFactor, maxOverhead);
        }

        /**
         * args = ( [index, value], ... )
         */
        public TDA sets(int... args) {
            for (int i = 0; i < args.length; i += 2) {
                super.set(args[i], args[i + 1]);
            }
            return this;
        }

        public TDA nest(Interval i, int m) {
            super.reportUsage(i, m);
            return this;
        }

        public TDA nest(Interval i, int m, Interval expect) {
            assertEquals(expect, super.reportUsage(i, m));
            return this;
        }

        public TDA test(int[] expected) {
            assertArrayEquals(expected, getArray());
            return this;
        }

        public int[] getArray() {
            var t = new TDATR();
            this.reportArray(t);
            return t.array;
        }

        // public String getArrayString() {
        // var t = new TDATR();
        // this.reportArray(t);
        // return Arrays.toString(t.array);
        // }
    }

    private static class TestComponent<Data extends Collection> {
        public Data data;
        public TDA array;

        // Test Dynamic Array
        public TestComponent(int growthFactor, int maxOverhead) {
            array = new TDA(growthFactor, maxOverhead);
        }

        public void setChildArray() {
            try {
                Field f1 = data.getClass().getDeclaredField("array");
                f1.setAccessible(true);
                f1.set(data, array);
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                fail(e.getMessage());
            }
        }

        public TestComponent<Data> test(int[] expected) {
            assertArrayEquals(expected, getArray(), String.format("%nexpected:\t%s%ngot:\t%s%n",
                    Arrays.toString(expected), Arrays.toString(getArray())));
            return this;
        }

        public int[] getArray() {
            try {
                Field f1 = DynamicStack.class.getDeclaredField("array");
                f1.setAccessible(true);
                var k = (TDA) f1.get(data);
                return k.getArray();
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Test Dynamic Stack
    private static class TDS extends TestComponent<DynamicStack> {
        public TDS(int growthFactor, int maxOverhead) {
            super(growthFactor, maxOverhead);
            data = new DynamicStack(growthFactor, maxOverhead, new TDATR());
            setChildArray();
        }

        public TDS pushes(int... args) {
            for (int i = 0; i < args.length; i++) {
                data.pushBack(args[i]);
            }
            return this;
        }

        public TDS popCount(int count) {
            for (int i = 0; i < count; i++) {
                data.popBack();
            }
            return this;
        }

        public TDS pops(int... args) {
            for (int i = 0; i < args.length; i++) {
                assertEquals(args[i], data.popBack());
            }
            return this;
        }

        @Override
        public TDS test(int[] expected) {
            return (TDS) super.test(expected);
        }
    }

    // Test Dynamic (Ring) Queue
    private static class TDQ extends TestComponent<RingQueue> {
        // Test Dynamic Array
        public TDQ(int growthFactor, int maxOverhead) {
            super(growthFactor, maxOverhead);
            data = new RingQueue(growthFactor, maxOverhead, new TDATR());

            try {
                Field f1 = RingQueue.class.getDeclaredField("array");
                f1.setAccessible(true);
                f1.set(data, new TDA(growthFactor, maxOverhead));
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                fail(e.getMessage());
            }
        }

        public TDQ pushes(int... args) {
            for (int i = 0; i < args.length; i++) {
                data.pushBack(args[i]);
            }
            return this;
        }

        public TDQ popCount(int count) {
            for (int i = 0; i < count; i++) {
                data.popFront();
            }
            return this;
        }

        public TDQ pops(int... args) {
            for (int i = 0; i < args.length; i++) {
                assertEquals(args[i], data.popFront());
            }
            return this;
        }

        @Override
        public TDQ test(int[] expected) {
            return (TDQ) super.test(expected);
        }

        @Override
        public int[] getArray() {
            try {
                // System.out.println(Arrays.toString(DynamicStack.class.getDeclaredFields()));
                Field f1 = RingQueue.class.getDeclaredField("array");
                f1.setAccessible(true);
                var k = (TDA) f1.get(data);
                return k.getArray();
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Test Dynamic (Stacky) Queue
    private static class TDSQ extends StackyQueue {
        public TDS s1;
        public TDS s2;

        public TDSQ(int growthFactor, int maxOverhead) {
            super(growthFactor, maxOverhead, new TDATR(), new TDATR());

            s1 = new TDS(growthFactor, maxOverhead);
            s2 = new TDS(growthFactor, maxOverhead);

            try {
                Field f1 = StackyQueue.class.getDeclaredField("first");
                f1.setAccessible(true);
                f1.set(this, s1.data);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            try {
                Field f1 = StackyQueue.class.getDeclaredField("second");
                f1.setAccessible(true);
                f1.set(this, s2.data);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        public TDSQ pushes(int... args) {
            for (int i = 0; i < args.length; i++) {
                pushBack(args[i]);
            }
            return this;
        }

        public TDSQ popCount(int count) {
            for (int i = 0; i < count; i++) {
                popFront();
            }
            return this;
        }

        public TDSQ pops(int... args) {
            for (int i = 0; i < args.length; i++) {
                assertEquals(args[i], popFront());
            }
            return this;
        }

        public TDSQ test(int[] expected, int[] expected2) {
            s1.test(expected);
            s2.test(expected2);
            return this;
        }
    }

    //
    // </region help>
    //

    //
    // <region test driven tests>
    //

    @Test
    @DisplayName("Artemis Array Initialization")
    public void artemis1() {
        Arr(3, 4)
                .nest(Ival(), 1, Ival())
                .sets(2, 1, 0, 3)
                .test(Ints(3, 0, 1));
    }

    @Test
    public void artemis2() {
        Arr(3, 4)
                .nest(Ival(), 1)
                .sets(2, 1, 0, 3)
                .nest(Ival(1, 2), 4, Ival(0, 1))
                .test(Ints(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    @Disabled
    public void artemis3() {
        Arr(3, 4)
                .nest(Ival(), 1)
                .sets(2, 1, 0, 3)
                .nest(Ival(3, 6), 4, Ival(3, 6))
                .test(Ints(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void artemis4() {
        Arr(3, 4)
                .nest(Ival(), 1)
                .sets(2, 1, 0, 3)
                .nest(Ival(1, 2), 4)
                .nest(Ival(3, 6), 4)
                .nest(Ival(1, 1), 1, Ival(0, 0))
                .test(Ints(1, 0, 0));
    }

    @Test
    @Disabled
    public void artemis5() {
        Arr(3, 4)
                .nest(Ival(), 1)
                .sets(0, 1)
                .nest(Ival(2, 0), 4, Ival())
                .test(Ints(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void artemis6() {
        Arr(3, 4)
                .nest(Ival(), 1)
                .sets(0, 1)
                .nest(Ival(2, 0), 4)
                .nest(Ival(5, 1), 9, Ival(5, 1))
                .test(Ints(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void artemis7() {
        Arr(3, 4)
                .nest(Ival(), 1)
                .sets(0, 1)
                .nest(Ival(2, 0), 4)
                .nest(Ival(), 0, Ival())
                .test(Ints());
    }

    @Test
    public void jTest() {
        var arr = Arr(5, 7)
                .nest(Ival(), 100);
        assertEquals(500, arr.getArray().length);
        for (int i = 0; i < 500; i++) {
            arr.getArray()[i] = i;
        }
        var arr2 = arr.nest(Ival(10, 20), 50);
        assertEquals(250, arr2.getArray().length);
        for (int i = 0; i < 10; i++) {
            assertEquals(10 + i, arr2.getArray()[i]);
        }
    }

    @Test
    @DisplayName("Illegal Arguments in Constructor Test")
    public void arrayConstructor() {
        assertThrows(IllegalArgumentException.class, () -> Stck(5, 4));
        assertThrows(IllegalArgumentException.class, () -> Stck(10, 9));
        assertThrows(IllegalArgumentException.class, () -> Stck(100, 99));
        assertThrows(IllegalArgumentException.class, () -> Stck(101, 100));
        assertDoesNotThrow(() -> Stck(9, 10));
        assertDoesNotThrow(() -> Stck(99, 100));
        assertDoesNotThrow(() -> Stck(100, 101));
        assertDoesNotThrow(() -> Stck(101, 102));
        assertThrows(IllegalArgumentException.class, () -> Stck(-1, 2));
        assertDoesNotThrow(() -> Stck(1, 2));
    }

    @Test
    public void dynamic1() {
        Stck(3, 4)
                .test(Ints())
                .pushes(1, 2, 3)
                .test(Ints(1, 2, 3))
                .pops(3, 2)
                .test(Ints(1, 2, 3));
    }

    @Test
    public void dynamic2() {
        Stck(3, 4)
                .pushes(1, 2, 3, 4)
                .test(Ints(1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0))
                .pops(4)
                .test(Ints(1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0))
                .pushes(5)
                .test(Ints(1, 2, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void dynamic3() {
        Stck(3, 4)
                .pushes(1, 2, 3, 4)
                .pops(4)
                .pushes(5)
                .pops(5, 3)
                .test(Ints(1, 2, 0, 0, 0, 0));
    }

    @Test
    public void dynamicSize() {
        var arr = Stck(3, 4).pushes(1, 2, 3, 4, 5);
        assertEquals(5, arr.data.size());
    }

    @Test
    public void dynamicEmpty() {
        assertEquals(0, Stck(3, 4).getArray().length);
        for (int i = 0; i < 50; i += 10) {
            var s = Stck(3, 4)
                    .pushes(new int[i])
                    .pops(new int[i])
                    .test(Ints());
        }
    }

    @Test
    public void dynamicEmptyNotThrows() {
        assertDoesNotThrow(() -> Stck(3, 4).data.popBack());
        assertDoesNotThrow(() -> Stck(3, 4).pushes(1).pops(1).data.popBack());
        assertDoesNotThrow(() -> Stck(3, 4).pushes(1, 2, 3, 4).pops(4, 3, 2, 1).data.popBack());
    }

    @RepeatedTest(10)
    @Disabled // irrelevant
    public void testSameNumbersDynamicStack(RepetitionInfo repetitionInfo) {
        var i = 10 + repetitionInfo.getCurrentRepetition() * 20;
        final int c = 1; // 177369420;

        var s = Stck(5, 5).pushes();

        for (int j = 0; j < i; j++) {
            s = s.pushes(c);
            System.out.println(s.data.toString());
        }
        for (int j = 0; j < i; j++) {
            s = s.pops(c);
            System.out.println(s.data.toString());
        }

        final var k = s;
        assertDoesNotThrow(() -> k.popCount(1));
    }

    @Test
    public void queue1() {
        Queue(3, 4)
                .test(Ints())
                .pushes(1, 2, 3)
                .test(Ints(1, 2, 3))
                .pushes(4)
                .test(Ints(1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void queue2() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4, 5)
                .pops(1)
                .test(Ints(1, 2, 3, 4, 5, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void queue3() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4)
                .pops(1, 2)
                .test(Ints(3, 4, 0, 0, 0, 0));
    }

    @Test
    public void queue4() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4)
                .pops(1, 2, 3)
                .test(Ints(4, 0, 0));
    }

    @Test
    public void queue5() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4)
                .popCount(3)
                .pushes(5, 6)
                .test(Ints(4, 5, 6));
    }

    @Test
    public void queue6() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4)
                .popCount(3)
                .pushes(5, 6)
                .pops(4)
                .test(Ints(4, 5, 6));
    }

    @Test
    public void queue7() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4)
                .popCount(3)
                .pushes(5, 6)
                .pops(4)
                .pushes(7)
                .test(Ints(7, 5, 6));
    }

    @Test
    public void queue8() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4)
                .popCount(3)
                .pushes(5, 6)
                .pops(4)
                .pushes(7, 8)
                .test(Ints(5, 6, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void queuePlus() {
        Queue(3, 4)
                .pushes(1, 2, 3, 4)
                .popCount(3)
                .pushes(5, 6)
                .pops(4)
                .pushes(7, 8, 1, 3, 3, 7, 6, 9, 4, 2)
                .test(Ints(5, 6, 7, 8, 1, 3, 3, 7, 6, 9, 4, 2));
    }

    @Test
    public void queueZero() {
        assertDoesNotThrow(() -> Queue(3, 4).data.popFront());
        assertDoesNotThrow(() -> Queue(3, 4).pushes(1).pops(1).data.popFront());
        assertDoesNotThrow(() -> Queue(3, 4).pushes(1, 2, 3, 4).pops(1, 2, 3, 4).data.popFront());
    }

    @Test
    public void stackyQueue1() {
        StkQu(3, 4)
                .test(Ints(), Ints());
    }

    @Test
    public void stackyQueue2() {
        StkQu(3, 4)
                .pushes(1, 2, 3)
                .test(Ints(1, 2, 3), Ints())
                .pushes(4)
                .test(Ints(1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0), Ints());
    }

    @Test
    public void stackyQueue3() {
        StkQu(3, 4)
                .pushes(1, 2, 3, 4)
                .pops(1)
                .test(Ints(), Ints(4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0))
                .pops(2)
                .test(Ints(), Ints(4, 3, 0, 0, 0, 0))
                .pops(3)
                .test(Ints(), Ints(4, 0, 0));
    }

    @Test
    public void stackyQueue4() {
        StkQu(3, 4)
                .pushes(1, 2, 3, 4)
                .popCount(3)
                .pushes(5, 6)
                .test(Ints(5, 6, 0), Ints(4, 0, 0));
    }

    @Test
    public void stackyQueue5() {
        StkQu(3, 4)
                .pushes(1, 2, 3, 4)
                .popCount(3)
                .pushes(5, 6)
                .pops(4)
                .test(Ints(5, 6, 0), Ints())
                .pops(5)
                .test(Ints(), Ints(6, 5, 0));
    }

    //
    // </region test driven tests>
    //

    //
    // <region beauty help>
    //

    //
    // </region beauty tests>
    //

    //
    // <region beauty tests>
    //

    @Test
    public void testIntervals() {
        var d = Arr(150, 477);

        assertEquals(Ival(), d.reportUsage(Ival(), 10));
        assertArrayEquals(new int[1500], d.getArray());

        assertEquals(Ival(0, 10), d.reportUsage(Ival(0, 10), 10));

        assertEquals(Ival(0, 10), d.reportUsage(Ival(0, 10), 100));
        assertArrayEquals(new int[1500], d.getArray());

        assertEquals(Ival(0, 10), d.reportUsage(Ival(0, 10), 200));
        assertArrayEquals(new int[1500], d.getArray());

        assertEquals(Ival(0, 10), d.reportUsage(Ival(0, 10), 2000));
        assertArrayEquals(new int[300000], d.getArray());

        assertEquals(Ival(0, 1), d.reportUsage(Ival(0, 1), 500));
        assertArrayEquals(new int[75000], d.getArray());

        assertEquals(Ival(74950, 50), d.reportUsage(Ival(74950, 50), 500));
        assertArrayEquals(new int[75000], d.getArray());

        assertEquals(Ival(0, 100), d.reportUsage(Ival(74950, 50), 100));
        assertArrayEquals(new int[15000], d.getArray());
    }

    //
    // </region beauty tests>
    //
}
