package com.dishbreak.testlab.tree;

public class SearchTree {
	
	private TreeNode[] nodes;
	private Integer currentIndex; 
	
	private static final Integer SIZE = 100;
	
	
	public SearchTree(Integer size) {
		this.nodes = new TreeNode[size];
		this.currentIndex = 0;
	}
	
	public SearchTree() {
		this(SIZE);
	}
	
	public void insert(Integer key, Integer value) {
		TreeNode node = new TreeNode(key, value);
		
		bubbleDown(node, 0);
	}
	
	public Integer get(Integer key) {
		return searchForValue(key, 0);
	}
	
	private Integer searchForValue(Integer key, Integer index) {
		Integer result = null;
		if (nodes[index] != null) {
			if (nodes[index].key() == key) {
				result = nodes[index].value();
			} else if (key < nodes[index].key()) {
				result = searchForValue(key, getLeftIdx(index));
			} else {
				result = searchForValue(key, getRightIdx(index));
			}
		}
		return result;
	}
	
	private void bubbleDown(TreeNode node, Integer index) {
		if (nodes[index] == null) {
			nodes[index] = node;
		} else {
			if (node.key() == nodes[index].key()) {
				nodes[index].setValue(node.value());
			}
			else if (node.key() < nodes[index].key()) {
				bubbleDown(node, getLeftIdx(index));
			} else {
				bubbleDown(node, getRightIdx(index));
			}
		}
	}
	
	
	
	private Integer getLeftIdx(Integer rootIndex) {
		return 2 * rootIndex + 1;
	}
	
	private Integer getRightIdx(Integer rootIndex) {
		return 2 * rootIndex + 2;
	}
	
}
