package com.dishbreak.testlab.tree.linked;

import java.util.*;
import java.util.function.Predicate;

public class SumPathsTree {
    
    @SuppressWarnings("unused")
    private static final int NO_MATCH = 0;
    private static final int LEFT_MATCH = 1;
    private static final int RIGHT_MATCH = 2;
    private static final int BOTH_MATCH = 3;
    
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
        
        public int childMatch(Predicate<Node> test) {
            int result = 0;
            if (left != null && test.test(left)) result = result | 1;
            if (right != null && test.test(right)) result = result | 2;
            return result;
        }
       
    }
    
    @SuppressWarnings("serial")
    public static class Path extends LinkedList<Node> {
        private int sum;
        Node end;
        
        public Path() {
            
        }
        
        public Path(Path other) {
            super(other);
            sum = other.getSum();
            end = other.getEnd();
        }
        
        @Override
        public boolean add(Node n) {
            end = n;
            sum += n.value();
            return super.add(n);
        }
        
        public int getSum() {
            return sum;
        }
        
        public Node getEnd() {
            return end;
        }
        
        public void addLeft() {
            add(end.left());
        }
        
        public void addRight() {
            add(end.right());
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
        for (int i = 0; i < values.length - 1; i++) {
            nodes[i] = new Node(values[i]);
        }
        
        tree.setRoot(buildSubtree(nodes, 0, values.length - 1));
        
        return tree;
    }
    
    private static Node buildSubtree(Node[] nodes, int start, int end) {
        
        int midpoint = (start + end) / 2;
        Node selectedNode = nodes[midpoint];
        if (start == end) {
            return null;
        } else if (midpoint == start) {
            selectedNode.setRight(nodes[end]);
        } else if (end - start == 2) {
            selectedNode.setLeft(nodes[start]);
            selectedNode.setRight(nodes[end]);
        } else {
            selectedNode.setLeft(buildSubtree(nodes, start, midpoint - 1));
            selectedNode.setRight(buildSubtree(nodes, midpoint + 1, end));
        }
        return selectedNode;
    }
    
    public List<Path> findAllPaths(int target) {
        List<Path> result = new ArrayList<>();
        
        List<Node> startingPoints = findAllMatches(n -> { return n.value <= target; } );
        
        for (Node node : startingPoints) {
            result.addAll(findAllPathsFromNode(node, target));
        }
        
        return result;
    }
    
    private List<Path> findAllPathsFromNode(Node startingPoint, int target) {
        List<Path> result = new ArrayList<>();
        List<Path> working = new ArrayList<>();
        
        Path startingPath = new Path();
        startingPath.add(startingPoint);
        
        working.add(startingPath);
        
        while (!working.isEmpty()) {
            ListIterator<Path> iter = working.listIterator();
            while(iter.hasNext()) {
                Path path = iter.next();
                if (path.getSum() == target) {
                    iter.remove();
                    result.add(path);
                    continue;
                }
                
                Node end = path.getEnd();
                int childSearch = end.childMatch(n -> { return (end.value() + n.value()) <= target; });
                if (childSearch == LEFT_MATCH) {
                    path.addLeft();
                } else if (childSearch == RIGHT_MATCH) {
                    path.addRight();
                } else if (childSearch == BOTH_MATCH) {
                    Path otherPath = new Path(path);
                    path.addLeft();
                    otherPath.addRight();
                    iter.add(otherPath);
                } else { //NO_MATCH
                    iter.remove();
                }
            }
        }
        
        return result;
    }


}
