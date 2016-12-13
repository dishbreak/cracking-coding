package com.dishbreak.cci.trees_and_graphs;
import java.util.*;
import java.util.function.Consumer;

public class GraphNode {
    private final String name;
    private Map<GraphNode, Integer> connections = new HashMap<>();
    
    public GraphNode(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    public void connect(GraphNode other, int weight) {
        connections.put(other, weight);
    }
    
    public void disconnect(GraphNode other) {
        connections.remove(other);
    }
    
    public void visitNeighbors(Consumer<GraphNode> func) {
        for(GraphNode node : connections.keySet()) {
            func.accept(node);
        }
    }
    
    public static void connect(GraphNode one, GraphNode other, int weightOneToOther, int weightOtherToOne) {
        one.connect(other, weightOneToOther);
        other.connect(one, weightOtherToOne);
    }
    
    public static void connect(GraphNode one, GraphNode other, int weight) {
        connect(one, other, weight, weight);
    }
    
    public static void connect(GraphNode one, GraphNode other) {
        connect (one, other, 1, 1);
    }
} 
