package com.dishbreak.testlab.tree;

public class TreeNode {
	
	private TreeNode left = null;
	private TreeNode right = null;
	private Integer data = null;
	
	public TreeNode(Integer data) {
		this.data = data;
	}
		
	public TreeNode left() { return left; }
	
	public TreeNode right() { return right; }
	
	public Integer data() { return data; }
	
	public void setLeft(TreeNode node) {
		this.left = node;
	}
	
	public void setRight(TreeNode node) {
		this.right = node;
	}
	
	public void setData(Integer data) {
		this.data = data;
	}
}
