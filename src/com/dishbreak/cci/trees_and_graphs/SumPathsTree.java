package com.dishbreak.cci.trees_and_graphs;

import java.util.ArrayList;
import java.util.List;

public class SumPathsTree extends GenericBinaryTree<Integer> {

    public SumPathsTree() {

    }

    public SumPathsTree(Integer[] values) {
        super(values);
    }
    
    public List<String> findAllPaths(int target) {
        List<String> results = new ArrayList<>();
        
        findAllPaths(target, getRoot(), new ArrayList<>(), results);
        
        return results;
    }
    
    @SuppressWarnings("unchecked")
    private void findAllPaths(int target, Node<Integer> tail, ArrayList<Integer> buffer, List<String> results) {
        if (tail == null) return;
        
        buffer.add(tail.value());
        int end = buffer.size() - 1;
        int remaining = target;
        for (int i = end; i >= 0; i--) {
            remaining -= buffer.get(i);
            if (remaining == 0) results.add(printPath(buffer, i, end));
        }
        
        findAllPaths(target, tail.left(), (ArrayList<Integer>) buffer.clone(), results);
        findAllPaths(target, tail.right(), (ArrayList<Integer>) buffer.clone(), results);
        
    }
    
    private String printPath(List<Integer> buffer, int startIndex, int endIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            builder.append(buffer.get(i));
            if (i != endIndex) builder.append(" -> ");
        }
        return builder.toString();
    }

}
