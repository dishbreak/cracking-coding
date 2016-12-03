package com.dishbreak.testlab.tree.linked;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LinkedSearchTreeTest {
	
	private LinkedSearchTree tree;
	private static final int[] keys = {5, 6, 12, 4, 7, 9, 8, 2};
	private static final int[] values = {34, 23, 44, 54, 67, 24, 71, 35};
	
	@Before
	public void setUp() throws Exception {
		
		
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
		LinkedTreeNode node = tree.search((LinkedTreeNode target) -> { return target.key().intValue() == 8; });
				
		assertEquals(71, node.value().intValue());
	}
	
	@Test
	public void testSearchEmpty() {
		tree = new LinkedSearchTree();
		
		LinkedTreeNode node = tree.search((LinkedTreeNode target) -> { return target.key().intValue() == 8; });
		
		assertNull(node);
	}
	
	@Test
	public void testSearchNegative() {
		LinkedTreeNode node = tree.search((LinkedTreeNode target) -> { return target.key().intValue() == 90;});
		
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
	
	@Test
	public void testGetHeight() {
		assertEquals(6, tree.getHeight());
	}
	
	@Test
	public void testFindNextInOrder() {
		int[] sortedKeys = { 2, 4, 5, 6, 7, 8, 9, 12};
		
		for (int i = 0; i < sortedKeys.length - 1; i++) {
			LinkedTreeNode node = tree.findNextInOrder(sortedKeys[i]);
			assertEquals("Failed finding successor of " + sortedKeys[i], (Integer) sortedKeys[i+1], node.key());
		}
		
		assertNull(tree.findNextInOrder(12));
	}
	
	@Test 
	public void testBalancedList() {
		int[] sortedKeys = { 2, 4, 5, 6, 7, 8, 9, 12};
		tree = new LinkedSearchTree(sortedKeys, values);
		assertTrue(tree.isBalanced());
	}
	
	@Test
	public void testInsertNodesAtLevel() {
		List<List<LinkedTreeNode>> nodes = tree.getNodesPerLevel();
		
		assertEquals("[[( 5 => 34 )], [( 4 => 54 ), ( 6 => 23 )], [( 2 => 35 ), ( 12 => 44 )], [( 7 => 67 )], [( 9 => 24 )], [( 8 => 71 )]]", nodes.toString());
	}
	
	@Test
	public void testSearchBreadthFirst() {
		LinkedTreeNode node = tree.search((LinkedTreeNode target) -> { return target.key().intValue() == 7; });

		assertEquals((Integer) 7, node.key());
	}
	
	@Test
	public void testCommonAncestorParent1() {
		
		LinkedTreeNode node1 = tree.getNode(6);
		LinkedTreeNode node2 = tree.getNode(9);
		
		assertEquals(node1, tree.getCommonAncestor(node1, node2));
	}
	
	@Test
	public void testCommonAncestorParent2() {
		
		LinkedTreeNode node1 = tree.getNode(9);
		LinkedTreeNode node2 = tree.getNode(6);
		
		assertEquals(node2, tree.getCommonAncestor(node1, node2));
	}
	
	@Test
	public void testCommonAncestorNotParent() {
		tree.insert(13, 56);
		tree.insert(15, 62);
		
		LinkedTreeNode node1 = tree.getNode(2);
		LinkedTreeNode node2 = tree.getNode(13);
		LinkedTreeNode expected = tree.getNode(5);
		
		assertEquals(expected, tree.getCommonAncestor(node1, node2));
	}
	
	

}	
