package com.dishbreak.testlab.dynamic;

public class AppleOrchard {

    public AppleOrchard() {
        
    }
    
    public static int getMostApples(int[][] orchard) {
        int result = 0;
        
        
        for (int[] row : orchard) {
            if (row.length != orchard[0].length) {
                return result;
            }
        }
        
        int[][] states = new int[orchard.length][orchard[0].length];
        
        for (int i = 0; i < orchard.length; i++) {
            for (int j = 0; j < orchard[i].length; j++) {
                int fromLeft = (j == 0) ? 0 : states[i][j-1];
                int fromTop = (i == 0) ? 0 : states[i-1][j];
                states[i][j] = orchard[i][j] + Math.max(fromLeft, fromTop);
            }
        }
        
        result = states[states.length - 1][states[states.length - 1].length - 1];
        return result;
    }
    
    public static void main(String[] args) {
        
    }

}
