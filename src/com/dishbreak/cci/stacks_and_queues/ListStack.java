package com.dishbreak.cci.stacks_and_queues;

import java.util.Comparator;

import com.dishbreak.cci.linked_lists.*;

public class ListStack<T> implements Stack<T> {

    private Node<T> root = null;

    @Override
    public T pop() {
        if (root == null) {
            return null;
        } else {
            T data = root.getData();
            root = root.next();
            return data;
        }
    }

    @Override
    public T peek() {
        return (root != null) ? root.getData() : null;
    }

    @Override
    public void push(T data) {
        Node<T> head = new Node<T>(data);
        head.setNext(root);
        root = head;

    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append("(");

        Node<T> iter = root;
        while (iter != null) {
            buffer.append(iter.getData());
            if (iter.next() != null)
                buffer.append(",");
            iter = iter.next();
        }
        buffer.append(")");
        return buffer.toString();
    }

    public Node<T> getTop() {
        return root;
    }

    public void sort(Comparator<T> comparator) {
        ListStack<T> sortStack = new ListStack<>();

        while (!this.isEmpty()) {
            T temp = pop();
            T top = sortStack.peek();

            while (!sortStack.isEmpty() && comparator.compare(top, temp) > 0) {
                push(sortStack.pop());
            }
            sortStack.push(temp);
        }
        root = sortStack.getTop();
    }

}
