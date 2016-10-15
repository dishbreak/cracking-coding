package com.dishbreak.cci.linked_lists;

public class Adder {
	public static LinkedList add(LinkedList left, LinkedList right) {
		LinkedList result = new LinkedList();
		
		Node<Integer> iterLeft = left.getRoot(); 
		Node<Integer> iterRight = right.getRoot();
		
		Integer carryover = 0;
		
		while (iterLeft != null || iterRight != null) {
			Integer leftVal = (iterLeft == null) ? 0 : iterLeft.getData();
			Integer rightVal = (iterRight == null) ? 0 : iterRight.getData();
			
			Integer addition = carryover + leftVal + rightVal;
			
			carryover = addition / 10;
			addition = addition % 10;
			
			result.append(addition);
			
			iterLeft = (iterLeft == null) ? null : iterLeft.next();
			iterRight = (iterRight == null) ? null : iterRight.next();
		}
		
		return result;
	}
}
