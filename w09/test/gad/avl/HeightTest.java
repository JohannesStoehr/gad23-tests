package gad.avl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HeightTest {

    @BeforeAll
    static public void generalTests() {
        AVLTreeNodeTest testAVLTreeNode = new AVLTreeNodeTest();
        testAVLTreeNode.testConstructor();
    }

    @Test
    public void testHeightSimple() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(1);
        AVLTreeNode left = new AVLTreeNode(2);
        AVLTreeNode right = new AVLTreeNode(3);

        root.setLeft(left);
        root.setRight(right);

        avlTree.setRoot(root);

        heightTester(2, avlTree);
    }

    @Test
    public void testHeightSingle() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(1);
        avlTree.setRoot(root);

        heightTester(1, avlTree);
    }

    @Test
    public void testHeightNull() {
        AVLTree avlTree = new AVLTree();
        heightTester(0, avlTree);
    }

    public void heightTester(int expected, AVLTree avlTree) {
        System.out.println("Test with tree:");
        System.out.println(avlTree);

        assertEquals(expected, avlTree.height());
    }
}
