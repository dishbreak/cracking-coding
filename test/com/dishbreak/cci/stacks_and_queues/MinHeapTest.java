package com.dishbreak.cci.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MinHeapTest {

    private MinHeap heap;

    @Before
    public void setUp() {
        heap = new MinHeap();
    }

    @Test
    public void testInsert() {
        heap.insert(3);

        assertEquals(3, heap.getRoot().intValue());
    }

    @Test
    public void testGetMin() {
        Integer[] values = { 6, 8, 11, 8, 7, 4, 20, 12, 11, 8, 5, 4, 1, 5, 6, 7, 16, 7, 16, 2, 2, 14, 16, 14, 13, 12,
                17, 17, 11, 12 };

        for (Integer value : values) {
            heap.insert(value);
        }

        assertEquals(1, heap.getRoot().intValue());
    }

    @Test
    public void testPopMin() {
        Integer[] values = { 6, 8, 11, 8, 7, 4, 20, 12, 11, 8, 5, 4, 1, 5, 6, 7, 16, 7, 16, 2, 2, 14, 16, 14, 13, 12,
                17, 17, 11, 12 };

        for (Integer value : values) {
            heap.insert(value);
        }

        assertEquals(1, heap.popRoot().intValue());
        assertEquals(2, heap.getRoot().intValue());
    }

}
