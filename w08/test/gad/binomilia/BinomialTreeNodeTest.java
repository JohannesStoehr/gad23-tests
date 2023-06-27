package test.gad.binomilia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinomialTreeNodeTest {

    private static Stream<Arguments> simpleTest()
    {
        return Stream.of(
                Arguments.of(305818196),
                Arguments.of(2002281049),
                Arguments.of(-858398579),
                Arguments.of(1834927768),
                Arguments.of(1544841234),
                Arguments.of(1442756015),
                Arguments.of(1235069899),
                Arguments.of(-960568800),
                Arguments.of(-253372286),
                Arguments.of(1042475511)

        );
    }

    @ParameterizedTest
    @MethodSource("simpleTest")
    void minSimpleTest(int i)
    {
        BinomialTreeNode treeNode = new BinomialTreeNode(i);
        assertEquals(i,treeNode.min());
    }

    @ParameterizedTest
    @MethodSource("simpleTest")
    void rankSimpleTest(int i)
    {
        BinomialTreeNode treeNode = new BinomialTreeNode(i);
        assertEquals(0,treeNode.rank());
    }

    @Test
    void mergeTest()
    {
        BinomialTreeNode treeNode1 = new BinomialTreeNode(50);
        BinomialTreeNode treeNode2 = new BinomialTreeNode(69);

        assertEquals(treeNode1, BinomialTreeNode.merge(treeNode1, treeNode2), "You returned the wrong node!");
        assertEquals(1, treeNode1.rank());
        assertEquals(50, treeNode1.min());

        BinomialTreeNode treeNode3 = new BinomialTreeNode(12);
        BinomialTreeNode treeNode4 = new BinomialTreeNode(420);
        assertEquals(treeNode3, BinomialTreeNode.merge(treeNode4, treeNode3), "You returned the wrong node!");
        assertEquals(1, treeNode3.rank());
        assertEquals(12, treeNode3.min());
        assertEquals(treeNode3, BinomialTreeNode.merge(treeNode1, treeNode3), "You returned the wrong node!");
        assertEquals(2, treeNode3.rank());
        assertEquals(12, treeNode3.min());

        BinomialTreeNode bigTreeNode = BinomialTreeNode.merge(
                BinomialTreeNode.merge(new BinomialTreeNode(911), new BinomialTreeNode(133)),
                BinomialTreeNode.merge(new BinomialTreeNode(18), new BinomialTreeNode(3))
        );
        assertEquals(3, bigTreeNode.min());
        assertEquals(2, bigTreeNode.rank());

        assertEquals(bigTreeNode, BinomialTreeNode.merge(bigTreeNode, treeNode3));
        assertEquals(3, bigTreeNode.min());
        assertEquals(3, bigTreeNode.rank());
    }
}
