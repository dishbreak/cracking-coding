package com.dishbreak.testlab.tree.linked;

import java.util.*;
import java.util.function.Predicate;

public class SumPathsTree {
    
    public static class Node {
        private final int value;
        private Node left;
        private Node right;
        
        public Node left() {
            return left;
        }
        
        public void setLeft(Node left) {
            this.left = left;
        }
        
        public Node right() {
            return right;
        }
        
        public void setRight(Node right) {
            this.right = right;
        }
        
        public Node(int value) {
            this.value = value;
        }
        
        public int value() {
            return value;
        }
        
        public String toString() {
            return Integer.toString(value);
        }
       
    }
        
    Node root;
    
    public void setRoot(Node root) {
        this.root = root;
    }
    
    public Node getRoot() {
        return root;
    }
    
    public List<Node> findAllMatches(Predicate<Node> test) {
        List<Node> result = new ArrayList<>();
        
        visit(root, test, result);
        
        return result;
    }

    private void visit(Node node, Predicate<Node> test, List<Node> result) {
        List<Node> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(node);
        
        visit(nodesToVisit, test, result);
        
    }

    private void visit(List<Node> nodesToVisit, Predicate<Node> test, List<Node> result) {
        List<Node> nextLayer = new LinkedList<>();
        
        for(Node node : nodesToVisit) {
            if (test.test(node)) {
                result.add(node);
            }
            if (node.left() != null) nextLayer.add(node.left());
            if (node.right() != null) nextLayer.add(node.right());
        }
        
        if (nextLayer.isEmpty()) return;
        
        visit(nextLayer, test, result);
    }
    
    public static SumPathsTree buildFromArray(int [] values) {
        SumPathsTree tree = new SumPathsTree();
        
        Node[] nodes = new Node[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new Node(values[i]);
        }
        
        tree.setRoot(buildSubTree(0, nodes));
        
        return tree;
    }
    
    
    private static Node buildSubTree(int i, Node[] nodes) {
        if (i > nodes.length - 1) return null;
        
        Node result = nodes[i];
        result.setLeft(buildSubTree(2*i + 1, nodes));
        result.setRight(buildSubTree(2*i + 2, nodes));
        
        return result;
    }

    public List<String> findAllPaths(int target) {
        List<String> results = new ArrayList<>();
        findSum(root, target, new ArrayList<>(), 0, results);
        return results;
    }
    
    private void findSum (Node tail, int target, ArrayList<Integer> buffer, int level, List<String> results) {
        if (tail == null) return;
        
        int remaining = target;
        buffer.add(tail.value());
        for (int i = level; i > -1; i--) {
            remaining -= buffer.get(i);
            if (remaining == 0) {
                results.add(pathToString(buffer, i, level));
            }
        }
        
        @SuppressWarnings("unchecked")
        ArrayList<Integer> bufferLeft = (ArrayList<Integer>) buffer.clone();
        @SuppressWarnings("unchecked")
        ArrayList<Integer> bufferRight = (ArrayList<Integer>) buffer.clone();
        
        findSum(tail.left, target, bufferLeft, level + 1, results);
        findSum(tail.right, target, bufferRight, level + 1, results);

    }
    
    private String pathToString(ArrayList<Integer> buffer, int startingPoint, int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = startingPoint; i <= level; i++) {
            builder.append(buffer.get(i));
            if (i != level) builder.append(" -> ");
        }
        return builder.toString();
    }

}
