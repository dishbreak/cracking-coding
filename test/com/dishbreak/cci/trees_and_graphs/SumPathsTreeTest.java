package com.dishbreak.cci.trees_and_graphs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class SumPathsTreeTest {
    
    private SumPathsTree tree;

    @Before
    public void setUp() throws Exception {
    }


    
    @Test
    public void testFindAllPaths() {
        Integer[] numbers = {5, -5, 0, 1, 4, -5, 1, 3, -2, -5, -4, 3, -3, -2, 3 };
        tree = new SumPathsTree(numbers);
        
        List<String> actual = tree.findAllPaths(0);
        
        List<String> expected = new ArrayList<>();
        
        expected.add("5 -> -5");
        expected.add("4 -> -4");
        expected.add("5 -> -5 -> 4 -> -4");
        expected.add("0");
        expected.add("5 -> 0 -> -5");
        
        assertEquals(expected, actual);
    }

}
