package com.dishbreak.cci.trees_and_graphs;

import java.lang.reflect.Array;
import java.util.function.Predicate;
import java.util.*;

public class GenericBinaryTree<T> {
    
    Node<T> root = null;
    
    public GenericBinaryTree() {
        
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
    
    
}
