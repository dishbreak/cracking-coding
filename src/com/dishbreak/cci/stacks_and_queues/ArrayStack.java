package com.dishbreak.cci.stacks_and_queues;

public class ArrayStack implements Stack<Integer> {
	
	private Integer[] array;
	
	private int endIndex = -1;
	
	private static final int STACK_SIZE = 10;
	
	public ArrayStack(int stackSize) {
		array = new Integer[stackSize];
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return endIndex < 0;
	}

	@Override
	public Integer pop() {
		if (endIndex < 0) return null;
		Integer result = array[endIndex];
		endIndex--;
		return result;
	}

	@Override
	public Integer peek() {
		return (endIndex < 0) ? array[endIndex] : null;
	}

	@Override
	public void push(Integer data) {
		endIndex++;
		array[endIndex] = data;
	}

}
