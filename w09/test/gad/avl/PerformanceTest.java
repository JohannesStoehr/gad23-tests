package gad.avl;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class PerformanceTest {
    /*
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------------
     * -----------------------------------------
     * In case you're wondering if it's even possible to pass all these tests:
     * Yes, it is.
     * The trick is to make sure each of your method runs in either constant,
     * logarithmic or linear time.
     * If any of your methods run in O(n*log(n)) or O(n^2) time, these tests might
     * fail.
     * Even the insert method and whatever method you're using to rebalance the tree
     * should run in constant or linear time.
     * One of my outputs was:
     * /////////////////////////////////////////////////////////////////////////////
     * ////////////////
     * Time needed to insert 1000 elements was 20 miliseconds
     * Time needed to insert 2000 elements was 42 miliseconds
     * Time needed to insert 3000 elements was 93 miliseconds
     * Time needed to insert 4000 elements was 188 miliseconds
     * Time needed to insert 5000 elements was 316 miliseconds
     * Time needed to insert 6000 elements was 431 miliseconds
     * Time needed to insert 7000 elements was 596 miliseconds
     * Time needed to insert 8000 elements was 798 miliseconds
     * Time needed to insert 9000 elements was 1050 miliseconds
     * Time needed to insert 10000 elements was 1334 miliseconds
     * Time needed to insert 11000 elements was 1748 miliseconds
     * Time needed to insert 12000 elements was 2085 miliseconds
     * Successfully insertet 12000 elements in under 3 seconds!
     * Test 1 succesfull!
     * 
     * Test 2 Attempt 1:
     * Time needed to execute all methods was 761 miliseconds
     * Successfully executed each method 2500 times in under 1 second!
     * Test 2 Attempt 2:
     * Time needed to execute all methods was 531 miliseconds
     * Successfully executed each method 2500 times in under 1 second!
     * Test 2 succesfull!
     * /////////////////////////////////////////////////////////////////////////////
     * ////////////////
     * -----------------------------------------------------------------------------
     * -----------------------------------------------------------------------------
     * -----------------------------------------
     */

    @Test
    void performanceTest1() {
        int caseTrials = 12;
        for (int i = 1; i <= caseTrials; i++) {
            performanceLoop1(1000 * i);
        }
        System.out.println("Successfully insertet " + 1000 * caseTrials + " elements in under 3 seconds!");
        System.out.println("Test 1 succesfull!\n");
    }

    void performanceLoop1(int elements) {
        elements = Math.abs(elements);
        Random random = new Random(42);
        AVLTree tree = new AVLTree();
        long startTime = System.nanoTime();
        for (int i = 0; i < elements; i++) {
            System.out.println("Inserting " + i);
            tree.insert(random.nextInt(0, Integer.MAX_VALUE));
        }
        long timeElapsed = System.nanoTime() - startTime;
        int miliseconds = (int) (timeElapsed / 1000000L);
        System.out.println("Time needed to insert " + elements + " elements was " + miliseconds + " miliseconds");
        if (miliseconds > 3000) {
            throw new RuntimeException("Execution time exceeded 3 seconds for " + elements + " elements");
        }
    }

    @Test
    void performanceTest2() {
        System.out.println("Test 2 Attempt 1:");
        performanceLoop2(true);
        System.out.println("Test 2 Attempt 2:");
        performanceLoop2(false);
        System.out.println("Test 2 succesfull!\n");
    }

    void performanceLoop2(boolean insertRandomly) {
        int elements = 2500;
        Random random = new Random(42);
        AVLTree tree = new AVLTree();
        long startTime = System.nanoTime();
        for (int i = 1; i <= elements; i++) {
            if (i == 152) {
                System.out.println("Error at 152");
            }

            int newKey = insertRandomly ? random.nextInt(0, elements) : i;
            System.out.println("Inserting " + newKey);
            tree.insert(newKey);
            if (!tree.validAVL()) {
                throw new RuntimeException("The validAVL() Method says the tree is invalid.");
            }
            int height = tree.height();
            for (int ii = 0; ii <= elements; ii++) {
                // This just tests the time it takes to execute.
                // Whether or not your output of find is correct is irrelevant here.
                boolean found = tree.find(random.nextInt(0, elements));
            }
        }
        long timeElapsed = System.nanoTime() - startTime;
        int miliseconds = (int) (timeElapsed / 1000000L);
        System.out.println("Time needed to execute all methods was " + miliseconds + " miliseconds");
        if (miliseconds > 1000) {
            throw new RuntimeException("Execution time exceeded 1 second!\n");
        }
        System.out.println("Successfully executed each method 2500 times in under 1 second!");
    }
}
