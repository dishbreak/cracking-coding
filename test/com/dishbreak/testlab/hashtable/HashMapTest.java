package com.dishbreak.testlab.hashtable;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest {

    private HashMap map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap();
    }

    @Test
    public void testPut() {
        map.put("Vishal", 28);

        assertEquals(28, map.get("Vishal").intValue());
        assertNull(map.get("Pookie"));
    }

    @Test
    public void testPutLotsOfStuff() {
        String[] names = { "Vishal", "David", "Sam", "Hema", "Padma", "Churro" };

        Integer[] values = { 28, 34, 32, 29, 36, 4 };

        for (int i = 0; i < 6; i++) {
            map.put(names[i], values[i]);
        }

        for (int i = 0; i < 6; i++) {
            assertEquals(values[i], map.get(names[i]));
        }
    }

}
