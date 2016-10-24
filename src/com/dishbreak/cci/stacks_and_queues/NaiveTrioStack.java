package com.dishbreak.cci.stacks_and_queues;

public class NaiveTrioStack {
	
	private static final int STACK_SIZE = 10;
	
	private int[] end = { -1, -1, -1 };
	
	private int indivStack;
	
	Integer[] baseArray;
	
	public NaiveTrioStack(int stackSize) {
		this.indivStack = stackSize;
		this.baseArray = new Integer[stackSize * 3];
	}
	
	public NaiveTrioStack() {
		this(STACK_SIZE);
	}
	
	public void push(int stackId, Integer data) {
		if (stackId > 2) return; 
		
	
		if (end[stackId] < indivStack) {
			end[stackId] = end[stackId] + 1;
			int baseIndex = end[stackId] * 3 + stackId;
			baseArray[baseIndex] = data;
		}

	}
	
	public void push(int stackId, Integer...integers) {
		for (Integer value : integers) {
			push(stackId, value);
		}
	}
	
	public Integer pop(int stackId) {
		if (stackId > 2 || end[stackId] < 0) return null;
		
		Integer result;
		int baseIndex = end[stackId] * 3 + stackId;
		result = baseArray[baseIndex];
		baseArray[baseIndex] = null;
		end[stackId] = end[stackId] - 1;

		
		return result;
	}
	
	public Integer peek(int stackId) {
		if (stackId > 2) return null;
		
		Integer result;
		int baseIndex = end[stackId] * 3 + stackId;
		result = baseArray[baseIndex];
		
		return result;
	}
	
}
