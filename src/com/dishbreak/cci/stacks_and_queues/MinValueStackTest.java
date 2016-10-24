package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinValueStackTest {

	private MinValueStack stack;
	
	@Before
	public void setUp() throws Exception {
		stack = new MinValueStack(); 
	}

	@Test
	public void testPush() {
		stack.push(5, 3, 7);
		
		assertEquals(3, stack.min().intValue());
	}
	
	@Test
	public void testPushNewMin() {
		stack.push(5);
		stack.push(3);
		stack.push(7);
		stack.push(2);
		
		assertEquals(2, stack.min().intValue());
	}
	
	@Test
	public void testRevertToOldMin() {
		stack.push(5, 3, 7, 2);
		assertEquals(2, stack.pop().intValue());
		assertEquals(3, stack.min().intValue());
	}

}
