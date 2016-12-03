package com.dishbreak.cci.arrays_and_strings;

import java.util.HashMap;
import java.util.Map;

public class ArraysAndStrings {

    public static boolean hasUniqueCharactersHashMap(String input) {
        if (input == null) {
            return true;
        }

        if (input.length() == 0) {
            return true;
        }

        HashMap<Character, Boolean> hitMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (hitMap.containsKey(c)) {
                return false;
            } else {
                hitMap.put(c, true);
            }
        }
        return true;
    }

    public static boolean hasUniqueCharactersN2(String input) {
        if (input == null) {
            return true;
        }

        if (input.length() == 0) {
            return true;
        }

        char[] array = input.toCharArray();

        Integer length = input.length();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    j++;
                } else {
                    if (array[i] == array[j]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static String reverse(String input) {
        char[] array = input.toCharArray();
        Integer start = 0;
        Integer end = array.length - 1; // would be an iteration to the end in
                                        // c/c++

        if (end < 0) {
            return null;
        }

        while (start != end) {
            Character buffer = array[end];
            array[end] = array[start];
            array[start] = buffer;
            end--;
            start++;
        }

        return new String(array);

    }

    public static boolean isAnagram(String left, String right) {
        Map<Character, Integer> sigLeft = getSignature(left);
        Map<Character, Integer> sigRight = getSignature(right);

        for (Character key : sigLeft.keySet()) {
            if (!sigRight.containsKey(key) && sigRight.get(key) != sigLeft.get(key)) {
                return false;
            }
        }

        return true;
    }

    private static Map<Character, Integer> getSignature(String input) {
        Map<Character, Integer> signature = new HashMap<>();

        for (char c : input.toCharArray()) {
            if (signature.containsKey(c)) {
                signature.put(c, signature.get(c) + 1);
            } else {
                signature.put(c, 1);
            }
        }

        return signature;
    }

    public static String removeDuplicates(String input) {
        char[] array = input.toCharArray();

        boolean[] hitList = new boolean[256];

        for (int i = 0; i < 256; i++) {
            hitList[i] = false;
        }

        int j = 0;
        for (int i = 0; i < input.length(); i++) {
            if (!hitList[array[i]]) {
                hitList[array[i]] = true;
                array[j] = array[i];
                j++;
            }
        }

        return new String(array, 0, j);
    }

    public static String encodeSpaces(String input) {
        char[] array = input.toCharArray();

        int spaces = 0;
        for (char c : array) {
            if (c == ' ') {
                spaces++;
            }
        }
        // we're replacing one character with 3, so we need a net of 2
        // extra per space.
        char[] encoded_array = new char[array.length + (2 * spaces)];

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                encoded_array[j] = '%';
                j++;
                encoded_array[j] = '2';
                j++;
                encoded_array[j] = '0';
            } else {
                encoded_array[j] = array[i];
            }
            j++;
        }

        return new String(encoded_array);
    }

    public static byte[][] rotate(byte[][] input) {
        for (byte[] row : input) {
            if (row.length != input.length) {
                return null;
            }
        }

        byte[][] output = new byte[input.length][input.length];

        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < input.length; y++) {
                Point point = new Point(x, y);
                point.translate(input.length - 1);
                output[point.x][point.y] = input[x][y];
            }
        }

        return output;
    }

    public static void zeroOutRowsandCols(Integer[][] input) {
        Integer cols = input[0].length;

        for (Integer[] row : input) {
            if (row.length != cols) {
                return;
            }
        }

        Integer[] zeroCols = new Integer[cols];
        Integer[] zeroRows = new Integer[input.length];

        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < cols; y++) {
                if (input[x][y] == 0) {
                    zeroCols[y] = 1;
                    zeroRows[x] = 1;
                }
            }
        }

        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < cols; y++) {
                if (zeroCols[y] != null || zeroRows[x] != null) {
                    input[x][y] = 0;
                }
            }
        }
    }

    public static boolean isRotation(String s1, String s2) {
        String base = s1 + s1;
        return base.contains(s2);
    }
}
