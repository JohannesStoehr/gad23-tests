package gad.avl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AVLTreeNodeTest {

    // Alle Tests hier müssen bestanden werden, um die Funktionalität der anderen
    // Tests zu gewährleisten.

    private final String failMessage = """
            \n
            *******************************************************************

                                    ACHTUNG!

            Damit alle Tests richtig funktionieren können,
            stelle sicher, dass alle Tests in testAVLTreeNode bestanden werden.
            \n*******************************************************************

            """;

    @Test
    public void testConstructor() {
        AVLTree avlTree = new AVLTree();
        AVLTreeNode root = new AVLTreeNode(3);
        AVLTreeNode left = new AVLTreeNode(2);
        AVLTreeNode right = new AVLTreeNode(1);

        root.setLeft(left);
        root.setRight(right);

        avlTree.setRoot(root);

        assertEquals(3, avlTree.getRoot().getKey(), failMessage);
        assertEquals(2, avlTree.getRoot().getLeft().getKey(), failMessage);
        assertEquals(1, avlTree.getRoot().getRight().getKey(), failMessage);
    }
}
