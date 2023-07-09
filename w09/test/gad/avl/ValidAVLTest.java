package gad.avl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ValidAVLTest {

    @BeforeAll
    public static void generalTests() {
        AVLTreeNodeTest testAVLTreeNode = new AVLTreeNodeTest();
        testAVLTreeNode.testConstructor();
    }

    @Test
    public void testValidAVLSimple1() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(1);
        AVLTreeNode left = new AVLTreeNode(2);
        AVLTreeNode right = new AVLTreeNode(3);

        root.setLeft(left);
        root.setRight(right);

        avlTree.setRoot(root);

        validAVLTester(false, avlTree);
    }

    @Test
    public void testValidAVLSimple2() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(3);
        AVLTreeNode left = new AVLTreeNode(2);
        AVLTreeNode right = new AVLTreeNode(1);

        root.setLeft(left);
        root.setRight(right);

        avlTree.setRoot(root);

        validAVLTester(false, avlTree);
    }

    @Test
    public void testValidAVLSame1() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(3);
        AVLTreeNode right = new AVLTreeNode(3);

        root.setRight(right);
        root.setBalance(1);

        avlTree.setRoot(root);

        validAVLTester(true, avlTree);
    }

    @Test
    public void testValidAVLSame2() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(3);
        AVLTreeNode left = new AVLTreeNode(3);

        root.setLeft(left);
        root.setBalance(-1);

        avlTree.setRoot(root);

        validAVLTester(true, avlTree);
    }

    @Test
    public void testValidAVLSingle() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(1);
        avlTree.setRoot(root);

        validAVLTester(true, avlTree);
    }

    @Test
    public void testValidAVLNull() { // changed this test in order to make it work without checking for a specific
                                     // return value (undefined)
        AVLTree avlTree = new AVLTree();
        printTree(avlTree);
        assertDoesNotThrow((Executable) avlTree::validAVL,
                "Unexpected Exception when calling validAVL on an empty Tree");
    }

    @Test
    void invalidAVLEdgeCase1() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(25);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(26);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        left.setLeft(new AVLTreeNode(19));
        left.setRight(new AVLTreeNode(23));

        right.setRight(new AVLTreeNode(27));
        right.setLeft(new AVLTreeNode(24));

        printTree(tree);

        assertFalse(tree.validAVL(), "Der Baum war nicht richtig sortiert, wurde aber als richtig erkannt.");
    }

    @Test
    void invalidAVLEdgeCase2() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(25);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(26);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        left.setLeft(new AVLTreeNode(19));
        left.setRight(new AVLTreeNode(200));

        right.setRight(new AVLTreeNode(27));
        right.setLeft(new AVLTreeNode(26));

        printTree(tree);

        assertFalse(tree.validAVL(), "Der Baum war nicht richtig sortiert, wurde aber als richtig erkannt.");
    }

    @Test
    void testValidAVLCorrect01() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(30);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(40);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        left.setLeft(new AVLTreeNode(10));
        left.setRight(new AVLTreeNode(25));

        right.setRight(new AVLTreeNode(50));
        right.setLeft(new AVLTreeNode(35));

        printTree(tree);

        assertEquals(true, tree.validAVL(), "Der Baum war korrekt, wurde aber nicht als richtig erkannt.");
    }

    @Test
    void testValidAVLCorrect04() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(40);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(50);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        left.setLeft(new AVLTreeNode(10));
        left.setRight(new AVLTreeNode(30));

        right.setLeft(new AVLTreeNode(45));
        right.setRight(new AVLTreeNode(60));

        printTree(tree);

        assertEquals(true, tree.validAVL(), "Der Baum war korrekt, wurde aber nicht als richtig erkannt.");
    }

    @Test
    void testValidAVLLoop01() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(30);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(40);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        left.setLeft(root); // This creates a cycle

        try {
            assertFalse(tree.validAVL());
        } catch (Throwable e) {
            fail("Bei validAVL mit einem Kreis entsteht eine Exception oder Ähnliches: " + e);
        }
    }

    @Test
    void testValidAVLLoop02() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(30);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(40);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        right.setRight(root); // This creates a cycle

        try {
            assertFalse(tree.validAVL());
        } catch (Throwable e) {
            fail("Bei validAVL mit einem Kreis entsteht eine Exception oder Ähnliches: " + e);
        }
    }

    @Test
    void testValidAVLBalanceEqualsHeight01() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(30);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(40);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        // Incorrect balance values
        root.setBalance(2);
        left.setBalance(-1);
        right.setBalance(1);

        printTree(tree);

        assertEquals(false, tree.validAVL(),
                "Der Baum hatte falsche balance-Werte gespeichert, wurde aber als richtig erkannt.");
    }

    @Test
    void testValidAVLBalanceEqualsHeight02() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(30);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(40);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        left.setLeft(new AVLTreeNode(10));
        left.setRight(new AVLTreeNode(25));

        right.setRight(new AVLTreeNode(50));
        right.setLeft(new AVLTreeNode(35));

        root.setBalance(2); // Incorrect balance factor
        left.setBalance(0); // Correct balance factor
        right.setBalance(-1); // Incorrect balance factor

        printTree(tree);

        assertEquals(false, tree.validAVL(),
                "Der Baum hatte falsche balance-Werte gespeichert, wurde aber als richtig erkannt.");
    }

    @Test
    void testValidAVLBalanceEqualsHeight03() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(40);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(50);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        // Incorrect balance values
        root.setBalance(0);
        left.setBalance(2);
        right.setBalance(-1);

        printTree(tree);

        assertEquals(false, tree.validAVL(),
                "Der Baum hatte falsche balance-Werte gespeichert, wurde aber als richtig erkannt.");
    }

    @Test
    void testValidAVLBalanceEqualsHeight04() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(20);
        AVLTreeNode left = new AVLTreeNode(10);
        AVLTreeNode right = new AVLTreeNode(30);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        left.setLeft(new AVLTreeNode(5));
        left.setRight(new AVLTreeNode(15));

        right.setRight(new AVLTreeNode(40));
        right.setLeft(new AVLTreeNode(25));

        root.setBalance(-1); // Incorrect balance factor
        left.setBalance(1); // Incorrect balance factor
        right.setBalance(0); // Correct balance factor

        printTree(tree);

        assertEquals(false, tree.validAVL(),
                "Der Baum hatte falsche balance-Werte gespeichert, wurde aber als richtig erkannt.");
    }

    @Test
    void testValidAVLBalanceEqualsHeightOnlyRoot() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(20);
        tree.setRoot(root);

        root.setBalance(-1); // Incorrect balance factor, should be 0

        printTree(tree);

        assertEquals(false, tree.validAVL(),
                "Der Baum hatte falsche balance-Werte gespeichert, wurde aber als richtig erkannt.");
    }

    public void validAVLTester(boolean expected, AVLTree avlTree) {
        printTree(avlTree);
        assertEquals(expected, avlTree.validAVL());
    }

    public void printTree(AVLTree avlTree) {
        System.out.println("Test with tree:");
        System.out.println(avlTree);
    }
}
