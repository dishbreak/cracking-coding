package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListStackTest {
	
	Stack<Integer> stack;
	
	@Before
	public void setUp() {
		stack = new ListStack<>();
	}

	@Test
	public void testPushOneItem() {
		stack.push(3);
		
		assertEquals("(3)", stack.toString());
	}
	
	@Test
	public void testPushMultipleItems() {
		Integer[] values = { 2, 4, 6, 10, 3, 4 };
		for (Integer value : values ) {
			stack.push(value); 
		}
		
		assertEquals("(4,3,10,6,4,2)", stack.toString());
	}
	
	@Test
	public void testPeekEmpty() {
		assertNull(stack.peek());
	}
	
	@Test
	public void testPopEmpty() {
		assertNull(stack.pop());
	}
	
	@Test
	public void testIsEmptyEmpty() {
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void testIsEmptyNonEmpty() {
		stack.push(5);
		assertFalse(stack.isEmpty());
	}
	
	@Test
	public void testPopNonEmpty() {
		Integer[] values = { 2, 4, 6, 10, 3, 4 };
		for (Integer value : values ) {
			stack.push(value); 
		}
		
		assertEquals(4, stack.pop().intValue());
		assertEquals("(3,10,6,4,2)", stack.toString());
	}
	
	@Test
	public void testPeekNonEmpty() {
		Integer[] values = { 2, 4, 6, 10, 3, 4 };
		for (Integer value : values ) {
			stack.push(value); 
		}
		
		assertEquals(4, stack.peek().intValue());
		assertEquals("(4,3,10,6,4,2)", stack.toString());
	}
	

}
