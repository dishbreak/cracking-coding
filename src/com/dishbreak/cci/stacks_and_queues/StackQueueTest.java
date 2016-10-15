package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackQueueTest {

	private Queue<Integer> queue = new StackQueue<>();
	
	@Test
	public void testEnqueue() {
		queue.enqueue(4);
		queue.enqueue(6);
		queue.enqueue(8);
		
		assertFalse(queue.isEmpty());
	}
	
	@Test
	public void testDequeue() {
		queue.enqueue(4);
		queue.enqueue(6);
		queue.enqueue(8);
		
		assertEquals(4, queue.dequeue().intValue());
		assertEquals(6, queue.dequeue().intValue());
		assertEquals(8, queue.dequeue().intValue());
	}

}
