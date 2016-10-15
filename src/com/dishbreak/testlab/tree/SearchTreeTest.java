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

}
