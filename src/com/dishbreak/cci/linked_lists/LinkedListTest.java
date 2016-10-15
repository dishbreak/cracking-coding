package com.dishbreak.cci.linked_lists;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	private LinkedList list;
	
	@Before
	public void setUp() {
		list = new LinkedList();
		list.append(4);
		list.append(5);
		list.append(7);
		list.append(6);
		list.append(5);
		list.append(8);
		list.append(3);
	}
	
	@Test
	public void testAddNode() {
		assertEquals("(4,5,7,6,5,8,3)", list.toString());
	}
	
	@Test
	public void testDeleteNode() {
		list.deleteNode(5);
		assertEquals("(4,7,6,5,8,3)", list.toString());
	}
	
	@Test
	public void testDedupe() {
		
		
		list.dedupe();
		assertEquals("(4,5,7,6,8,3)", list.toString());
	}
	
	@Test
	public void testDedupeN2() {

		list.dedupeN2();
		assertEquals("(4,5,7,6,8,3)", list.toString());
	}
	
	@Test
	public void testLastElement() {
		Node<Integer> nthPlace = list.lastElement(2);
		assertEquals(5, nthPlace.getData().intValue());
	}
	
	@Test
	public void testDeleteNodeByRef() {
		Node<Integer> iter = list.getRoot();
		
		for (int i = 0; i < 4; i++) {
			iter = iter.next();
		}
		
		list.deleteNode(iter);
		
		assertEquals("(4,5,7,6,8,3)", list.toString());
	}
}
