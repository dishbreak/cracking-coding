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
	
	private static interface TreeGetter {
		public LinkedTreeNode getNode(LinkedTreeNode node);
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
	
	public List<List<LinkedTreeNode>> getNodesPerLevel() {
		List<List<LinkedTreeNode>> result = new ArrayList<>();
		
		if (root != null) {
			result.add(0, new LinkedList<>());
			result.get(0).add(root);
			Integer level = 1;
			insertNodesAtLevel(result, level);
		}
		return result;
	}

	private void insertNodesAtLevel(List<List<LinkedTreeNode>> result, Integer level) {
		result.add(level, new LinkedList<LinkedTreeNode>());
		
		for (LinkedTreeNode node : result.get(level-1)) {
			if (node.left() != null) {
				result.get(level).add(node.left());
			}
			if (node.right() != null) {
				result.get(level).add(node.right());
			}
		}
		
		if (result.get(level).size() > 0) {
			insertNodesAtLevel(result, ++level);
		} else {
			result.remove(level.intValue());
		}
		
	}
		
	public LinkedTreeNode search(LinkedTreeNode startingNode, TreeTest test) {
		Queue<LinkedTreeNode> queue = new LinkedList<>();
		
		queue.add(startingNode);
		return bfs(queue, test);
	}
	
	private LinkedTreeNode bfs(Queue<LinkedTreeNode> queue, TreeTest test) {
		Queue<LinkedTreeNode> newQueue = new LinkedList<>();
		
		for (LinkedTreeNode node : queue) {
			if (test.isMatch(node)) {
				return node;
			}
			if (node.left() != null) {
				newQueue.add(node.left());
			}
			if (node.right() != null) {
				newQueue.add(node.right());
			}
		}
		
		if (newQueue.isEmpty()) {
			return null;
		} else {
			return bfs(newQueue, test);
		}
	}
	
	public LinkedTreeNode getCommonAncestor(LinkedTreeNode o1, LinkedTreeNode o2) {
		LinkedTreeNode result = null;
		
		result = search(o1, new TreeTest() {
			
			@Override
			public boolean isMatch(LinkedTreeNode node) {
				return node == o2;
			}
		});
		
		if (result == o2) {
			return o1;
		}
		
		TreeTest findOther = new TreeTest() {
			@Override
			public boolean isMatch(LinkedTreeNode node) {
				return node == o1;
			}
		};
		
		result = search(o2, findOther);
		
		if (result == o1) {
			return o2;
		}
		
		if (o2.parent() != null) {
			LinkedTreeNode startingPoint;
			startingPoint = o2.parent();
			
			TreeTest test;
			TreeGetter getter;
			if (o2.isLeftChild()) {
				test = new TreeTest() {

					@Override
					public boolean isMatch(LinkedTreeNode node) {
						return node.right() != null;
					}

				};
				getter = new TreeGetter() {

					@Override
					public LinkedTreeNode getNode(LinkedTreeNode node) {
						return search(node.right(), findOther);
					}
					
				};
			} else {
				test = new TreeTest() {

					@Override
					public boolean isMatch(LinkedTreeNode node) {
						return node.left() != null;
					}

				};
				getter = new TreeGetter() {

					@Override
					public LinkedTreeNode getNode(LinkedTreeNode node) {
						return search(node.left(), findOther);
					}
					
				};
			}
			
			while (startingPoint != null) {
				
				if (test.isMatch(startingPoint)) {
					LinkedTreeNode other = getter.getNode(startingPoint);
					if (other != null) {
						return startingPoint;
					}
				}
				
				startingPoint = startingPoint.parent();
			}
		}
		
		return result;
		
	}

}
