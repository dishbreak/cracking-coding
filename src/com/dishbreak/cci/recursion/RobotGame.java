package com.dishbreak.cci.recursion;
import java.util.*;

/*
 * Imagine a robot sitting on the upper left hand corner of an NxN grid. 
 * The robot can only move in two directions: right and down. 
 * How many possible paths are there for the robot?
 */

public class RobotGame {
    
    public static List<String> getAllPaths(int gridSize) {
        ArrayList<String> paths = new ArrayList<>();
        getAllPaths(gridSize, 0, 0, new ArrayList<>(), paths);
        return paths;
    }
    
    @SuppressWarnings("unchecked")
    private static void getAllPaths(int gridSize, int row, int col, ArrayList<String> buffer, ArrayList<String> paths) {
        if (row == gridSize || col == gridSize) return; //off the edge of the gameboard!
        
        buffer.add(String.format("( %d, %d )", row, col));
        
        if (buffer.size() > 1) {
            StringBuilder builder = new StringBuilder();
            Iterator<String> iter = buffer.iterator();

            while(iter.hasNext()) {
                builder.append(iter.next());
                if (iter.hasNext()) builder.append(" -> ");
            }

            paths.add(builder.toString());
        }
        
        getAllPaths(gridSize, row + 1, col, (ArrayList<String>) buffer.clone(), paths);
        getAllPaths(gridSize, row, col + 1, (ArrayList<String>) buffer.clone(), paths);
    }

}