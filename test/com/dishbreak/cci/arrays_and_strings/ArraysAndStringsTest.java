package com.dishbreak.cci.arrays_and_strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArraysAndStringsTest {

    @Test
    public void testUniqueStringHashMapTrue() {
        assertTrue(ArraysAndStrings.hasUniqueCharactersHashMap("bong"));
    }

    @Test
    public void testUniqueStringHashMapFalse() {
        assertFalse(ArraysAndStrings.hasUniqueCharactersHashMap("boong"));
    }

    @Test
    public void testUniqueStringHashMapNull() {
        assertTrue(ArraysAndStrings.hasUniqueCharactersHashMap(null));
    }

    @Test
    public void testUniqueStringHashMapEmpty() {
        assertTrue(ArraysAndStrings.hasUniqueCharactersHashMap(""));
    }

    @Test
    public void testUniqueStringN2True() {
        assertTrue(ArraysAndStrings.hasUniqueCharactersN2("bong"));
    }

    @Test
    public void testUniqueStringN2False() {
        assertFalse(ArraysAndStrings.hasUniqueCharactersN2("boong"));
    }

    @Test
    public void testUniqueStringN2Null() {
        assertTrue(ArraysAndStrings.hasUniqueCharactersN2(null));
    }

    @Test
    public void testUniqueStringN2Empty() {
        assertTrue(ArraysAndStrings.hasUniqueCharactersN2(""));
    }

    @Test
    public void testReverseNormal() {
        assertEquals("ognob", ArraysAndStrings.reverse("bongo"));
    }

    @Test
    public void testAnagram() {
        assertTrue(ArraysAndStrings.isAnagram("waterbottle", "bottlewater"));
    }

    @Test
    public void testRemoveDuplicatesConsecutive() {
        assertEquals("a", ArraysAndStrings.removeDuplicates("aaaa"));
    }

    @Test
    public void testEncodeSpaces() {
        assertEquals("hi%20you%20boo", ArraysAndStrings.encodeSpaces("hi you boo"));
    }

    @Test
    public void testRotateMatrix() {
        byte[][] input = { { 'a', 'b', 'c', 'd' }, { 'e', 'f', 'g', 'h' }, { 'i', 'j', 'k', 'l' },
                { 'm', 'n', 'o', 'p' } };

        byte[][] expected = { { 'm', 'i', 'e', 'a' }, { 'n', 'j', 'f', 'b' }, { 'o', 'k', 'g', 'c' },
                { 'p', 'l', 'h', 'd' } };

        byte[][] output = ArraysAndStrings.rotate(input);

        assertEquals("expected " + expected.length + " rows", expected.length, output.length);

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals("failed match on row " + i, expected[i], output[i]);
        }
    }

    @Test
    public void testZeroOutMatrix() {
        Integer[][] input = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 } };

        Integer[][] expected = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 },
                { 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 }, { 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 },
                { 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 }, { 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        ArraysAndStrings.zeroOutRowsandCols(input);
        assertEquals("expected " + expected.length + " rows", expected.length, input.length);

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals("failed match on row " + i, expected[i], input[i]);
        }
    }

    @Test
    public void testRotation() {
        assertTrue(ArraysAndStrings.isRotation("erbottlewat", "waterbottle"));
    }

    @Test
    public void testNegativeRotation() {
        assertFalse(ArraysAndStrings.isRotation("boogle", "wahoo"));
    }

}
