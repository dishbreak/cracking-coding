package com.dishbreak.cci.recursion;

public class FibonacciGen {
    public static int getFibNum(int n) {
        if (n < 3) {
            return 1;
        } else {
            return getFibNum(n-2) + getFibNum(n-1);
        }
    }

    public static int getFibNumIter(int n) {
        int a = 1;
        int b = 1;
        int c = -1;
        for (int i = 3; i <= n; i++ ) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
