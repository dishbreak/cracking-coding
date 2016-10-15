package com.dishbreak.cci.stacks_and_queues;

public class MinHeap extends AbstractHeap {

	@Override
	protected boolean shouldSwapParent(Integer nodeIndex, Integer parentIndex) {
		return array[nodeIndex] < array[parentIndex];
	}

	@Override
	protected boolean shouldSwapChild(Integer nodeIndex, Integer childIndex) {
		return array[nodeIndex] > array[childIndex];
	}

	@Override
	protected Integer selectChildIdx(Integer leftChildIdx, Integer rightChildIdx) {
		return (array[leftChildIdx] < array[rightChildIdx] ) ?  leftChildIdx : rightChildIdx;
	}
	

}
