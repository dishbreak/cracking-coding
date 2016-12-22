package com.dishbreak.cci.sorting_and_searching;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testFindRotated() {
        int[] input = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
        
        assertEquals(8, ArrayUtils.findInRotated(input, 5));
        assertEquals(1, ArrayUtils.findInRotated(input, 16));
    }

    @Test
    public void testMergeArrays() {
        int[] largeArray = { 1, 3, 5, 7, 9, 11, 12, 0, 0, 0 };
        int[] smallArray = { 2, 4, 6 };
        
        int[] mergedArray = {1, 2, 3, 4, 5, 6, 7, 9, 11, 12 };
        ArrayUtils.mergeArrays(largeArray, smallArray);
        
        assertArrayEquals(mergedArray, largeArray);
        
    }
    
    @Test
    public void testSortAnagrams() {
        String[] input = {
                "book",
                "bar",
                "koob",
                "arb",
                "kobo",
                "rab"
        };
        
        String[] expected = {
                "bar",
                "arb",
                "rab",
                "book",
                "koob",
                "kobo"
        };
        
        assertArrayEquals(expected, ArrayUtils.sortAnagrams(input));
    }
}
