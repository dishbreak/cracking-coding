package com.dishbreak.cci.recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FibonacciGenTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testRecursive() {
        assertEquals(1, FibonacciGen.getFibNum(1));
        assertEquals(1, FibonacciGen.getFibNum(2));
        assertEquals(2, FibonacciGen.getFibNum(3));
        assertEquals(3, FibonacciGen.getFibNum(4));
        assertEquals(5, FibonacciGen.getFibNum(5));
        assertEquals(8, FibonacciGen.getFibNum(6));
        assertEquals(13, FibonacciGen.getFibNum(7));
        assertEquals(21, FibonacciGen.getFibNum(8));
        assertEquals(34, FibonacciGen.getFibNum(9));
    }

    @Test
    public void testIterative() {
        assertEquals(1, FibonacciGen.getFibNumIter(1));
        assertEquals(1, FibonacciGen.getFibNumIter(2));
        assertEquals(2, FibonacciGen.getFibNumIter(3));
        assertEquals(3, FibonacciGen.getFibNumIter(4));
        assertEquals(5, FibonacciGen.getFibNumIter(5));
        assertEquals(8, FibonacciGen.getFibNumIter(6));
        assertEquals(13, FibonacciGen.getFibNumIter(7));
        assertEquals(21, FibonacciGen.getFibNumIter(8));
        assertEquals(34, FibonacciGen.getFibNumIter(9));
    }
}
