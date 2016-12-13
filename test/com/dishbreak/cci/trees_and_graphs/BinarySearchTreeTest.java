package com.dishbreak.cci.trees_and_graphs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
    

    BinarySearchTree<String> tree;
    
    @Before
    public void setUp() throws Exception {
        tree = new BinarySearchTree<>();
        tree.add(40,"tightness");
        tree.add(20,"lajoie");
        tree.add(30,"xiphoid");
        tree.add(35,"nemoricoline");
        tree.add(25,"barmecidal");
        tree.add(27,"unrebuttable");
        tree.add(26,"legging");
        tree.add(21,"alfeo");
        tree.add(10,"alleluiatic");
        tree.add(5,"ferromagnetism");
        tree.add(15,"labially");
        tree.add(80,"protestable");
        tree.add(50,"drinkableness");
        tree.add(45,"proevolutionary");
        tree.add(60,"finicalness");
        tree.add(55,"nonpolemical");
        tree.add(90,"gen");
        tree.add(85,"interposingly");
        tree.add(82,"postpeduncular");
        tree.add(87,"alloa");
    }

    @Test
    public void testAdd() {
        
        
        assertEquals(tree.get(40), "tightness");
        assertEquals(tree.get(20), "lajoie");
        assertEquals(tree.get(30), "xiphoid");
        assertEquals(tree.get(35), "nemoricoline");
        assertEquals(tree.get(25), "barmecidal");
        assertEquals(tree.get(27), "unrebuttable");
        assertEquals(tree.get(26), "legging");
        assertEquals(tree.get(21), "alfeo");
        assertEquals(tree.get(10), "alleluiatic");
        assertEquals(tree.get(5), "ferromagnetism");
        assertEquals(tree.get(15), "labially");
        assertEquals(tree.get(80), "protestable");
        assertEquals(tree.get(50), "drinkableness");
        assertEquals(tree.get(45), "proevolutionary");
        assertEquals(tree.get(60), "finicalness");
        assertEquals(tree.get(55), "nonpolemical");
        assertEquals(tree.get(90), "gen");
        assertEquals(tree.get(85), "interposingly");
        assertEquals(tree.get(82), "postpeduncular");
        assertEquals(tree.get(87), "alloa");
    }
    
    @Test
    public void testDeleteNodeWithLeftChild() {
        tree.delete(10);
        
        assertEquals(tree.get(40), "tightness");
        assertEquals(tree.get(20), "lajoie");
        assertEquals(tree.get(30), "xiphoid");
        assertEquals(tree.get(35), "nemoricoline");
        assertEquals(tree.get(25), "barmecidal");
        assertEquals(tree.get(27), "unrebuttable");
        assertEquals(tree.get(26), "legging");
        assertEquals(tree.get(21), "alfeo");
        assertEquals(tree.get(5), "ferromagnetism");
        assertEquals(tree.get(15), "labially");
        assertEquals(tree.get(80), "protestable");
        assertEquals(tree.get(50), "drinkableness");
        assertEquals(tree.get(45), "proevolutionary");
        assertEquals(tree.get(60), "finicalness");
        assertEquals(tree.get(55), "nonpolemical");
        assertEquals(tree.get(90), "gen");
        assertEquals(tree.get(85), "interposingly");
        assertEquals(tree.get(82), "postpeduncular");
        assertEquals(tree.get(87), "alloa");
        
    }
    
    @Test
    public void testDeleteNodeWithRightChild() {
        tree.delete(50);
        
        assertEquals(tree.get(40), "tightness");
        assertEquals(tree.get(20), "lajoie");
        assertEquals(tree.get(30), "xiphoid");
        assertEquals(tree.get(35), "nemoricoline");
        assertEquals(tree.get(25), "barmecidal");
        assertEquals(tree.get(27), "unrebuttable");
        assertEquals(tree.get(26), "legging");
        assertEquals(tree.get(21), "alfeo");
        assertEquals(tree.get(10), "alleluiatic");
        assertEquals(tree.get(5), "ferromagnetism");
        assertEquals(tree.get(15), "labially");
        assertEquals(tree.get(80), "protestable");
        assertEquals(tree.get(45), "proevolutionary");
        assertEquals(tree.get(60), "finicalness");
        assertEquals(tree.get(55), "nonpolemical");
        assertEquals(tree.get(90), "gen");
        assertEquals(tree.get(85), "interposingly");
        assertEquals(tree.get(82), "postpeduncular");
        assertEquals(tree.get(87), "alloa");
    }
    
    @Test
    public void buildBalancedTree() {
        Integer[] keys = {
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15
        };
        
        String[] values = {
                "michiganian",
                "nontransforming",
                "shf",
                "moxie",
                "dun",
                "ophyte",
                "miscounselled",
                "esterifying",
                "middlemost",
                "valuta",
                "swagsman",
                "receptual",
                "unprotecting",
                "audiometer",
                "august"
        };
        
        tree = BinarySearchTree.buildBalanced(keys, values);
        
        assertFalse(tree.isEmpty());
        assertTrue(tree.isBalanced());
    }

}
