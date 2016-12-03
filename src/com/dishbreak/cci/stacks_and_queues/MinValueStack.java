package com.dishbreak.cci.stacks_and_queues;

public class MinValueStack {

    private static class StackNode {
        public Integer data;
        public StackNode next;
        public StackNode min;

        public StackNode(Integer data) {
            this.data = data;
        }
    }

    private StackNode head = null;

    public void push(Integer data) {
        StackNode node = new StackNode(data);

        node.min = (head != null && head.min.data < node.data) ? head.min : node;
        node.next = head;
        head = node;

    }

    public void push(Integer... integers) {
        for (Integer value : integers) {
            push(value);
        }

    }

    public Integer min() {
        return (head != null) ? head.min.data : null;
    }

    public Integer pop() {
        Integer data = head.data;
        head = head.next;
        return data;
    }

}
