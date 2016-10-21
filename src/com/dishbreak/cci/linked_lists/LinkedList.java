package com.dishbreak.cci.linked_lists;

import java.util.HashMap;
import java.util.Map;

public class LinkedList {
	private Node<Integer> root;
	
	public void append(Integer data) {
		Node<Integer> insertedValue = new Node<Integer>(data);
		this.append(insertedValue);
	}
	
	public void append(Node<Integer> insertedValue) {
		if (root == null) {
			root = insertedValue;
		} else {
			Node<Integer> iter = root;
			while(iter.next() != null) {
				iter = iter.next();
			}
			iter.setNext(insertedValue);
		}
	}
	
	public void deleteNode(Integer data) {
		Node<Integer> iter = root;
		
		if (iter.getData() == data) {
			root = iter.next();
		} else {
			while (iter.next() != null && iter.next().getData() != data) {
				iter = iter.next();
			}
			if (iter.next() != null) {
				Node<Integer> deletedValue = iter.next();
				iter.setNext(deletedValue.next());
			}
		}
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(");
		
		Node<Integer> iter = root;
		
		while(iter != null) {
			stringBuilder.append(iter.getData());
			if (iter.next() != null) {
				stringBuilder.append(",");
			}
			iter = iter.next();
		}
		
		stringBuilder.append(")");
		return stringBuilder.toString();
	}
	
	public void dedupe() {
		Node<Integer> iter = root;
		Node<Integer> prev = null;
		
		Map<Integer, Boolean> hitMap = new HashMap<>();
		
		while (iter != null) {
			if (hitMap.containsKey(iter.getData())) {
				if (prev != null) {
					prev.setNext(iter.next());
				}
			} else {
				hitMap.put(iter.getData(), true);
				prev = iter;
			}
			iter = iter.next();
		}
	}
	
	// same as the other one, but it has time O(n^2) and no space complexity 
	public void dedupeN2() {
		Node<Integer> iter = root;
		
		while (iter != null) {
			Node<Integer> walker = iter.next();
			Node<Integer> prev = null;
			while (walker != null) {
				if (walker.getData() == iter.getData()) {
					if (prev != null) {
						prev.setNext(walker.next());
					}
				} else {
					prev = walker;
				}
				walker = walker.next();
			}
			iter = iter.next();
		}
	}
	
	public Node<Integer> lastElement(Integer nthPlace) {
		if (root == null) {
			return null;
		}
		Node<Integer> reverse_iter = null;
		Node<Integer> iter = root;
		
		while (iter != null) {
			Node<Integer> prev = new Node<Integer>(iter);
			prev.setNext(reverse_iter);
			reverse_iter = prev;
			iter = iter.next();
		}
		
		for (int i = 0; i < nthPlace; i++) {
			reverse_iter = reverse_iter.next();
		}
		
		return reverse_iter;
	}
	
	public Node<Integer> getRoot() {
		return root;
	}
	
	public boolean deleteNode(Node<Integer> node) {
		if (node == null || node.next() == null) {
			return false;
		}
		
		node.setData(node.next().getData());
		node.setNext(node.next().next());
		return true;
	}
	
	public void reverse() {
		reverse(root);
	}
	
	private Node<Integer> reverse(Node<Integer> iter) {
		if (iter == null) {
			return iter;
		}
		if (iter.next() == null) {
			root = iter;
			return iter;
		}
		
		Node<Integer> nextNode = iter.next();
		iter.setNext(null);
		
		Node<Integer> rest = reverse(nextNode);
		
		nextNode.setNext(iter);
		
		return rest;
	}
}

