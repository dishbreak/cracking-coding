package com.dishbreak.cci.recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class RobotGameTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testShortPaths() {
        List<String> expected = new ArrayList<>();
        
        expected.add("( 0, 0 ) -> ( 1, 0 )");
        expected.add("( 0, 0 ) -> ( 1, 0 ) -> ( 1, 1 )");
        expected.add("( 0, 0 ) -> ( 0, 1 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 1, 1 )");
        
        assertEquals(expected, RobotGame.getAllPaths(2));
    }

}
