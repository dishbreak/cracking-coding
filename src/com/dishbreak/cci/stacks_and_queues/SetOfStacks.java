package com.dishbreak.cci.stacks_and_queues;

public class SetOfStacks implements Stack<Integer> {
    private static final int STACK_LIMIT = 10;

    private final int stackLimit;
    private ListStack<ArrayStack> stackOfStacks = null;

    public SetOfStacks(int stackSize) {
        this.stackLimit = stackSize;
    }

    public SetOfStacks() {
        this(STACK_LIMIT);
    }

    @Override
    public Integer pop() {
        Integer result = null;
        if (!stackOfStacks.isEmpty()) {
            result = stackOfStacks.peek().pop();
            if (stackOfStacks.peek().isEmpty()) {
                stackOfStacks.pop();
            }
        }

        return result;
    }

    @Override
    public Integer peek() {
        Integer result = null;

        if (!stackOfStacks.isEmpty()) {
            result = stackOfStacks.peek().peek();
        }

        return result;

    }

    @Override
    public void push(Integer data) {

        if (!stackOfStacks.isEmpty() && !stackOfStacks.peek().isFull()) {
            stackOfStacks.peek().push(data);
        } else {
            ArrayStack stack = new ArrayStack(stackLimit);
            stack.push(data);
            stackOfStacks.push(stack);
        }

    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return stackOfStacks.isEmpty();
    }

}
