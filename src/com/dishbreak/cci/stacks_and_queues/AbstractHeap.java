package com.dishbreak.cci.stacks_and_queues;

public abstract class AbstractHeap {

    protected abstract boolean shouldSwapParent(Integer nodeIndex, Integer parentIndex);

    protected abstract boolean shouldSwapChild(Integer nodeIndex, Integer childIndex);

    protected abstract Integer selectChildIdx(Integer leftChildIdx, Integer rightChildIdx);

    protected static final Integer SIZE = 100;
    protected Integer[] array;
    private Integer maxSize;
    private Integer currentIndex;

    public AbstractHeap(Integer size) {
        array = new Integer[size];
        this.maxSize = size;
        this.currentIndex = 0;
    }

    public AbstractHeap() {
        this(SIZE);
    }

    public void insert(Integer data) {
        if (currentIndex < maxSize) {
            array[currentIndex] = data;
            bubbleUp(currentIndex);
            currentIndex++;
        }
    }

    private void bubbleUp(Integer index) {
        if (index == 0)
            return;

        Integer data = array[index];

        Integer parentIndex = Math.floorDiv(index - 1, 2);

        if (shouldSwapParent(index, parentIndex)) {
            array[index] = array[parentIndex];
            array[parentIndex] = data;
            bubbleUp(parentIndex);
        }

    }

    public Integer getRoot() {
        return array[0];
    }

    public Integer popRoot() {
        Integer max = array[0];

        currentIndex--;
        array[0] = array[currentIndex];
        array[currentIndex] = null;

        bubbleDown(0);

        return max;
    }

    private void bubbleDown(int index) {
        if (index > currentIndex)
            return;

        Integer data = array[index];

        Integer leftIdx = 2 * index + 2;
        Integer rightIdx = 2 * index + 1;

        Integer leftVal = (leftIdx > maxSize) ? null : array[leftIdx];
        Integer rightVal = (rightIdx > maxSize) ? null : array[rightIdx];

        Integer greaterIdx = null;
        if (rightVal == null && leftVal == null) {
            return;
        } else if (leftVal != null && rightVal != null) {
            greaterIdx = selectChildIdx(leftIdx, rightIdx);
        } else if (leftVal != null) {
            greaterIdx = leftIdx;
        } else { // only possible scenario is that rightVal is non-null
            greaterIdx = rightIdx;
        }

        if (array[greaterIdx] != null && shouldSwapChild(index, greaterIdx)) {
            array[index] = array[greaterIdx];
            array[greaterIdx] = data;
            bubbleDown(greaterIdx);
        }

    }
}
