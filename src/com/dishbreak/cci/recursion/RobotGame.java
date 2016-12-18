package com.dishbreak.cci.recursion;
import java.util.*;

/*
 * Imagine a robot sitting on the upper left hand corner of an NxN grid. 
 * The robot can only move in two directions: right and down. 
 * How many possible paths are there for the robot?
 */

public class RobotGame {
    
    private final int rows;
    private final int cols;
    private final Set<Integer> noGoX = new HashSet<>();
    private final Set<Integer> noGoY = new HashSet<>();
    
    public static class Point {
        public final int x;
        public final int y;
 
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    
    public RobotGame(int rows, int cols) {
        this(rows, cols, null);
    }
    
    public RobotGame(int rows, int cols, List<Point> noGoPoints) {
        this.rows = rows;
        this.cols = cols;
        if (noGoPoints != null) {
            for (Point point : noGoPoints) {
                noGoX.add(point.x);
                noGoY.add(point.y);
            }
        }
    }
    
    public List<String> getAllPaths() {
        ArrayList<String> paths = new ArrayList<>();
        getAllPaths(0, 0, new ArrayList<>(), paths);
        return paths;
    }
    
    @SuppressWarnings("unchecked")
    private void getAllPaths(int row, int col, ArrayList<String> buffer, ArrayList<String> paths) {
        if (row == rows || col == cols || isOccupied(row, col)) return; //off the edge of the gameboard!
        else {
            buffer.add(String.format("( %d, %d )", row, col));
            if (row == rows - 1 && col == cols - 1) {
                printPath(buffer, paths);
            } else {
                getAllPaths(row + 1, col, (ArrayList<String>) buffer.clone(), paths);
                getAllPaths(row, col + 1, (ArrayList<String>) buffer.clone(), paths);
            }       
        }
    }

    private boolean isOccupied(int row, int col) {
        // TODO Auto-generated method stub
        return noGoX.contains(row) && noGoY.contains(col);
    }
    
    private void printPath(ArrayList<String> buffer, ArrayList<String> paths) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> iter = buffer.iterator();

        while(iter.hasNext()) {
            builder.append(iter.next());
            if (iter.hasNext()) builder.append(" -> ");
        }
        paths.add(builder.toString());
    }

}