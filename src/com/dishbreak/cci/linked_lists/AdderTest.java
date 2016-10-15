package com.dishbreak.cci.linked_lists;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdderTest {

	private LinkedList left;
	private LinkedList right;
	
	@Before
	public void setUp() {
		left = new LinkedList();
		left.append(3);
		left.append(1);
		left.append(5);
		
		right = new LinkedList();
		right.append(5);
		right.append(9);
		right.append(2);
	}
	
	@Test
	public void test() {
		LinkedList result = Adder.add(left, right);
		
		assertEquals("(8,0,8)", result.toString());
	}

}
