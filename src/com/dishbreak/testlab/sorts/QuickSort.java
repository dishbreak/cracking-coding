package com.dishbreak.testlab.sorts;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] input) {
        sort(input, 0, input.length - 1);
        return;
    }

    private static void sort(int[] input, int startIdx, int endIdx) {
        if (input.length == 1 || endIdx < startIdx)
            return;

        int pivot = input[(startIdx + endIdx) / 2];

        int i = startIdx;
        int j = endIdx;

        while (i <= j) {
            while (input[i] < pivot) {
                i++;
            }
            while (input[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(input, i, j);
                i++;
                j--;
            }
        }

        sort(input, startIdx, j);
        sort(input, i, endIdx);
    }

    private static void swap(int[] input, int oneIndex, int otherIndex) {
        int temp = input[oneIndex];
        input[oneIndex] = input[otherIndex];
        input[otherIndex] = temp;
    }

    public static void main(String[] args) {
        int[] input = { 3, 6, 7, 2, 1, 8, 4, 5 };
        System.out.println(Arrays.toString(input));
        sort(input);
        System.out.println(Arrays.toString(input));
    }

}
