package com.dishbreak.testlab.tree;

public class TreeNode {

    private Integer key = null;
    private Integer value = null;

    public TreeNode(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }

    public Integer key() {
        return this.key;
    }
}
