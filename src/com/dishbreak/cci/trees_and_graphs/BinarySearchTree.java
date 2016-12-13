package com.dishbreak.cci.trees_and_graphs;

import java.lang.reflect.Array;

public class BinarySearchTree<V> extends GenericBinaryTree<Integer> {
    
    public 
    
    @SuppressWarnings("unchecked") 
    NodeEntry<Integer, V> getRootEntry() {
        return (NodeEntry<Integer, V>) super.getRoot();
    }
    
    @SuppressWarnings("unchecked")
    public void add(Integer key, V value) {
        NodeEntry<Integer, V> node = new NodeEntry<>(key, value);
        
        if (isEmpty()) {
            setRoot((Node<Integer>) node);
            return;
        }
        
        NodeEntry<Integer, V> iter = getRootEntry();
        
        while(true) {
            if (iter.key().equals(key)) {
                iter.setValue(value);
                break;
            } else if (iter.key() < key) {
                if (iter.left() != null) {
                    iter = (NodeEntry<Integer, V>) iter.left();
                } else {
                    iter.setLeft(node);
                    break;
                }
            } else {
                if (iter.right() != null) {
                    iter = (NodeEntry<Integer, V>) iter.right();
                } else {
                    iter.setRight(node);
                    break;
                }
            }
        }
    }
    
    public void delete(Integer key) {
        
        NodeEntry<Integer, V> node = findNode(key);
        
        if (node == null) return;
        
        delete(node);
        
    }
    
    @SuppressWarnings("unchecked")
    private NodeEntry<Integer, V> findNode(Integer key) {
        NodeEntry<Integer, V> iter = getRootEntry();
        
        while (iter != null) {
            if (iter.key().equals(key)) break;
            else if (iter.key() < key) iter = (NodeEntry<Integer, V>) iter.left();
            else iter = (NodeEntry<Integer, V>) iter.right();
        }
        
        return iter;
    }
    
    private void delete(NodeEntry<Integer, V> node) {
        if (node.isLeafNode()) node.parent().deleteChild(node);
        else if (node.right() != null) {
            @SuppressWarnings("unchecked")
            NodeEntry<Integer, V> smallestChild = getSmallestChild((NodeEntry<Integer, V>) node.right());
            node.overwrite(smallestChild);
            delete(smallestChild);
        } else {
            @SuppressWarnings("unchecked")
            NodeEntry<Integer, V> largestChild = getLargestChild((NodeEntry<Integer, V>) node.left());
            node.overwrite(largestChild);
            delete(largestChild);
        }
    }
    
    @SuppressWarnings("unchecked")
    private NodeEntry<Integer, V> getSmallestChild(NodeEntry<Integer, V> node) {
        NodeEntry<Integer, V> iter = node;
        
        while (iter.left() != null) iter = (NodeEntry<Integer, V>) iter.left();
        
        return iter;
    }
    
    @SuppressWarnings("unchecked")
    private NodeEntry<Integer, V> getLargestChild(NodeEntry<Integer, V> node) {
        NodeEntry<Integer, V> iter = node;
        
        while (iter.right() != null) iter = (NodeEntry<Integer, V>) iter.right();
        
        return iter;
    }
    
    public V get(Integer key) {
        
        NodeEntry<Integer, V> node = findNode(key);
        if (node == null) return null;
        else return node.value();
    }
    
    public static <V> BinarySearchTree<V> buildBalanced(Integer[] keys, V[] values) {
        int limit = Math.min(keys.length, values.length);
        
        @SuppressWarnings("unchecked")
        NodeEntry<Integer, V>[] nodeEntries = (NodeEntry<Integer, V>[]) Array.newInstance(NodeEntry.class, limit);
        
        for (int i = 0; i < limit; i++) {
            nodeEntries[i] = new NodeEntry<Integer, V>(keys[i], values[i]);
        }
        
        BinarySearchTree<V> tree = new BinarySearchTree<V>();
        
        @SuppressWarnings("unchecked")
        Node<Integer> root = (Node<Integer>) buildSubTree(nodeEntries, 0, limit-1);
        tree.setRoot(root);
        
        return tree;
    }
    
    private static <V> NodeEntry<Integer, V> buildSubTree(NodeEntry<Integer, V>[] nodeEntries, int indexStart, int indexEnd) {
        int midPoint = (indexStart + indexEnd) / 2;
        NodeEntry<Integer, V> node = nodeEntries[midPoint];
        if (indexStart != indexEnd) {
            if (midPoint != indexStart) {
                node.setLeft(buildSubTree(nodeEntries, indexStart, midPoint - 1));
            } 
            node.setRight(buildSubTree(nodeEntries, midPoint + 1, indexEnd));
        }        
        
        return node;
    }
 
}
