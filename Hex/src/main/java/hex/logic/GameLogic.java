/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import hex.domain.Board;
import hex.domain.HexColor;
import hex.domain.Player;

/**
 * Contains most of the playing logic.
 *
 * Playing at a board position.
 * Checking if position is free.
 * Winning position detection.
 *
 * @author akir
 */
public class GameLogic {

    private Board board;

    /**
     * Sets up a new GameLogic object.
     *
     * @param board 
     */
    public GameLogic(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Checks if this board position is still playable
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if board position is free, else false
     */
    public boolean isFree(int x, int y) {
        if (board.getCellAt(x, y).getColor() == HexColor.WHITE) {
            return true;
        }
        return false;
    }

    /**
     * Allows a player to play a move at a board position.
     *
     * Board position will be assigned to the Player's color.
     *
     * @param p the player who is playing this move
     * @param x x coordinate
     * @param y y coordinate
     */
    public void playAt(Player p, int x, int y) {

        board.getCellAt(x, y).setColor(p.getColor());
    }

    /**
     * Checks if player has won this game.
     *
     * @param p Player
     * @return boolean
     */
    public boolean checkWin(Player p) {
        GameEndChecker gec = new UnionFindEndGameChecker();
        boolean isWin = gec.isWin(board, p);
        return isWin;
    }
}
