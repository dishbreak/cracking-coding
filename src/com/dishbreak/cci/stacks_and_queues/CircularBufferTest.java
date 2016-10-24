package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CircularBufferTest {

	private CircularBuffer buffer = null;
	
	@Before
	public void setUp() throws Exception {
		buffer = new CircularBuffer();
	}

	@Test
	public void testEnqueue() {
		buffer.enqueue(4);
		buffer.enqueue(6);
		buffer.enqueue(8);
		
		assertFalse(buffer.isEmpty());
	}
	
	@Test
	public void testDequeue() {
		buffer.enqueue(4);
		buffer.enqueue(6);
		buffer.enqueue(8);
		
		assertEquals(4, buffer.dequeue().intValue());
		assertEquals(6, buffer.dequeue().intValue());
		assertEquals(8, buffer.dequeue().intValue());
	}
	
	@Test
	public void testEmptyDequeue() {
		assertTrue(buffer.isEmpty());
		assertNull(buffer.dequeue());
		assertTrue(buffer.isEmpty());
	}
	
	@Test
	public void testFullQueue() {
		buffer.enqueue(4);
		buffer.enqueue(6);
		buffer.enqueue(8);
		
		assertEquals(4, buffer.dequeue().intValue());
		assertEquals(6, buffer.dequeue().intValue());
		assertEquals(8, buffer.dequeue().intValue());
		
		assertTrue(buffer.isEmpty());
		
		for (Integer i = 0; i < 12; i++) {
			buffer.enqueue(i); // last two should be a no-op
		}
		
		for (Integer i = 0; i < 10; i++) {
			assertEquals(i, buffer.dequeue());
		}
		
		assertTrue(buffer.isEmpty());
	}


}
