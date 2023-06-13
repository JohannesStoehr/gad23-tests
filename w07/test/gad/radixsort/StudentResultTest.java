package gad.radixsort;

import gad.radix.Result;

public class StudentResultTest implements Result {
    /**
     *<div>
     * This class and method exists only that during the tests on error is produced.
     * It has the same structure as the StudentResult which is given in the code of Artemis,
     * but without the "System.out.println(Arrays.toString(array));"
     * which is for the sorting process irrelevant.
     *</div>
     */
    @Override
    public void logArray(int[] array) {}
}
