package com.dishbreak.cci.stacks_and_queues;

public class MaxHeap extends AbstractHeap {

	@Override
	protected boolean shouldSwapParent(Integer nodeIndex, Integer parentIndex) {
		return  array[nodeIndex] > array[parentIndex];
	}

	@Override
	protected boolean shouldSwapChild(Integer nodeIndex, Integer childIndex) {
		return array[childIndex] > array[nodeIndex];
	}

	@Override
	protected Integer selectChildIdx(Integer leftChildIdx, Integer rightChildIdx) {
		return (array[leftChildIdx] > array[rightChildIdx]) ? leftChildIdx : rightChildIdx;
	}
	
	

}
