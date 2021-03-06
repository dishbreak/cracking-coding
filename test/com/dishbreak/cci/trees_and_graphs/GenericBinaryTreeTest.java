package com.dishbreak.cci.trees_and_graphs;

import static org.junit.Assert.*;

import java.util.*;

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
    
    @Test
    public void testDeleteSubtreeEmpty() {
        tree = new GenericBinaryTree<Integer>();
        
        tree.deleteSubtreeAt(t -> { return t == 15; });
        
        assertTrue("Empty tree should be empty after delete operation.", tree.isEmpty());
    }
    
    @Test
    public void testDeleteNonExistentSubtree() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        tree.deleteSubtreeAt(t -> { return t == 16; });
        
        Object[] actualValues = tree.listAllValues().toArray();
        
        assertArrayEquals("Tree should be unchanged if no node matches delete predicate.", treeValues, actualValues);
    }
    
    @Test
    public void testDeleteActualSubtree() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        tree.deleteSubtreeAt(t -> { return t == 6; });
        
        Integer[] expectedValues = {
                1, 2, 3, 4, 5,
                7, 8, 9, 10,
                11, 14, 15
        };
        
        assertArrayEquals("Tree should no longer have subtree containing 6, 12, and 13", 
                expectedValues, tree.listAllValues().toArray());
    }
    
    @Test 
    public void testEmptyTreeBalanced() {
        
        tree = new GenericBinaryTree<>();
        
        assertTrue("Empty tree should be balanced.", tree.isBalanced());
    }
    
    @Test 
    public void testFullTreeBalanced() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        assertTrue("Full tree should be balanced.", tree.isBalanced());

    }
    
    @Test
    public void testTreeUnbalanced() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        tree.deleteSubtreeAt(t -> { return t == 6; });
        
        assertFalse("Tree with missing subtree is unbalanced.", tree.isBalanced());
        
    }
    
    @Test
    public void testGetValuesAtEachLevel() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        Integer[][] expectedArray = {
                { 1 },
                { 2, 3 },
                { 4, 5, 6, 7 },
                { 8, 9, 10, 11, 12, 13, 14, 15 }
        };
        
        List<List<Node<Integer>>> expectedList = new ArrayList<>(4);
        for (Integer[] level : expectedArray) {
            List<Node<Integer>> list = new LinkedList<>();
            for (Integer value : level) {
                list.add(new Node<>(value));
            }
            expectedList.add(list);
        }
        
        assertEquals(expectedList, tree.getValuesAtEachLevel());
    }
    
    @Test
    public void testGetNextSuccessor() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        List<List<Node<Integer>>> nodes = tree.getValuesAtEachLevel();
        /*
         *               1
         *             /   \
         *            /     \
         *           /       \
         *          /         \
         *         /           \
         *        /             \
         *        2              3
         *      /  \            /  \
         *     /    \          /    \
         *    /      \        /      \
         *    4       5       6       7
         *  /  \    /  \    /  \    /  \
         * 08  09  10  11  12  13  14  15  
         */
        Node<Integer> leftChildLeaf = nodes.get(3).get(0); // Node '8'  
        assertEquals(nodes.get(2).get(0), tree.getNextSuccessor(leftChildLeaf)); // Node '4'
        
        Node<Integer> rightChildLeaf = nodes.get(3).get(3); // Node '11'
        assertEquals(nodes.get(0).get(0), tree.getNextSuccessor(rightChildLeaf)); // Node '1'
        
        Node<Integer> lastNodeInOrder = nodes.get(3).get(7);
        assertNull(tree.getNextSuccessor(lastNodeInOrder));
    }
    
    @Test
    public void testGetCommonAncestorOneIsAncestor() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        List<List<Node<Integer>>> nodes = tree.getValuesAtEachLevel();
        
        Node<Integer> root = nodes.get(0).get(0);
        Node<Integer> leaf = nodes.get(3).get(0);
        
        assertEquals(root, tree.getClosestCommonAncestor(root, leaf));
    }
    
    @Test
    public void testGetCommonAncestorOtherIsAncestor() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        List<List<Node<Integer>>> nodes = tree.getValuesAtEachLevel();
        
        Node<Integer> root = nodes.get(0).get(0);
        Node<Integer> leaf = nodes.get(3).get(0);
        
        assertEquals(root, tree.getClosestCommonAncestor(leaf, root));
    }
    
    @Test
    public void testGetCommonAncestorNeitherIsAncestor() {
        Integer[] treeValues = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        
        List<List<Node<Integer>>> nodes = tree.getValuesAtEachLevel();
        
        Node<Integer> ancestor = nodes.get(1).get(0); // Node '2'
        Node<Integer> leafOne = nodes.get(3).get(0); // Node '8'
        Node<Integer> leafOther = nodes.get(3).get(3); //Node '3'
        
        assertEquals(ancestor, tree.getClosestCommonAncestor(leafOne, leafOther));
    }
    
    @Test
    public void testIsASubtree() {
        Integer[] treeValues = new Integer[31];
        for (int i = 0; i < treeValues.length; i++) treeValues[i] = i + 1;
        
        Integer[] subTreeValues = { 
                7,
                14, 15,
                28, 29, 30, 31
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        GenericBinaryTree<Integer> otherTree = new GenericBinaryTree<>(subTreeValues);
        
        assertTrue(tree.isSubtree(otherTree));
    }
    
    @Test
    public void testIsNotASubtree() {
        Integer[] treeValues = new Integer[31];
        for (int i = 0; i < treeValues.length; i++) treeValues[i] = i + 1;
        
        Integer[] subTreeValues = { 
                7,
                14, 17,
                28, 29, 30, 31
        };
        
        tree = new GenericBinaryTree<>(treeValues);
        GenericBinaryTree<Integer> otherTree = new GenericBinaryTree<>(subTreeValues);
        
        assertFalse(tree.isSubtree(otherTree));
    }

}
