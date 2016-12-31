
package com.dishbreak.testlab.dynamic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppleOrchardTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetMostApples() {
        int[][] orchard = {
                { 1, 3, 4, 3, 2, 1},
                { 2, 2, 1, 5, 2, 6},
                { 4, 1, 5, 3, 7, 2},
                { 2, 1, 4, 5, 3, 1}
        };
        
        assertEquals(30, AppleOrchard.getMostApples(orchard));
    }

}
