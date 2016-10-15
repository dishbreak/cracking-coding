package com.dishbreak.cci.stacks_and_queues;

public interface Queue<T> {
	public boolean isEmpty();
	
	public void enqueue(T data);
	
	public T dequeue();
}
