package com.dishbreak.testlab.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class GraphNode {
	private String name;
	
	private HashMap<GraphNode, Integer> connections;
	
	public GraphNode(String name) {
		this.name = name;
		connections = new HashMap<>();
	}
	
	public void connect(GraphNode node, Integer weight) {
		connections.put(node, weight);
	}
	
	public Set<GraphNode> getConnections() {
		return connections.keySet();
	}
	
	public void disconnect(GraphNode node) {
		connections.remove(node);
	}
	
	public Integer getWeight(GraphNode node) {
		return connections.get(node);
	}
	
	public void connect(GraphNode node) {
		connect(node, 1);
	}
	
	public String getName() {
		return this.name;
	}
	
	public static void connect(GraphNode a, GraphNode b) {
		a.connect(b);
		b.connect(a);
	}
	
	public static void connect(GraphNode a, GraphNode b, Integer weight) {
		a.connect(b, weight);
		b.connect(a, weight);
	}
	
	public static void disconnect(GraphNode a, GraphNode b) {
		a.disconnect(b);
		b.disconnect(a);
	}
	
	
	public static Set<GraphNode> discover(GraphNode startingNode) {
		Set<GraphNode> discoveredNodes = new HashSet<GraphNode>();
		
		Stack<GraphNode> stack = new Stack<>();
		stack.push(startingNode);
		while (!stack.isEmpty()) {
			GraphNode node = stack.pop();
			
			Iterator<GraphNode> iter = node.getConnections().iterator();
			while(iter.hasNext()) {
				GraphNode childNode = iter.next();
				if (!discoveredNodes.contains(childNode)) {
					discoveredNodes.add(childNode);
					stack.push(childNode);
				}
			}
  		}
		
		return discoveredNodes;
	}
	
	public static boolean isRoutable(GraphNode startingNode, GraphNode endingNode) {
		Set<GraphNode> network = discover(startingNode);
		return network.contains(endingNode);
	}
	
}
