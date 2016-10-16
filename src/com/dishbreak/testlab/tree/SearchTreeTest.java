package com.dishbreak.testlab.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchTreeTest {

	private SearchTree tree;
	
	@Before
	public void setUp() throws Exception {
		tree = new SearchTree();
		Integer[] keys = { 4, 3, 5, 6, 7, 2 };
		Integer[] values = { 22, 35, 13, 17, 41, 38 };
		
		for (int i = 0; i < keys.length; i++) {
			tree.insert(keys[i], values[i]);
		}
	}

	@Test
	public void testGet() {
		assertNull(tree.get(12));
		assertEquals(17, tree.get(6).intValue());
	}
	
	@Test
	public void testUpdate() {
		tree.insert(5, 21);
		assertEquals(21, tree.get(5).intValue());
	}
	
	@Test
	public void testDelete() {
		tree.insert(1, 34);
		tree.delete(3);
		assertNull(tree.get(3));
		
		Integer[] keys = { 4, 5, 6, 7, 2, 1 };
		Integer[] values = { 22, 13, 17, 41, 38, 34 };
		for (int i = 0; i < keys.length; i++) {
			assertEquals(values[i], tree.get(keys[i]));
		}
	}

}
