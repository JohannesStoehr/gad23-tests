package gad.avl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FindTest {
    @Test
    void testFindEmptyTree() {
        AVLTree tree = new AVLTree();

        assertFalse(tree.find(10), "Found a node in an empty tree");
    }

    @Test
    void testFindSingleNodeTree() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(10);
        tree.setRoot(root);

        assertTrue(tree.find(10), "Didn't find the root node");
        assertFalse(tree.find(20), "Found a node that doesn't exist");
    }

    @Test
    void testFindMultipleNodesTree() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(30);
        AVLTreeNode left = new AVLTreeNode(20);
        AVLTreeNode right = new AVLTreeNode(40);
        root.setLeft(left);
        root.setRight(right);
        tree.setRoot(root);

        assertTrue(tree.find(30), "Didn't find the root node");
        assertTrue(tree.find(20), "Didn't find the left child node");
        assertTrue(tree.find(40), "Didn't find the right child node");
        assertFalse(tree.find(10), "Found a node that doesn't exist");
        assertFalse(tree.find(50), "Found a node that doesn't exist");
    }

    @Test
    void testFindNodeInLargeTree() {
        AVLTree tree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(50);
        AVLTreeNode node20 = new AVLTreeNode(20);
        AVLTreeNode node80 = new AVLTreeNode(80);
        AVLTreeNode node10 = new AVLTreeNode(10);
        AVLTreeNode node30 = new AVLTreeNode(30);
        AVLTreeNode node60 = new AVLTreeNode(60);
        AVLTreeNode node90 = new AVLTreeNode(90);
        root.setLeft(node20);
        root.setRight(node80);
        node20.setLeft(node10);
        node20.setRight(node30);
        node80.setLeft(node60);
        node80.setRight(node90);
        tree.setRoot(root);

        assertTrue(tree.find(50), "Didn't find the root node");
        assertTrue(tree.find(20), "Didn't find a node");
        assertTrue(tree.find(80), "Didn't find a node");
        assertTrue(tree.find(10), "Didn't find a node");
        assertTrue(tree.find(30), "Didn't find a node");
        assertTrue(tree.find(60), "Didn't find a node");
        assertTrue(tree.find(90), "Didn't find a node");
        assertFalse(tree.find(0), "Found a node that doesn't exist");
        assertFalse(tree.find(100), "Found a node that doesn't exist");
    }
}
