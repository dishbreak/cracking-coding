package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class ListStackTest {

    ListStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new ListStack<>();
    }

    @Test
    public void testPushOneItem() {
        stack.push(3);

        assertEquals("(3)", stack.toString());
    }

    @Test
    public void testPushMultipleItems() {
        Integer[] values = { 2, 4, 6, 10, 3, 4 };
        for (Integer value : values) {
            stack.push(value);
        }

        assertEquals("(4,3,10,6,4,2)", stack.toString());
    }

    @Test
    public void testPeekEmpty() {
        assertNull(stack.peek());
    }

    @Test
    public void testPopEmpty() {
        assertNull(stack.pop());
    }

    @Test
    public void testIsEmptyEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmptyNonEmpty() {
        stack.push(5);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPopNonEmpty() {
        Integer[] values = { 2, 4, 6, 10, 3, 4 };
        for (Integer value : values) {
            stack.push(value);
        }

        assertEquals(4, stack.pop().intValue());
        assertEquals("(3,10,6,4,2)", stack.toString());
    }

    @Test
    public void testPeekNonEmpty() {
        Integer[] values = { 2, 4, 6, 10, 3, 4 };
        for (Integer value : values) {
            stack.push(value);
        }

        assertEquals(4, stack.peek().intValue());
        assertEquals("(4,3,10,6,4,2)", stack.toString());
    }

    @Test
    public void testSort() {
        Integer[] values = { 2, 4, 6, 10, 3, 4 };
        for (Integer value : values) {
            stack.push(value);
        }

        stack.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null) {
                    return -1;
                } else if (o2 == null) {
                    return 1;
                } else if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0; // only possible if o1 == o2
                }
            }
        });

        assertEquals("(2,3,4,4,6,10)", stack.toString());
    }

}
