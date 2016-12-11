package com.dishbreak.cci.trees_and_graphs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class GenericBinaryTreeTest {

    private GenericBinaryTree<Integer> tree;
    private static final Integer[] values = {
            71,   57,   36,   43,    1,   54,   99,   64,   82,   20,
            37,   29,   55,   40,   87,   11,   31,   72,   38,   77,
            73,    6,   48,   21,    2,   41,   13,   25,   15,    9,
            4,   27,   24,   56,   81,   12,   26,   84,   32,   52,
            35,   76,   59,   95,   79,   34,   33,   47,   83,   65,
            90,   70,   66,   92,   98,   85,   69,   94,   74,   10,
            61,    3,   91,   60,   49,   45,   68,    5,   93,   18,
            22,   16,   97,   46,   53,    7,   23,   78,   58,   67,
            96,   75,   86,   42,   30,   28,   50,   14,   63,   80,
            51,   62,   44,  100,   89,   88,   17,   39,   19,    8
    };

    @Before
    public void setUp() throws Exception {
        tree = new GenericBinaryTree<>(values);
    }

    @Test
    public void testConstructor() {
        List<Integer> result = tree.listAllValues();

        Integer[] expectedValues = {
                71,   57,   36,   43,    1,   54,   99,   64,   82,   20,
                37,   29,   55,   40,   87,   11,   31,   72,   38,   77,
                73,    6,   48,   21,    2,   41,   13,   25,   15,    9,
                4,   27,   24,   56,   81,   12,   26,   84,   32,   52,
                35,   76,   59,   95,   79,   34,   33,   47,   83,   65,
                90,   70,   66,   92,   98,   85,   69,   94,   74,   10,
                61,    3,   91,   60,   49,   45,   68,    5,   93,   18,
                22,   16,   97,   46,   53,    7,   23,   78,   58,   67,
                96,   75,   86,   42,   30,   28,   50,   14,   63,   80,
                51,   62,   44,  100,   89,   88,   17,   39,   19,    8
        };

        Object[] actualValues = result.toArray();

        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    public void testFindAllMatches() {
        List<Integer> result = tree.findAllMatches(v -> { return v % 2 == 0; });

        Integer[] expectedValues = {
                36, 54, 64, 82, 20, 
                40, 72, 38, 6, 48, 
                2, 4, 24, 56, 12, 
                26, 84, 32, 52, 76, 
                34, 90, 70, 66, 92, 
                98, 94, 74, 10, 60, 
                68, 18, 22, 16, 46, 
                78, 58, 96, 86, 42, 
                30, 28, 50, 14, 80, 
                62, 44, 100, 88, 8
        };

        Object[] actualValues = result.toArray();

        assertArrayEquals(expectedValues, actualValues);
    }

}
