package com.dishbreak.cci.stacks_and_queues;

public interface Stack<T> {

    public T pop();

    public T peek();

    public void push(T data);

    public boolean isEmpty();

}
