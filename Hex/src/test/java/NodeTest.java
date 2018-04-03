/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hex.logic.Node;
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
public class NodeTest {

    public NodeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNodeCreation() {
        Node n = new Node(2, 3);
        assertEquals(2, n.getX());
        assertEquals(3, n.getY());
    }

    @Test
    public void testNodeNeighbors() {
        Node n = new Node(1, 2);
        Node n1 = new Node(3, 4);
        n.addNeighbor(n1);
        assertTrue(n.getNeighbors().contains(n1));

        Node n2 = new Node(5, 6);
        n.addNeighbor(n2);
        assertTrue(n.getNeighbors().contains(n2));
        assertTrue(n.getNeighbors().contains(n1));
    }
}
