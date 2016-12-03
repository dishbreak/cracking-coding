package com.dishbreak.testlab.sorts;

import java.util.Arrays;

public class MergeSort {

    public static int[] sort(int[] input) {
        if (input.length == 1) {
            return input;
        }

        int[] left = sort(Arrays.copyOfRange(input, 0, input.length / 2));
        int[] right = sort(Arrays.copyOfRange(input, input.length / 2, input.length));

        int[] result = new int[input.length];

        int leftIdx = 0;
        int rightIdx = 0;
        for (int i = 0; i < input.length; i++) {
            int selectedValue = 0;
            if ((rightIdx == right.length) || ((leftIdx < left.length) && (left[leftIdx] < right[rightIdx]))) {
                selectedValue = left[leftIdx];
                leftIdx++;
            } else {
                selectedValue = right[rightIdx];
                rightIdx++;
            }
            result[i] = selectedValue;
        }

        return result;

    }

    public static void main(String[] args) {
        int[] input = { 3, 6, 7, 2, 1, 8, 4, 5 };
        System.out.println(Arrays.toString(input));
        int[] result = sort(input);
        System.out.println(Arrays.toString(result));
    }

}
