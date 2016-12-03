package com.dishbreak.testlab.tries;

public class TrieNode {

    public TrieNode(String data) {
        this.data = data;
    }

    TrieNode[] children = new TrieNode[256];

    private boolean leafNode = true;

    private String data = null;

    public boolean isLeaf() {
        return leafNode;
    }

    public void setLeaf(boolean isLeaf) {
        this.leafNode = isLeaf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Character popData() {
        Character poppedChar = data.substring(0, 1).toCharArray()[0];
        data = data.substring(1);
        return poppedChar;
    }

    public boolean hasChild(Character key) {
        return children[key] != null;
    }

    public TrieNode child(Character key) {
        return children[key];
    }

    public void insertChild(TrieNode child) {
        if (leafNode) {
            children[data.substring(0, 1).toCharArray()[0]] = new TrieNode(data.substring(1));
            leafNode = false;
            data = null;
        }

        Character key = child.popData();
        if (children[key] != null) {
            children[key].insertChild(child);
        } else {
            children[key] = child;
        }
    }

}
