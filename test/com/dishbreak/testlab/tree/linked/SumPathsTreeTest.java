package com.dishbreak.testlab.tree.linked;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dishbreak.testlab.tree.linked.SumPathsTree.Node;

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
        
        
        
        assertArrayEquals(values, actualOrder);
        
    }
    
    @Test
    public void testFindSpecific() {
        List<Node> nodes = tree.findAllMatches(n -> n.value() > 70);
        
        int[] result = nodes.stream()
                .mapToInt(n -> n.value())
                .toArray();
        
        List<Integer> valList = new ArrayList<>();
        
        for(int value : values) {
            valList.add(value);
        }
        
        int[] expected = valList.stream()
                .filter(t -> t > 70)
                .mapToInt(t -> t.intValue())
                .toArray();
        
        
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testFindAllPaths() {
        int[] numbers = {5, -5, 0, 1, 4, -5, 1, 3, -2, -5, -4, 3, -3, -2, 3 };
        tree = SumPathsTree.buildFromArray(numbers);
        
        List<String> actual = tree.findAllPaths(0);
        
        List<String> expected = new ArrayList<>();
        
        expected.add("5 -> -5");
        expected.add("4 -> -4");
        expected.add("5 -> -5 -> 4 -> -4");
        expected.add("0");
        expected.add("5 -> 0 -> -5");
        
        assertEquals(expected, actual);
    }

}
