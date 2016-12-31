package com.dishbreak.cci.recursion;

public class CoinPurse {

    private static final int[] denominations = { 25, 10, 5, 1 };
    
    public static int getCoinCombos(int number) {
        Integer result = 0;
        
        if (number == 0 ) return 0;
         
        result = getSolutions(0, number, result);
        
        return result;
    }
    
    public static int findMinSum(int sum) {
        if (sum <= 0) return 0;
        
        Integer[] states = new Integer[sum + 1];
        
        states[0] = 0;
        for (int i = 1; i < states.length; i++) {
            states[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 1; i < states.length; i++) {
            for (int j = denominations.length - 1; j >= 0; j--) {
                if (denominations[j] <= i && states[i-denominations[j]] + 1 < states[i]) {
                    states[i] = states[i-denominations[j]] + 1;
                }
            }
        }
        
        return states[sum].intValue();
    }

    public static Integer getSolutions(int denomIdx, int number, Integer result) {
        if (number < 0) return result;
        if (number == 0) {
            return result + 1;
        }
        
        if (denomIdx == denominations.length) return result;
        int denomination = denominations[denomIdx];
        
        for (int i=denomIdx; i < denominations.length && denomination > number; i++) {
            denomination = denominations[i];
            denomIdx = i;
        }
        
        //getSolutions(denomIdx + 1, number, result); 
        int nextNumber = number;
        while(nextNumber > 0) {
            nextNumber -= denomination;
            result += getSolutions(denomIdx + 1, nextNumber, result);
        }
        
        return result;
    }
    
    
    
}
