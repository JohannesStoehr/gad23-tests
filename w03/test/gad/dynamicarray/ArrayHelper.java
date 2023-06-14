package gad.dynamicarray;

import gad.dynamicarray.DynamicArray;
import gad.dynamicarray.DynamicStack;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayHelper {

    static void checkUnderlyingArray(int[] expected, Object o, String[] errorMsg) throws NoSuchFieldException,
            IllegalAccessException {

        //get dynamicArray variable from object
        var dynamicalArrayField = o.getClass().getDeclaredField("array");
        dynamicalArrayField.setAccessible(true);

        var dynamicArray = (DynamicArray) dynamicalArrayField.get(o);

        //get int[] array from object
        var elementsField = DynamicArray.class.getDeclaredField("elements");
        elementsField.setAccessible(true);

        var actual = (int[]) elementsField.get(dynamicArray);

        assertArrayEquals(
                expected,
                actual,
                "\nShould be:\n" +
                        Arrays.toString(expected) + " but is:\n" +
                        Arrays.toString(actual) + "\n" +
                        Arrays.toString(errorMsg) + "\n\n");
    }

    static void checkUnderlyingArrayFirst(int[] expected, Object o, String[] errorMsg) throws NoSuchFieldException,
            IllegalAccessException {

        //get stackyQueue variable from object
        var stackyQueueField = o.getClass().getDeclaredField("first");
        stackyQueueField.setAccessible(true);

        var p = (DynamicStack) stackyQueueField.get(o);

        //get dynamicArray variable from object
        var dynamicalArrayField = p.getClass().getDeclaredField("array");
        dynamicalArrayField.setAccessible(true);

        var dynamicArray = (DynamicArray) dynamicalArrayField.get(p);

        //get int[] array from object
        var elementsField = DynamicArray.class.getDeclaredField("elements");
        elementsField.setAccessible(true);

        var actual = (int[]) elementsField.get(dynamicArray);

        assertArrayEquals(
                expected,
                actual,
                "\nFirst should be:\n" +
                        Arrays.toString(expected) + " but is:\n" +
                        Arrays.toString(actual) + "\n" +
                        Arrays.toString(errorMsg) + "\n\n");
    }

    static void checkUnderlyingArraySecond(int[] expected, Object o, String[] errorMsg) throws NoSuchFieldException,
            IllegalAccessException {

        //get stackyQueue variable from object
        var stackyQueueField = o.getClass().getDeclaredField("second");
        stackyQueueField.setAccessible(true);

        var p = (DynamicStack) stackyQueueField.get(o);

        //get dynamicArray variable from object
        var dynamicalArrayField = p.getClass().getDeclaredField("array");
        dynamicalArrayField.setAccessible(true);

        var dynamicArray = (DynamicArray) dynamicalArrayField.get(p);

        //get int[] array from object
        var elementsField = DynamicArray.class.getDeclaredField("elements");
        elementsField.setAccessible(true);

        var actual = (int[]) elementsField.get(dynamicArray);

        assertArrayEquals(
                expected,
                actual,
                "\nSecond should be:\n" +
                        Arrays.toString(expected) + " but is:\n" +
                        Arrays.toString(actual) + "\n" +
                        Arrays.toString(errorMsg) + "\n\n");
    }
}