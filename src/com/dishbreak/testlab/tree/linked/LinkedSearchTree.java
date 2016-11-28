package com.dishbreak.testlab.tree.linked;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class LinkedSearchTree {
	private LinkedTreeNode root = null;

	public static interface TreeTest {
		public boolean isMatch(LinkedTreeNode node);
	}

	public void insert(Integer key, Integer value) {
		LinkedTreeNode node = new LinkedTreeNode(key, value);
		if (root == null) {
			root = node;
		} else {
			siftDown(node);
		}
	}

	public Integer getValue(Integer key) {
		LinkedTreeNode iter = root;
		Integer value = null;

		while (iter != null) {
			if (key == iter.key()) {
				value = iter.value();
				break;
			} else if (key < iter.key()) {
				iter = iter.left();
			} else {
				iter = iter.right();
			}
		}

		return value;
	}

	public LinkedTreeNode getNode(Integer key) {
		LinkedTreeNode iter = root;

		while (iter != null) {
			if (key == iter.key()) {
				break;
			} else if (key < iter.key()) {
				iter = iter.left();
			} else {
				iter = iter.right();
			}
		}

		return iter;
	}

	public LinkedTreeNode findNextInOrder(Integer key) {

		LinkedTreeNode node = getNode(key);
		return findNextInOrder(node);
	}

	public LinkedTreeNode findNextInOrder(LinkedTreeNode node) {
		LinkedTreeNode result = null;

		if (node == null) return null;

		if (node.right() != null) {
			result = node.right();
			while (result.left() != null) {
				result = result.left();
			}
		} else if (node.isLeftChild()) {
			result = node.parent();
		} else if (node.isRightChild() && !node.parent().isRightChild()) {
			result = node.parent().parent();
		}

		return result;

	}


	public LinkedTreeNode search(TreeTest test) {
		LinkedTreeNode result = null;

		if (root == null) return result;

		Set<LinkedTreeNode> visited = new HashSet<>();
		Queue<LinkedTreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			LinkedTreeNode node = queue.remove();
			if (test.isMatch(node)) {
				result = node;
				break;
			}
			visited.add(node);
			if (node.right() != null) {
				queue.add(node.right());
			}
			if (node.left() != null) {
				queue.add(node.left());
			}
		}

		return result;
	}


	private void siftDown(LinkedTreeNode childNode) {
		boolean pass = true;
		LinkedTreeNode parent = root;
		while(pass) {
			if (parent.key() == childNode.key()) {
				parent.setValue(childNode.value());
			} else if (parent.key() > childNode.key()) {
				if (parent.left() == null) {
					parent.setLeft(childNode);
					childNode.setParent(parent);
					pass = false;
				} else {
					parent = parent.left();
				}
			} else {
				if (parent.right() == null) {
					parent.setRight(childNode);
					childNode.setParent(parent);
					pass = false;
				} else {
					parent = parent.right();
				}
			}

		}
	}

	public String toString() {
		List<LinkedTreeNode> list = new ArrayList<>();

		inOrderTraverse(list, root);

		return list.toString();

	}

	private void inOrderTraverse(List<LinkedTreeNode> list, LinkedTreeNode root) {
		if (root == null) return;
		inOrderTraverse(list, root.left());
		visit(root, list);
		inOrderTraverse(list, root.right());
	}

	private void visit(LinkedTreeNode node, List<LinkedTreeNode> list) {
		list.add(node);
	}

	public static LinkedSearchTree buildFromArray(int[] keys, int[] values) {
		LinkedSearchTree tree = new LinkedSearchTree();

		int length = (keys.length > values.length) ? values.length : keys.length;

		for (int i = 0; i < length; i++) {
			tree.insert(keys[i], values[i]);
		}

		return tree;
	}

	public int getShortestPath(LinkedTreeNode node, int steps) {
		if (node == null) {
			return steps;
		}

		steps++;

		int stepsLeft = getShortestPath(node.left(), steps);
		int stepsRight = getShortestPath(node.right(), steps);

		return (stepsRight > stepsLeft) ? stepsLeft : stepsRight;
	}

	public int getLongestPath(LinkedTreeNode node, int steps) {
		if (node == null) {
			return steps;
		}

		steps++;

		int stepsLeft = getLongestPath(node.left(), steps);
		int stepsRight = getLongestPath(node.right(), steps);

		return (stepsRight < stepsLeft) ? stepsLeft : stepsRight;
	}

	public int getHeight() {
		return getLongestPath(root, 0);
	}

	public boolean isBalanced() {
		return Math.abs(getShortestPath(root, 0) - getLongestPath(root, 0)) == 1;
	}

	public void delete(Integer key) {
		if (key == null) return;


		LinkedTreeNode node = search(new TreeTest() {

			@Override
			public boolean isMatch(LinkedTreeNode node) {
				// TODO Auto-generated method stub
				return key.intValue() == node.key().intValue();
			}

		});

		delete(node);

	}

	public void delete(LinkedTreeNode node) {
		if (node == null) return;

		if (node.isLeaf()) {
			node.parent().removeChild(node);
		} else {
			if (node.left() != null) {
				LinkedTreeNode maxInSubtree = node.left();
				while (maxInSubtree.right() != null) {
					maxInSubtree = maxInSubtree.right();
				}
				node.overwrite(maxInSubtree);
				delete(maxInSubtree);
			} else {
				LinkedTreeNode minInSubtree = node.right();
				while (minInSubtree.left() != null) {
					minInSubtree = minInSubtree.left();
				}
				node.overwrite(minInSubtree);
				delete(minInSubtree);
			}
		}
	}
	
	
	public LinkedSearchTree(int[] keys, int[] values) {
		LinkedTreeNode[] nodes = new LinkedTreeNode[keys.length];
		
		for (int i = 0; i < keys.length; i++) {
			nodes[i] = new LinkedTreeNode(keys[i], values[i]);
		}
		
		root = insertBalanced(nodes, 0, nodes.length -1 );
	}
	
	private static LinkedTreeNode insertBalanced(LinkedTreeNode[] nodes, int startIdx, int endIdx) {
				
		if (startIdx > endIdx) return null;
		if (startIdx == endIdx) return nodes[startIdx];
		LinkedTreeNode node; 
		
		if (endIdx - startIdx == 1) {
			node = nodes[endIdx];
			node.setLeft(nodes[startIdx]);
		} else {

			int midPoint = Math.floorDiv(endIdx+startIdx, 2);

			node = nodes[midPoint];
			node.setLeft(insertBalanced(nodes, startIdx, midPoint-1));
			node.setRight(insertBalanced(nodes, midPoint+1, endIdx));

		}
		
		return node;
	}
	
	public LinkedSearchTree() {
		
	}

}
