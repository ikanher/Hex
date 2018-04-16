/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hex.logic.Board;
import hex.logic.Node;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author akir
 */
public class BoardTest {

    private Board board;
    private static final int SIZE = 5;

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        board = new Board(SIZE);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCornerNeighbors() {
        Node n = board.getNodeAt(1, 1);   
        List<Node> neighbors = n.getNeighbors();
        assertEquals(6, neighbors.size());

        // these are the real neighbors
        assertTrue(neighbors.contains(board.getNodeAt(2, 1)));
        assertTrue(neighbors.contains(board.getNodeAt(1, 2)));
    }

    @Test
    public void testMiddleNeighbors() {
        Node n = board.getNodeAt(3, 3);
        List<Node> neighbors = n.getNeighbors();
        assertEquals(6, neighbors.size());

        // all surrounding neighbors should be returned

        // above neighbors
        assertTrue(neighbors.contains(board.getNodeAt(3, 2)));
        assertTrue(neighbors.contains(board.getNodeAt(4, 2)));

        // side neighbors
        assertTrue(neighbors.contains(board.getNodeAt(2, 3)));
        assertTrue(neighbors.contains(board.getNodeAt(4, 3)));

        // bottom neighbors
        assertTrue(neighbors.contains(board.getNodeAt(2, 4)));
        assertTrue(neighbors.contains(board.getNodeAt(3, 4)));
    }
}