/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.ResultDatabase;
import hex.domain.GameResult;
import hex.domain.Player;
import hex.domain.PlayerStatistics;
import java.nio.file.Files;
import java.nio.file.Paths;
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
public class ResultDatabaseTest {

    private ResultDatabase db;

    public ResultDatabaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            String dbFile = ResultDatabase.getDatabaseFile(false);
            Files.deleteIfExists(Paths.get(dbFile));
            db = new ResultDatabase(false);
        } catch (Exception e) {
            System.err.println("Failed to get database.");
        }
    }

    @After
    public void tearDown() {
        try {
            String dbFile = ResultDatabase.getDatabaseFile(false);
            Files.deleteIfExists(Paths.get(dbFile));
        } catch (Exception e) {
            System.err.println("Failed to erase database.");
        }

    }

    @Test
    public void testPlayerDoesNotExist() {
        Player doesNotExists = new Player("DoesNotExist");
        assertFalse(db.playerExists(doesNotExists));
    }

    @Test
    public void testPlayerCreationWorks() {
        Player player = new Player("Player");
        db.createPlayer(player);
        assertTrue(db.playerExists(player));
    }

    @Test
    public void testCorrectNumberOfPlayersWon() {
        Player winner = new Player("Winner");
        Player loser1 = new Player("Loser 1");
        GameResult result1 = new GameResult(winner, loser1);
        result1.setWinner(winner);
        db.saveResult(result1);

        Player loser2 = new Player("Loser 2");
        GameResult result2 = new GameResult(winner, loser2);
        result2.setWinner(winner);
        db.saveResult(result2);

        PlayerStatistics stats = db.getPlayerStatistics(winner);
        assertEquals(2, stats.getPlayersWon().size());
    }

    @Test
    public void testCorrectNumberOfGamesWon() {
        Player winner = new Player("Winner");
        Player loser = new Player("Loser");
        GameResult result = new GameResult(winner, loser);
        result.setWinner(winner);
        db.saveResult(result);
        db.saveResult(result);

        PlayerStatistics stats = db.getPlayerStatistics(winner);
        stats.print();
        assertEquals(2, stats.winCount(loser));
    }
}
