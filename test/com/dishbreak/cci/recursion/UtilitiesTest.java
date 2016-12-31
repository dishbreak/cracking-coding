package com.dishbreak.cci.recursion;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UtilitiesTest {

    private ArrayList<String> strings;
    
    @Before
    public void setUp() throws Exception {
        strings = new ArrayList<>();
        strings.add("alpha");
        strings.add("bravo");
        strings.add("charlie");
        strings.add("delta");
        strings.add("echo");
    }

    @Test
    public void testPluckNegativeIndex() {        
        assertEquals(strings, Utilities.pluck(-1, strings));
    }
    
    @Test
    public void testPluckTooLargeIndex() {
        
        assertEquals(strings, Utilities.pluck(strings.size() + 1, strings));
    }
    
    @Test
    public void testPluckIndexInside() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("alpha");
        expected.add("bravo");
        // expected.add("charlie"); // renove index 2
        expected.add("delta");
        expected.add("echo");
        
        assertEquals(expected, Utilities.pluck(2, strings));
    }

}
