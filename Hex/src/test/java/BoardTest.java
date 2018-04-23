/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hex.domain.Board;
import hex.domain.Cell;
import hex.domain.HexColor;
import hex.domain.Player;
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
    private static final int SIZE = 3;

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
    public void testCalculateCellId1() {
        assertEquals(7, board.calculateCellId(2, 2));
    }

    @Test
    public void testCalculateCellId2() {
        assertEquals(13, board.calculateCellId(3, 3));
    }

    @Test
    public void testCalculateCellId3() {
        assertEquals(19, board.calculateCellId(4, 4));
    }

    @Test
    public void testCalculateCellId4() {
        assertEquals(8, board.calculateCellId(3, 2));
    }

    @Test
    public void testTopCells() {
        List<Cell> cells = board.topCells();
        assertEquals(5, cells.size());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 1 && c.getY() == 1 && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 2 && c.getY() == 1 && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 3 && c.getY() == 1 && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 4 && c.getY() == 1 && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 5 && c.getY() == 1 && c.getColor() == HexColor.RED).count());
    }

    @Test
    public void testBottomCells() {
        List<Cell> cells = board.bottomCells();
        assertEquals(5, cells.size());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 1 && c.getY() == board.getVirtualSize() && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 2 && c.getY() == board.getVirtualSize() && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 3 && c.getY() == board.getVirtualSize() && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 4 && c.getY() == board.getVirtualSize() && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 5 && c.getY() == board.getVirtualSize() && c.getColor() == HexColor.RED).count());
    }

    @Test
    public void testLeftCells() {
        List<Cell> cells = board.leftCells();
        assertEquals(5, cells.size());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 1 && c.getY() == 1 && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 1 && c.getY() == 2 && c.getColor() == HexColor.BLUE).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 1 && c.getY() == 3 && c.getColor() == HexColor.BLUE).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 1 && c.getY() == 4 && c.getColor() == HexColor.BLUE).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == 1 && c.getY() == 5 && c.getColor() == HexColor.RED).count());
    }


    @Test
    public void testRightCells() {
        List<Cell> cells = board.rightCells();
        assertEquals(5, cells.size());
        assertEquals(1, cells.stream().filter(c -> c.getX() == board.getVirtualSize() && c.getY() == 1 && c.getColor() == HexColor.RED).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == board.getVirtualSize() && c.getY() == 2 && c.getColor() == HexColor.BLUE).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == board.getVirtualSize() && c.getY() == 3 && c.getColor() == HexColor.BLUE).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == board.getVirtualSize() && c.getY() == 4 && c.getColor() == HexColor.BLUE).count());
        assertEquals(1, cells.stream().filter(c -> c.getX() == board.getVirtualSize() && c.getY() == 5 && c.getColor() == HexColor.RED).count());
    }
}
