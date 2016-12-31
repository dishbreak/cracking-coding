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
    
    public static LinkedList<GraphNode> getShortestPath(GraphNode from, GraphNode to) {
        LinkedList<GraphNode> list = new LinkedList<>();
        
        Map<GraphNode, Integer> pathLength = new HashMap<>();
        Map<GraphNode, GraphNode> previousStep = new HashMap<>();
        Set<GraphNode> visited = new HashSet<>();
        
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(from);
        pathLength.put(from, 0);
        previousStep.put(from, null);
        
        while(!queue.isEmpty()) {
            GraphNode node = queue.remove();
            int steps = pathLength.get(node);
            node.visitNeighborWeights((e) -> {
                GraphNode n = e.getKey();
                Integer weight = e.getValue();
                if (!visited.contains(n)) {
                    int pathSteps = steps + weight;
                    if (!pathLength.containsKey(n) || pathLength.get(n) > pathSteps) {
                        pathLength.put(n, pathSteps);
                        previousStep.put(n, node);
                        queue.add(n);
                    }
                }
            });
            
            visited.add(node);
        }
        
        if (visited.contains(to)) {
            GraphNode iter = to;
            while (iter != null) {
                list.add(iter);
                iter = previousStep.get(iter);
            }
        }
        
        return list;
    }
}
