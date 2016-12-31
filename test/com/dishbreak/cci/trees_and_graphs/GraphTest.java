package com.dishbreak.cci.trees_and_graphs;

import static org.junit.Assert.*;

import java.util.LinkedList;

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
    
    @Test
    public void testFindShortestPath() {
        GraphNode foxtrot = new GraphNode("foxtrot");
        GraphNode golf = new GraphNode("golf");
        GraphNode hotel = new GraphNode("hotel");
        GraphNode india = new GraphNode("india");
        
        GraphNode.connect(alpha, bravo, 1);
        GraphNode.connect(alpha, charlie, 3);
        GraphNode.connect(alpha, delta, 2);
        GraphNode.connect(delta, echo, 3);
        GraphNode.connect(bravo, foxtrot, 2);
        GraphNode.connect(bravo, charlie, 1);
        GraphNode.connect(charlie, foxtrot, 4);
        GraphNode.connect(foxtrot, golf, 1);
        GraphNode.connect(golf, hotel, 2);
        GraphNode.connect(golf, india, 6);
        GraphNode.connect(hotel, india, 2);
        
        LinkedList<GraphNode> list = new LinkedList<>();
        list.add(india);
        list.add(hotel);
        list.add(golf);
        list.add(foxtrot);
        list.add(bravo);
        list.add(alpha);
        
        assertEquals(list, Graph.getShortestPath(alpha, india));
    }

}
