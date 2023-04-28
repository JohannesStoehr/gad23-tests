package gad.logging;

import gad.binarysearch.Result;

import static org.junit.jupiter.api.Assertions.*;

public class TestResultImplementation implements Result {
    private int[] steps;
    private int index;

    public TestResultImplementation(int expectedSize) {
        steps = new int[expectedSize];
    }

    @Override
    public void addStep(int step) {
        if (index < steps.length) {
            steps[index] = step;
        }
        index++;
    }

    public void testLogging(int[] solution) {
        assertEquals(solution.length, index, "Number of logged steps wrong");

        assertArrayEquals(solution, steps, "Logged wrong path");
    }
}
