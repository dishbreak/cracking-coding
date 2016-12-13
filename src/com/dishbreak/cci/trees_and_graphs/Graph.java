package com.dishbreak.cci.trees_and_graphs;
import java.util.*;

public class Graph {
    public static boolean isRoutable(GraphNode one, GraphNode other) {
        Queue<GraphNode> queue = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();
        queue.add(one);
        while(!queue.isEmpty()) {
            GraphNode node = queue.remove();
            if (node == other) return true;
            node.visitNeighbors((n) -> { 
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                }
            });
        }
        return false;
    }
}
