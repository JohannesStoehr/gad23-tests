package gad.binomilia;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TreeCounterResult implements Result {
    private int size;

    @Override
    public void startInsert(int value, Collection<BinomialTreeNode> heap) {
    }

    @Override
    public void startInsert(int value, BinomialTreeNode[] heap) {
    }

    @Override
    public void startDeleteMin(Collection<BinomialTreeNode> heap) {
    }

    @Override
    public void startDeleteMin(BinomialTreeNode[] heap) {
    }

    @Override
    public void logIntermediateStep(Collection<BinomialTreeNode> heap) {
        // heap.size() should return the amount of trees in the heap. So it should
        // return the size of the Collection where u save the trees. If you do it
        // another way, this test may fail
        size = heap.size();
    }

    @Override
    public void logIntermediateStep(BinomialTreeNode[] heap) {
        logIntermediateStep(Arrays.stream(heap).toList());

    }

    @Override
    public void logIntermediateStep(BinomialTreeNode tree) {
        logIntermediateStep(List.of(tree));

    }

    @Override
    public void addToIntermediateStep(Collection<BinomialTreeNode> heap) {
        size += heap.size();
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode[] heap) {
        addToIntermediateStep(Arrays.stream(heap).toList());
    }

    @Override
    public void addToIntermediateStep(BinomialTreeNode tree) {
        addToIntermediateStep(List.of(tree));
    }

    @Override
    public void printCurrentIntermediateStep() {

    }

    public int getSize() {
        return size;
    }
}