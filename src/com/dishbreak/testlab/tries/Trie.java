package com.dishbreak.testlab.tries;

public class Trie {
    private TrieNode[] startingPoints = new TrieNode[256]; // root node is a
                                                           // special case -- no
                                                           // children but not a
                                                           // leaf node.

    public void insert(String key) {
        TrieNode node = new TrieNode(key);
        Character startingLetter = node.popData();
        if (startingPoints[startingLetter] != null) {
            startingPoints[startingLetter].insertChild(node);
        } else {
            startingPoints[startingLetter] = node;
        }
    }

    public boolean search(String key) {
        Character startingLetter = key.toCharArray()[0];
        if (startingPoints[startingLetter] != null) {
            return search(key.substring(1), startingPoints[startingLetter]);
        }
        return false;
    }

    private boolean search(String key, TrieNode node) {
        boolean result = node.isLeaf();
        if (node.isLeaf() && node.getData().equals(key)) {
            result = true;
        } else if (node.isLeaf()) {
            result = false;
        } else {
            Character firstLetter = key.substring(0, 1).toCharArray()[0];
            if (node.hasChild(firstLetter)) {
                result = search(key.substring(1), node.child(firstLetter));
            } else {
                result = false;
            }
        }

        return result;
    }
}