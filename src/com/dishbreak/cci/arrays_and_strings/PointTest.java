package com.dishbreak.cci.arrays_and_strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

    @Test
    public void testTranslate1() {
        Point point = new Point(0, 0);
        point.translate(3);
        Point expected = new Point(0, 3);
        assertTrue("expected " + expected + ", got " + point, point.equals(expected));
    }

}
