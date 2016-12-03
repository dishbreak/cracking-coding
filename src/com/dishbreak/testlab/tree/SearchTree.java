package com.dishbreak.testlab.tree;

public class SearchTree {

    private TreeNode[] nodes;

    private static final Integer SIZE = 100;

    public SearchTree(Integer size) {
        this.nodes = new TreeNode[size];
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

    private Integer searchForIndex(Integer key, Integer index) {
        Integer result = null;
        if (nodes[index] != null) {
            if (nodes[index].key() == key) {
                result = index;
            } else if (key < nodes[index].key()) {
                result = searchForIndex(key, getLeftIdx(index));
            } else {
                result = searchForIndex(key, getRightIdx(index));
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
            } else if (node.key() < nodes[index].key()) {
                bubbleDown(node, getLeftIdx(index));
            } else {
                bubbleDown(node, getRightIdx(index));
            }
        }
    }

    public void delete(Integer key) {
        Integer index = searchForIndex(key, 0);
        if (index != null)
            deleteDown(index);
    }

    private void deleteDown(Integer index) {

        Integer replacementIdx = null;

        if (hasLeftChild(index) && hasRightChild(index)) {
            replacementIdx = findMinIndex(getRightIdx(index));
        } else if (hasLeftChild(index)) {
            replacementIdx = getLeftIdx(index);
        } else if (hasRightChild(index)) {
            replacementIdx = getRightIdx(index);
        } else {
            nodes[index] = null;
        }

        if (replacementIdx != null) {
            nodes[index] = nodes[replacementIdx];
            deleteDown(replacementIdx);
        }

    }

    private Integer findMinIndex(Integer index) {
        Integer iter = index;

        while (hasLeftChild(iter)) {
            iter = getLeftIdx(iter);
        }

        return iter;

    }

    private boolean hasLeftChild(Integer index) {
        return getLeftIdx(index) < nodes.length && nodes[getLeftIdx(index)] != null;
    }

    private boolean hasRightChild(Integer index) {
        return getRightIdx(index) < nodes.length && nodes[getRightIdx(index)] != null;
    }

    private Integer getLeftIdx(Integer rootIndex) {
        return 2 * rootIndex + 1;
    }

    private Integer getRightIdx(Integer rootIndex) {
        return 2 * rootIndex + 2;
    }

}
