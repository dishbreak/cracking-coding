package com.dishbreak.cci.trees_and_graphs;

import java.lang.reflect.Array;
import java.util.function.*;
import java.util.*;

public class GenericBinaryTree<T> {
    
    private Node<T> root = null;
    
    
    public GenericBinaryTree() {
        
    }
    
    
    protected void setRoot(Node<T> node) {
        root = node;
    }
    
    protected Node<T> getRoot() {
        return root;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public GenericBinaryTree(T[] values) {
        @SuppressWarnings("unchecked")
        Node<T>[] nodes = (Node<T>[]) Array.newInstance(Node.class, values.length);
        
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new Node<T>(values[i]);
        }
        
        buildSubtree(0, nodes);
        
        this.root = nodes[0];
    }
    
    private void buildSubtree(int index, Node<T>[] nodes) {
        Node<T> node = nodes[index];
        
        int leftIdx = 2 * index + 1;
        int rightIdx = 2 * index + 2;
        
        if (leftIdx < nodes.length) {
            node.setLeft(nodes[leftIdx]);
            buildSubtree(leftIdx, nodes);
        }
        
        if (rightIdx < nodes.length) {
            node.setRight(nodes[rightIdx]);
            buildSubtree(rightIdx, nodes);
        }
    }
    
    public List<T> findAllMatches(Predicate<T> pred) {
        
        List<T> results = new LinkedList<>();
        if (root == null) return results;
        
        Queue<Node<T>> queue = new LinkedList<>();
        
        queue.add(root);
        
        while(!queue.isEmpty()) {
            Node<T> node = queue.remove();
            if (pred.test(node.value())) results.add(node.value());
            if (node.left() != null) queue.add(node.left());
            if (node.right() != null) queue.add(node.right());
        }
        
        return results;
    }
    
    public List<T> listAllValues() {
        return findAllMatches(value -> true);
    }
    
    public int getLongestPath() {
        int steps = getLongestPath(root, 0);
        return steps;
    }
    
    private int getLongestPath(Node<T> node, int steps) {
        if (node == null) return steps;
        steps++;
        return Math.max(getLongestPath(node.right(), steps), 
                getLongestPath(node.left(), steps));
    }
    
    public int getShortestPath() {
        int steps = getShortestPath(root, 0);
        return steps;
    }
    
    private int getShortestPath(Node<T> node, int steps) {
        if (node == null) return steps;
        steps++;
        return Math.min(getShortestPath(node.right(), steps), 
                getShortestPath(node.left(), steps));
    }
    
    public boolean isBalanced() {
        return Math.abs(getLongestPath() - getShortestPath()) <= 1; 
    }
    
    public boolean deleteSubtreeAt(Predicate<T> pred)  {
        
        Node<T> target = findNodeAt(pred);
        boolean result = target != null;
        
        if (target == getRoot()) {
            setRoot(null);
        } else if (result) {
            target.parent().deleteChild(target);
        }
        
        return result;
    }
    
    private Node<T> findNodeAt(Predicate<T> pred) {
        // TODO Auto-generated method stub
        return findNodeAt(pred, getRoot());
    }


    private Node<T> findNodeAt(Predicate<T> pred, Node<T> startingPoint) {
        if (isEmpty()) return null;
        
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(startingPoint);
        while(!queue.isEmpty()) {
            Node<T> node = queue.remove();
            if (pred.test(node.value())) return node;
            if (node.left() != null) queue.add(node.left());
            if (node.right() != null) queue.add(node.right());
        }
        
        return null;
    }
    
    List<List<Node<T>>> getValuesAtEachLevel() {
        List<List<Node<T>>> results = new ArrayList<>();
        
        List<Node<T>> firstLevel = new LinkedList<>();
        firstLevel.add(getRoot());
        results.add(firstLevel);
        
        loadAtLevel(results, 1);
        
        return results;
    }
    
    private void loadAtLevel(List<List<Node<T>>> list, int level) {
        List<Node<T>> levelList = new LinkedList<Node<T>>();
        
        for (Node<T> node : list.get(level - 1)) {
            if (node.left() != null) levelList.add(node.left());
            if (node.right() != null) levelList.add(node.right());
        }
        
        if (levelList.isEmpty()) return;
        
        level++;
        list.add(levelList);
        loadAtLevel(list, level);
    }
    
    public Node<T> getNextSuccessor(Node<T> node) {
        if (node.isLeafNode()) {
            if (node.isLeftChild()) {
                return node.parent();
            } else if (node.isRightChild()) {
                if (node.parent() != null)  {
                    if (node.parent().isLeftChild()) {
                        return node.parent().parent();
                    } else {
                        Node<T> iter = node; 
                        while (iter != null && iter.isRightChild()) {
                            iter = iter.parent();
                        }
                        if (iter != null) iter = iter.parent();
                        return iter;
                    }
                }
            }
            return null; 
        } else {
            Node<T> iter = node.right();
            while(iter != null && !iter.isLeafNode()) {
                iter = iter.left();
            }
            return iter;
        }
    }
    
    private Node<T> searchSubtree(Predicate<Node<T>> pred, Node<T> startingPoint) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(startingPoint);
        
        while(!queue.isEmpty()) {
            Node<T> node = queue.remove();
            if (pred.test(node)) return node;
            node.addChildrenToQueue(queue);
        }
        
        return null;
    }
    
    public Node<T> getClosestCommonAncestor(Node<T> oneNode, Node<T> otherNode) {
        Node<T> result = null;
        
        // Case 1: oneNode is an ancestor of otherNode
        result = (searchSubtree(n -> {return n.equals(otherNode);}, oneNode) != null) ? oneNode : null;
        
        // Case 2: otherNode is an ancestor of oneNode
        if (result == null) {
            result = (searchSubtree(n -> {return n.equals(oneNode);}, otherNode) != null) ? otherNode : null;
        }
        
        // Case 3: oneNode and otherNode have a common ancestor. 
        if (result == null) {
            Function<Node<T>, Node<T>> getChild = (oneNode.isRightChild()) ?  n -> n.left() :  n -> n.right();
            Node<T> iter = oneNode.parent();
            
            while(iter != null) {
                result = (searchSubtree(n -> { return n.equals(otherNode); }, getChild.apply(iter)) != null) ? iter : null;
                if (result != null) break;
                iter = iter.parent();
            }
        }
        
        return result;
    }
    
    public boolean isSubtree(GenericBinaryTree<T> other) {
        Queue<Node<T>> startingQueue = new LinkedList<>();
        Stack<Node<T>> stack = new Stack<>();
        stack.push(getRoot());
        
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (node.equals(other.getRoot())) startingQueue.add(node);
            node.addChildrenToStack(stack);
        }
        
        boolean result = true;
        while (!startingQueue.isEmpty()) {
            Queue<Node<T>> queue = new LinkedList<>();
            queue.add(startingQueue.remove());
            
            Queue<Node<T>> referenceQueue = new LinkedList<>();
            referenceQueue.add(other.getRoot());
            
            result = true;
            while (!queue.isEmpty() && result == true) {
                Node<T> node = queue.remove();
                Node<T> otherNode = referenceQueue.remove();
                if (!node.equals(otherNode)) {
                    result = false;
                } else {
                    node.addChildrenToQueue(queue);
                    otherNode.addChildrenToQueue(referenceQueue);
                }
            }
            if (result) break;
        }
        
        return result;
    }
    
    
}
