package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxHeapTest {

	private MaxHeap heap = new MaxHeap();
	
	@Test
	public void testInsert() {
		heap.insert(3);
		
		assertEquals(3, heap.getRoot().intValue());
	}
	
	@Test
	public void testGetMax() {
		Integer[] values = { 
				6, 8, 11, 8, 7, 4, 20, 
				12, 11, 8, 5, 4, 1, 5, 
				6, 7, 16, 7, 16, 2, 2, 14, 
				16, 14, 13, 12, 17, 17, 
				11, 12 
		};
		
		for (Integer value : values) {
			heap.insert(value);
		}
		
		assertEquals(20, heap.getRoot().intValue());
	}
	
	@Test
	public void testPopMax() {
		Integer[] values = { 
				6, 8, 11, 8, 7, 4, 20, 
				12, 11, 8, 5, 4, 1, 5, 
				6, 7, 16, 7, 16, 2, 2, 14, 
				16, 14, 13, 12, 17, 17, 
				11, 12 
		};
		
		for (Integer value : values) {
			heap.insert(value);
		}
		
		assertEquals(20, heap.popRoot().intValue());
		assertEquals(17, heap.popRoot().intValue());
	}

}
