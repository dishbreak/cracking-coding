package com.dishbreak.testlab.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GraphNodeTest {

	GraphNode alpha;
	GraphNode bravo;
	GraphNode charlie;
	GraphNode delta;
	GraphNode echo;
	GraphNode foxtrot;
	
	@Before
	public void setUp() throws Exception {
		alpha = new GraphNode("alpha");
		bravo = new GraphNode("bravo");
		charlie = new GraphNode("charlie");
		delta = new GraphNode("delta");
		echo = new GraphNode("echo");
		foxtrot = new GraphNode("foxtrot");
	}

	@Test
	public void testRoutePositive() {
		alpha.connect(bravo);
		alpha.connect(charlie);
		alpha.connect(delta);
		charlie.connect(delta);
		charlie.connect(foxtrot);
		delta.connect(echo);
		
		assertTrue(GraphNode.isRoutable(alpha, foxtrot));
	}
	
	@Test
	public void testRouteNegative() {
		alpha.connect(bravo);
		alpha.connect(charlie);
		alpha.connect(delta);
		charlie.connect(delta);
		charlie.connect(foxtrot);
		delta.connect(echo);
		
		assertFalse(GraphNode.isRoutable(delta, alpha));
	}

}
