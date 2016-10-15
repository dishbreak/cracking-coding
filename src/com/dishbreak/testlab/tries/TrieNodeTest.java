package com.dishbreak.testlab.tries;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrieNodeTest {

	@Test
	public void testPopData() {
		TrieNode node = new TrieNode("bongo");
		
		assertEquals((Character) 'b', node.popData());
		assertEquals("ongo", node.getData());
	}
	
	@Test public void testInsertChildToLeaf() {
		TrieNode node = new TrieNode("bongo");
		node.setLeaf(true);
		
		TrieNode childNode = new TrieNode("ingo");
		
		node.insertChild(childNode);
		
		assertFalse(node.isLeaf());
		assertTrue(node.hasChild('o'));
		assertEquals("ngo", node.child('o').getData());
		assertTrue(node.hasChild('i'));
		assertEquals("ngo", node.child('i').getData());
		
	}

}
