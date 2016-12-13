package com.dishbreak.cci.trees_and_graphs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

    private GraphNode alpha;
    private GraphNode bravo;
    private GraphNode charlie;
    private GraphNode delta;
    private GraphNode echo;
    
    @Before
    public void setUp() throws Exception {
        alpha = new GraphNode("alpha");
        bravo = new GraphNode("bravo");
        charlie = new GraphNode("charlie");
        delta = new GraphNode("delta");
        echo = new GraphNode("echo");
    }

    @Test
    public void testIsRoutable() {
        GraphNode.connect(alpha, bravo);
        GraphNode.connect(alpha, charlie);
        GraphNode.connect(bravo, charlie);
        GraphNode.connect(bravo, delta);
        GraphNode.connect(charlie, delta);

        assertTrue(Graph.isRoutable(alpha, delta));
        assertFalse(Graph.isRoutable(alpha, echo));
    }

}
