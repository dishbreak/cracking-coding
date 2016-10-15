package com.dishbreak.testlab.tries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TrieTest {
	
	private Trie trie; 
	
	@Before
	public void setUp() {
		trie = new Trie();
		trie.insert("horse");
		trie.insert("hearse");
		trie.insert("house");
		trie.insert("job");
		trie.insert("jab");
	}

	@Test
	public void testSearch() {
		assertTrue(trie.search("horse"));
		assertFalse(trie.search("boom"));
	}

}
