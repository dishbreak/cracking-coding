package com.dishbreak.cci.trees_and_graphs;

public class NodeEntry<K, V> extends Node<V> {
    
    private K key;
    
    public K key() { return key; }
    
    public void overwrite(NodeEntry<K,V> other) {
       key = other.key();
       setValue(other.value());
    }
    
    public NodeEntry(K key, V value) {
        super(value);
        this.key = key;
    }

}
