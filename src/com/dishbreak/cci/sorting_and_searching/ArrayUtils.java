package com.dishbreak.cci.sorting_and_searching;


public class ArrayUtils {

    public ArrayUtils() {
        // TODO Auto-generated constructor stub
    }

    public static int findInRotated(int[] input, int target) {
        int result = -1;
        
        int rotationPoint = findRotationPoint(input, 0, input.length-1);
        result = binarySearch(input, 0, input.length -1, rotationPoint, target);
        
        return result;
    }

    private static int findRotationPoint(int[] input, int start, int end) {
        
        if (input[start] < input[end] || start == end) return start;
        else if (end - start == 1) return end;
        
        int midpoint = start + end / 2;
        if (input[start] < input[midpoint]) return findRotationPoint(input, midpoint, end);
        else return findRotationPoint(input, start, midpoint);
    }
    
    private static int binarySearch(int[] input, int start, int end, int rotationPoint, int target) {
        int startRemap = remap(start, rotationPoint, input.length);
        if (start == end && input[startRemap] != target) {
            return -1;
        }
  
        else {
            int midpoint = start + end / 2;
            int midpointRemap = remap(midpoint, rotationPoint, input.length);
            if (input[midpointRemap] < target) {
                return binarySearch(input, midpoint, end, rotationPoint, target);
            } else if (input[midpointRemap] > target) {
                return binarySearch(input, start, midpoint, rotationPoint, target);
            } else { // input[midpointRemap] == target
                return midpointRemap;
            }
        }
        
    }
    
    private static int remap(int index, int rotation, int length) {
        return (index + length - rotation) % length;
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
    
    
    

}
