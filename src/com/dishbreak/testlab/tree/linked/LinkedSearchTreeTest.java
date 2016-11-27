package com.dishbreak.testlab.tree.linked;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dishbreak.testlab.tree.linked.LinkedSearchTree.TreeTest;

public class LinkedSearchTreeTest {
	
	private LinkedSearchTree tree;

	@Before
	public void setUp() throws Exception {
		int[] keys = {5, 6, 12, 4, 7, 9, 8, 2};
		int[] values = {34, 23, 44, 54, 67, 24, 71, 35};
		
		tree = LinkedSearchTree.buildFromArray(keys, values);
	}

	@Test
	public void testBuildFromArray() {
		
		assertEquals("[( 2 => 35 ), ( 4 => 54 ), ( 5 => 34 ), ( 6 => 23 ), ( 7 => 67 ), ( 8 => 71 ), ( 9 => 24 ), ( 12 => 44 )]", tree.toString());
	}
	
	@Test
	public void testGetValue() {
		assertEquals(23, tree.getValue(6).intValue());
	}
	
	@Test
	public void testSearchPositive() {
		LinkedTreeNode node = tree.search(new TreeTest() {

			@Override
			public boolean isMatch(LinkedTreeNode node) {
				return node.key() == 8;
			}
			
		});
		
		assertEquals(71, node.value().intValue());
	}
	
	@Test
	public void testSearchEmpty() {
		tree = new LinkedSearchTree();
		
		LinkedTreeNode node = tree.search(new TreeTest() {

			@Override
			public boolean isMatch(LinkedTreeNode node) {
				return node.key() == 8;
			}
			
		});
		
		assertNull(node);
	}
	
	@Test
	public void testSearchNegative() {
		LinkedTreeNode node = tree.search(new TreeTest() {

			@Override
			public boolean isMatch(LinkedTreeNode node) {
				return node.key() == 90;
			}
			
		});
		
		assertNull(node);
	}
	
	@Test
	public void testIsBalancedNegative() {
		assertFalse(tree.isBalanced());
	}
	
	@Test
	public void testIsBalancedPositive() {
		int[] keys = {6, 8, 9, 12, 7, 4, 5, 3, 2};
		int[] values = {34, 23, 44, 54, 67, 24, 71, 35, 77};
		
		tree = LinkedSearchTree.buildFromArray(keys, values);
		
		assertTrue(tree.isBalanced());
	}
	
	@Test
	public void testDeleteNonLeaf() {
		tree.delete(7);
		assertNull(tree.getValue(7));
		assertEquals(71, tree.getValue(8).intValue());
		assertEquals(24, tree.getValue(9).intValue());
		assertEquals("[( 2 => 35 ), ( 4 => 54 ), ( 5 => 34 ), ( 6 => 23 ), ( 8 => 71 ), ( 9 => 24 ), ( 12 => 44 )]", tree.toString());

	}
	
	@Test
	public void testDeleteFullParent() {
		tree.insert(13, 46); // will become right child for 12.
		tree.delete(12);
		
		assertEquals((Integer) 46, tree.getValue(13));
		assertEquals((Integer) 24, tree.getValue(9));
	}
	
	@Test
	public void testDeleteLeaf() {
		tree.delete(2);
		assertNull(tree.getValue(2));
		assertEquals("[( 4 => 54 ), ( 5 => 34 ), ( 6 => 23 ), ( 7 => 67 ), ( 8 => 71 ), ( 9 => 24 ), ( 12 => 44 )]", tree.toString());

	}
	
	@Test
	public void testDeleteNull() {
		tree.delete((Integer) null);
		
		assertEquals("[( 2 => 35 ), ( 4 => 54 ), ( 5 => 34 ), ( 6 => 23 ), ( 7 => 67 ), ( 8 => 71 ), ( 9 => 24 ), ( 12 => 44 )]", tree.toString());
		
	}
	
	@Test
	public void testDeleteBadValue() {
		tree.delete(81); // not in tree
		
		assertEquals("[( 2 => 35 ), ( 4 => 54 ), ( 5 => 34 ), ( 6 => 23 ), ( 7 => 67 ), ( 8 => 71 ), ( 9 => 24 ), ( 12 => 44 )]", tree.toString());
		
	}

}	
