package com.dishbreak.cci.trees_and_graphs;

class Node<T> {
    
    private Node<T> left = null;
    private Node<T> right = null;
    private Node<T> parent = null;
    private T value = null;
    
    public T value() { return value; }
    public Node<T> left() { return left; }
    public Node<T> right() { return right; }
    public Node<T> parent() { return parent; }
    
    public Node(T value) {
        this.value = value;
    }
    
    public void setRight(Node<T> node) {
        right = node;
        if (right != null) right.setParent(this);
    }
    
    public void setLeft(Node<T> node) {
        left = node;
        if (left != null) left.setParent(this);

    }
    
    public void setParent(Node<T> node) {
        parent = node;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public String toString() {
        return value.toString();
    }
    
    public boolean isLeafNode() {
        return left == null && right == null;
    }
    
    public void deleteChild(Node<T> node) {
        if (node == left) left = null;
        else if (node == right) right = null;
    }

}
