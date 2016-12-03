package com.dishbreak.cci.stacks_and_queues;

public class CircularBuffer {

    private static final Integer QUEUE_SIZE = 10;

    private Integer[] array;

    private int start = 0;
    private int end = 0;

    public CircularBuffer() {
        this(QUEUE_SIZE);
    }

    public CircularBuffer(Integer size) {
        array = new Integer[size];
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return start == end;
    }

    public void enqueue(Integer data) {
        int nextIndex = (end + 1) % array.length;
        if (array[end] != null) {
            return;
        }
        array[end] = data;
        end = nextIndex;

    }

    public Integer dequeue() {
        Integer result = null;
        if (array[start] != null) {
            result = array[start];
            array[start] = null;
            start = (start + 1) % array.length;
        }
        return result;
    }

}
