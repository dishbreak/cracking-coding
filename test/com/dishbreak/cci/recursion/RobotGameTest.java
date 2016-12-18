package com.dishbreak.cci.recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dishbreak.cci.recursion.RobotGame.Point;

import java.util.*;

public class RobotGameTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testShortPaths() {
        List<String> expected = new ArrayList<>();
        
        expected.add("( 0, 0 ) -> ( 1, 0 ) -> ( 1, 1 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 1, 1 )");
        
        RobotGame game = new RobotGame(2, 2);
        
        assertEquals(expected, game.getAllPaths());
    }
    
    @Test
    public void testBlockedPath() {
        List<String> expected = new ArrayList<>();
        
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 0, 2 ) -> ( 1, 2 ) -> ( 2, 2 ) -> ( 3, 2 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 1, 0 ) -> ( 2, 0 ) -> ( 3, 0 ) -> ( 3, 1 ) -> ( 3, 2 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 1, 0 ) -> ( 1, 1 ) -> ( 1, 2 ) -> ( 2, 2 ) -> ( 2, 3 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 0, 2 ) -> ( 1, 2 ) -> ( 2, 2 ) -> ( 2, 3 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 1, 1 ) -> ( 1, 2 ) -> ( 2, 2 ) -> ( 2, 3 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 0, 2 ) -> ( 1, 2 ) -> ( 1, 3 ) -> ( 2, 3 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 0, 2 ) -> ( 0, 3 ) -> ( 1, 3 ) -> ( 2, 3 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 1, 0 ) -> ( 1, 1 ) -> ( 1, 2 ) -> ( 2, 2 ) -> ( 3, 2 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 1, 1 ) -> ( 1, 2 ) -> ( 1, 3 ) -> ( 2, 3 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 1, 0 ) -> ( 1, 1 ) -> ( 1, 2 ) -> ( 1, 3 ) -> ( 2, 3 ) -> ( 3, 3 )");
        expected.add("( 0, 0 ) -> ( 0, 1 ) -> ( 1, 1 ) -> ( 1, 2 ) -> ( 2, 2 ) -> ( 3, 2 ) -> ( 3, 3 )");
        
        Set<String> expectedSet = new HashSet<>(expected);
        
        List<Point> noGo = new ArrayList<>();
        noGo.add(new Point(2, 1));
        
        RobotGame game = new RobotGame(4, 4, noGo);
        
        assertEquals(expectedSet, new HashSet<String>(game.getAllPaths()));


    }
    
    @Test
    public void testNoPath() {
        List<Point> noGo = new ArrayList<>();
        noGo.add(new Point(1,0));
        noGo.add(new Point(0,1));
        
        RobotGame game = new RobotGame(2, 2, noGo);
        List<String> paths = game.getAllPaths();
        
        assertEquals(0, paths.size());
    }

}
