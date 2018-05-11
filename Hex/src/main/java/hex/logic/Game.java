/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import hex.database.ResultDatabase;
import hex.domain.Board;
import hex.domain.GameResult;
import hex.domain.HexColor;
import hex.domain.Player;
import hex.domain.PlayerStatistics;

/**
 * Class presenting a single game.
 *
 * Takes care of game setup, handling turns etc.
 *
 * @author akir
 */
public class Game {

    private GameLogic logic;
    private Player red;
    private Player blue;
    private Player currentPlayer;
    private Player winner;
    private ResultDatabase db;

    /**
     * Create a new game for players "Red" and "Blue"
     *
     * @param size the board size
     */
    public Game(int size) {
        Board board = new Board(size);
        logic = new GameLogic(board);
        blue = new Player("Blue", HexColor.BLUE);
        red = new Player("Red", HexColor.RED);
        currentPlayer = blue;

        // open database in production mode
        db = new ResultDatabase(true);
    }

    /**
     * Create a new game for players bluePlayerName, redPlayerName.
     *
     * @param size the board size
     * @param bluePlayerName Blue player's name
     * @param redPlayerName Red player's name
     */
    public Game(int size, String bluePlayerName, String redPlayerName) {
        Board board = new Board(size);
        logic = new GameLogic(board);
        blue = new Player(bluePlayerName, HexColor.BLUE);
        red = new Player(redPlayerName, HexColor.RED);
        currentPlayer = blue;

        // open database in production mode
        db = new ResultDatabase(true);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * For current player plays at board position x, y.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if play was success (board position was free)
     */
    public boolean playAt(int x, int y) {
        if (!logic.isFree(x, y)) {
            return false;
        }
        logic.playAt(currentPlayer, x, y);
        return true;
    }

    /**
     * Returns a game result object representing current game state.
     *
     * @return GameResult object
     */
    public GameResult gameResult() {
        GameResult result = new GameResult(red, blue);
        if (logic.checkWin(currentPlayer)) {
            winner = currentPlayer;
            result.setWinner(currentPlayer);
            db.saveResult(result);
        }
        return result;
    }

    public Board getBoard() {
        return logic.getBoard();
    }

    /**
     * Switches playing turns.
     *
     */
    public void switchTurns() {
        if (currentPlayer.getColor() == HexColor.BLUE) {
            currentPlayer = red;
        } else {
            currentPlayer = blue;
        }
    }

    public PlayerStatistics getWinnerStatistics() {
        return db.getPlayerStatistics(winner);
    }
}
