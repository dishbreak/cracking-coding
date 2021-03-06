package com.dishbreak.cci.trees_and_graphs;

import java.util.Queue;
import java.util.Stack;

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
    
    public boolean isLeftChild() {
        boolean result = false;
        if (parent != null && parent.left() == this) result = true;
        return result;
    }
    
    public boolean isRightChild() {
        boolean result = false;
        if (parent != null && parent.right() == this) result = true;
        return result;
    }
    
    public void deleteChild(Node<T> node) {
        if (node == left) left = null;
        else if (node == right) right = null;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Node) {
            Node<?> node = (Node<?>) o;
            result = value.equals(node.value());
        }
        return result;
    }
    
    public int hashCode() {
        return value.hashCode();
    }
    
    public void addChildrenToStack(Stack<Node<T>> stack) {
        if (this.left() != null) stack.push(this.left());
        if (this.right() != null) stack.push(this.right());
    }
    
    public void addChildrenToQueue(Queue<Node<T>> queue) {
        if (this.left() != null) queue.add(this.left());
        if (this.right() != null) queue.add(this.right());
    }

}
