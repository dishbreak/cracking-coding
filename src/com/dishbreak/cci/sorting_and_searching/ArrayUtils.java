package com.dishbreak.cci.sorting_and_searching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class ArrayUtils {

    public ArrayUtils() {
        // TODO Auto-generated constructor stub
    }
    
    public static int findInSortedArray(int[] array, int target) {
        int index = -1;
        
        int lower = 0;
        int upper = array.length - 1;
        
        while (lower <= upper) {
            int midpoint = (lower + upper) / 2;
            if (array[midpoint] == target) {
                index = midpoint;
                break;
            } else if (array[midpoint] < target) {
                lower = midpoint + 1;
            } else {
                upper = midpoint - 1;
            }
        }
        
        return index;
    }

    public static int findInRotated(int[] input, int target) {
        int result = -1;
        
        int lower = 0;
        int upper = input.length - 1;
        
        while (lower <= upper) {
            int midpoint  = (lower + upper) / 2;
            if (input[midpoint] == target) {
                result = midpoint;
                break;
            } else if (input[lower] <= input[upper]) {
                if (target < input[midpoint]) {
                    upper = midpoint - 1;
                } else {
                    lower = midpoint + 1;
                }
            } else if (target < input[midpoint]) {
                upper = midpoint - 1;
            } else if (target <= input[upper] ) {
                lower = midpoint + 1;
            } else {
                upper = midpoint - 1;
            }
        }
        
        return result;
    }

    public static void mergeArrays(int[] largeArray, int[] smallArray) {
        int endPtr = largeArray.length - 1;
        int largePtr = endPtr - smallArray.length;
        int smallPtr = smallArray.length - 1;
        
        while (smallPtr != -1) {
            int selectedValue = 0;
            if (largeArray[largePtr] > smallArray[smallPtr]) {
                selectedValue = largeArray[largePtr];
                largePtr -= 1;
            } else {
                selectedValue = smallArray[smallPtr];
                smallPtr -= 1;
            }
            largeArray[endPtr] = selectedValue;
            endPtr -= 1;
        }
    }

    public static String[] sortAnagrams(String[] input) {
        Function<String, String> sortChars = (String source) -> {
            char[] o1array = source.toCharArray();
            Arrays.sort(o1array);
            return new String(o1array);
        };
        
        Comparator<String> comp = (String o1, String o2) -> { 
            return sortChars.apply(o1).compareTo(sortChars.apply(o2));
        }; 
        
        Arrays.sort(input, comp);
        
        return input;
    }

    public static int findStringInSparseArray(String[] source, String string) {
        int result = -1;
        
        int lower = 0;
        int upper = source.length - 1;
        
        while (lower <= upper) {
            int midpoint = (lower + upper) / 2;
            while(source[midpoint].equals("") && midpoint < upper) {
                midpoint++;
            }
            
            if (source[midpoint].compareTo("") == 0) break;
            
            int compare = source[midpoint].compareTo(string);
            if (compare == 0) {
                result = midpoint;
                break;
            }
            
            if (compare > 0) upper = midpoint - 1;
            else lower = midpoint + 1;
        }
        
        return result;
    }
    
    
    public static int findLongestNonDecreasingSequenceLength(int[] input) {
        int result = 0;
        
        int[] lengths = new int[input.length];
        
        for (int i = 0; i < lengths.length; i++) {
            lengths[i] = 1;
        }
        
        
        for (int i = 1; i < input.length; i++) {
            if (input[i-1] < input[i]) {
                lengths[i] = lengths[i-1] + 1;
                if (result < lengths[i]) {
                    result = lengths[i];
                }
            }
        }
        
        return result;
    }

}
