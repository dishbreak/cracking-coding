package com.dishbreak.testlab.tree.linked;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dishbreak.testlab.tree.linked.SumPathsTree.Node;
import com.dishbreak.testlab.tree.linked.SumPathsTree.Path;

public class SumPathsTreeTest {
    
    private SumPathsTree tree;
    private static final int[] values = {
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
        tree = SumPathsTree.buildFromArray(values);
    }

    @Test
    public void testBuildTree() {
                
        //this should get all nodes
        List<Node> nodes = tree.findAllMatches((Node node) -> true); 
        
        int[] actualOrder = nodes.stream()
                .mapToInt(n -> n.value())
                .toArray();
        
        int[] resultingOrder = {
                65, 2, 53, 29, 26, 3, 14, 54, 72, 4, 
                59, 85, 5, 96, 100, 36, 82, 87, 73, 25, 
                56, 52, 34, 66, 74, 49, 22, 78, 42, 51, 
                17, 71, 43, 99, 20, 55, 11, 38, 48, 41, 
                15, 27, 81, 84, 35, 95, 47, 90, 92, 69, 
                10, 91, 45, 93, 97, 7, 58, 75, 28, 63, 
                62, 89, 19, 57, 1, 64, 37, 40, 31, 77, 
                6, 21, 13, 9, 24, 12, 32, 76, 79, 33, 
                83, 70, 98, 94, 61, 60, 68, 18, 16, 46, 
                23, 67, 86, 30, 50, 80, 44, 88, 39
        };
        
        
        
        assertArrayEquals(resultingOrder, actualOrder);
        
    }
    
    @Test
    public void testFindSpecific() {
        List<Node> nodes = tree.findAllMatches(n -> n.value() > 70);
        
        int[] result = nodes.stream()
                .mapToInt(n -> n.value())
                .toArray();
        
        int[] expected = {
                72, 85, 96, 100, 82, 87, 73, 74, 78, 
                71, 99, 81, 84, 95, 90, 92, 91, 93, 
                97, 75, 89, 77, 76, 79, 83, 98, 94, 
                86, 80, 88
        };
        
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testFindAllPaths() {
        List<Path> paths = tree.findAllPaths(67);
        
        assertEquals("[[65, 2], [53, 14], [67]]", paths.toString());
    }

}
