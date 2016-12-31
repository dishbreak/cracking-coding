package com.dishbreak.cci.recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoinPurseTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testBasicCaseFindMinSum() {
        assertEquals(3, CoinPurse.findMinSum(16));
    }
    
    @Test
    public void testNegativeInputFindMinSum() {
        assertEquals(0, CoinPurse.findMinSum(-5));
    }
    
    @Test 
    public void testZeroInputFindMinSum() {
        assertEquals(0, CoinPurse.findMinSum(0));
    }
    
    @Test
    public void testCoinCombosBasicInput() {
        assertEquals(0, CoinPurse.getCoinCombos(16));
    }

}
