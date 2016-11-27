package com.dishbreak.testlab.tree.linked;

public class LinkedTreeNode {
	
	private LinkedTreeNode left;
	private LinkedTreeNode right;
	private LinkedTreeNode parent;
	private Integer key;
	private Integer value;
	
	public LinkedTreeNode(Integer key, Integer value) {
		this.key = key;
		this.value = value;
	}
	
	public Integer key() { return this.key; }
	public Integer value() { return this.value; }
	public LinkedTreeNode left() { return this.left; }
	public LinkedTreeNode right() { return this.right; }
	public LinkedTreeNode parent() { return this.parent; }
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public void setLeft(LinkedTreeNode node) {
		this.left = node;
	}
	
	public void setRight(LinkedTreeNode node) {
		this.right = node;
	}
	
	public void setParent(LinkedTreeNode node) {
		this.parent = node;
	}
	
	public String toString() {
		return "( " + key + " => " + value + " )";
	}
	
	public void removeChild(LinkedTreeNode node) {
		if (node == left) {
			left = null;
		} else if (node == right) {
			right = null;
		}
	}
	
	public boolean isLeaf() {
		return left != null || right != null;
	}
	
	public void overwrite(LinkedTreeNode node) {
		key = node.key;
		value = node.value;
	}
	
}
