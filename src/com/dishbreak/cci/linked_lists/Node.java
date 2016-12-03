package com.dishbreak.cci.linked_lists;

public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node(Node<T> iter) {
        this.data = iter.data;
    }

    public Node<T> next() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
