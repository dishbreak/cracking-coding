package com.dishbreak.testlab.sorts;

import java.util.*;

public class RadixSort {

	public static void sort(int[] input) {
		sort(input, 0, null);
	}
	
	private static void sort(int[] input, int place, Integer maxValue) {
		if (maxValue != null && (maxValue / 10^place) % 10 == 0 ) return;
		
		List<Queue<Integer>> bucketList = new ArrayList<>(10); // ha ha i am funny
		for (int i = 0; i < 10; i++) {
			bucketList.add(i, new LinkedList<>());
		}
		for (int value : input) {
			if (maxValue == null || maxValue < value) {
				maxValue = value;
			}
			int digit = (int) ((value / Math.pow(10, place)) % 10);
			
			
			bucketList.get(digit).add(value);
		}
		
		int i = 0;
		for (Queue<Integer> bucket : bucketList) {
			while(!bucket.isEmpty()) {
				input[i] = bucket.remove();
				i++;
			}
		}
		
		place++;
		sort(input, place, maxValue);
	}
	
	public static void main(String[] args) {
		int[] input = {103, 342, 234, 34, 3, 403, 441};
		System.out.println(Arrays.toString(input));
		sort(input);
		System.out.println(Arrays.toString(input));
	}
}
