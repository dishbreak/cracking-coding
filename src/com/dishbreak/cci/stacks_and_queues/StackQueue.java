package com.dishbreak.cci.stacks_and_queues;

public class StackQueue<T> implements Queue<T> {

	private Stack<T> inputStack = new ListStack<T>();
	private Stack<T> outputStack = new ListStack<T>();
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return inputStack.isEmpty() && outputStack.isEmpty();
	}

	@Override
	public void enqueue(T data) {
		inputStack.push(data);

	}

	@Override
	public T dequeue() {
		if (outputStack.isEmpty()) {
			while (!inputStack.isEmpty()) {
				outputStack.push(inputStack.pop());
			}
		}
		return outputStack.pop();
	}

}
