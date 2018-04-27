/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hex.domain.HexColor;
import hex.logic.Game;
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
public class GameTest {

    private Game game;
    private static final int SIZE = 3;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        game = new Game(SIZE);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSwitchTurns1() {
        game.switchTurns();
        assertEquals(HexColor.RED, game.getCurrentPlayer().getColor());
    }

    @Test
    public void testSwitchTurns2() {
        game.switchTurns();
        game.switchTurns();
        assertEquals(HexColor.BLUE, game.getCurrentPlayer().getColor());
    }

    @Test
    public void testDoNotPlayOnReservedCell() {
        game.playAt(2, 2);
        assertFalse(game.playAt(2, 2));
    }

    @Test
    public void testBlueWinsTheGame() {
        game.playAt(2, 2);
        game.switchTurns();
        game.playAt(1, 4);
        game.switchTurns();
        game.playAt(3, 2);
        game.switchTurns();
        game.playAt(2, 4);
        game.switchTurns();
        game.playAt(4, 2);
        assertTrue(game.isWin());
    }
    @Test
    public void testRedWinsTheGame() {
        game.playAt(4, 2);
        game.switchTurns();
        game.playAt(2, 2);
        game.switchTurns();
        game.playAt(4, 3);
        game.switchTurns();
        game.playAt(2, 3);
        game.switchTurns();
        game.playAt(4, 4);
        game.switchTurns();
        game.playAt(2, 4);
        assertTrue(game.isWin());
    }
}
