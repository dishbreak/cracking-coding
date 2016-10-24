package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NaiveTrioStackTest {

	private NaiveTrioStack stackTrio;
	
	@Before
	public void setUp() throws Exception {
		stackTrio = new NaiveTrioStack();
	}

	@Test
	public void testEmptyPops() {
		for (int i = 0; i < 3; i++) {
			assertNull(stackTrio.pop(i));
		}
	}
	
	@Test
	public void testEmpyPopSparseArray() {
		for (int i = 0; i < 2; i ++) {
			stackTrio.push(i, 5, 6, 4, 7, 1);
		}
		
		assertNull(stackTrio.pop(2));
	}
	
	@Test
	public void testPopFromAllThree() {
		stackTrio.push(0, 1, 4, 33, 54, 52, 45);
		stackTrio.push(1, 45, 43, 23, 31, 63);
		stackTrio.push(2, 24, 51, 78, 91, 38);
		
		assertEquals(45, stackTrio.pop(0).intValue());
		assertEquals(52, stackTrio.peek(0).intValue());
		
		assertEquals(63, stackTrio.pop(1).intValue());
		assertEquals(31, stackTrio.peek(1).intValue());
		
		assertEquals(38, stackTrio.pop(2).intValue());
		assertEquals(91, stackTrio.peek(2).intValue());
	}

}
