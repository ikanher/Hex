/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hex.domain.Board;
import hex.domain.HexColor;
import hex.domain.Player;
import hex.logic.GameLogic;
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
public class GameLogicTest {

    private GameLogic logic;
    private static final int SIZE = 3;

    public GameLogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Board b = new Board(SIZE);
        logic = new GameLogic(b);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestIsFree() {
        assertTrue(logic.isFree(2, 2));
    }

    @Test
    public void TestIsNotFree() {
        Player p = new Player("Blue", HexColor.BLUE);
        logic.playAt(p, 2, 2);
        assertFalse(logic.isFree(2, 2));
    }

    @Test
    public void testRedDidntWinOnEmptyBoard() {
        Player p = new Player("Red", HexColor.RED);
        assertFalse(logic.checkWin(p));
    }

    @Test
    public void testBlueDidntWinOnEmptyBoard() {
        Player p = new Player("Blue", HexColor.BLUE);
        assertFalse(logic.checkWin(p));
    }

    @Test
    public void testRedDidntWinWhenBlueWon() {
        Player p = new Player("Blue", HexColor.BLUE);
        logic.playAt(p, 2, 2);
        logic.playAt(p, 3, 3);
        logic.playAt(p, 4, 4);
        Player red = new Player("Red", HexColor.RED);
        assertFalse(logic.checkWin(red));
    }

    @Test
    public void testBlueDidntWinWhenRedWon() {
        Player p = new Player("Red", HexColor.RED);
        logic.playAt(p, 2, 2);
        logic.playAt(p, 3, 3);
        logic.playAt(p, 4, 4);
        Player blue = new Player("Blue", HexColor.BLUE);
        assertFalse(logic.checkWin(blue));
    }

    @Test
    public void testBlueWon1() {
        Player p = new Player("Blue", HexColor.BLUE);
        logic.playAt(p, 2, 2);
        logic.playAt(p, 3, 3);
        logic.playAt(p, 4, 4);
        assertTrue(logic.checkWin(p));
    }

    @Test
    public void testBlueWon2() {
        Player p = new Player("Blue", HexColor.BLUE);
        logic.playAt(p, 2, 3);
        logic.playAt(p, 3, 3);
        logic.playAt(p, 4, 3);
        assertTrue(logic.checkWin(p));
    }

    @Test
    public void testRedWon1() {
        Player p = new Player("Red", HexColor.RED);
        logic.playAt(p, 2, 2);
        logic.playAt(p, 3, 3);
        logic.playAt(p, 4, 4);
        assertTrue(logic.checkWin(p));
    }

    @Test
    public void testRedWon2() {
        Player p = new Player("Red", HexColor.RED);
        logic.playAt(p, 3, 2);
        logic.playAt(p, 3, 3);
        logic.playAt(p, 3, 4);
        assertTrue(logic.checkWin(p));
    }

    @Test
    public void testNobodyWon1() {
        Player red = new Player("Red", HexColor.RED);
        Player blue = new Player("Blue", HexColor.RED);
        logic.playAt(blue, 2, 2);
        logic.playAt(red, 3, 3);
        logic.playAt(blue, 4, 2);
        assertFalse(logic.checkWin(blue));
        assertFalse(logic.checkWin(red));
    }
}
