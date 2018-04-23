/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import hex.domain.Board;
import hex.domain.Cell;
import hex.domain.Graph;
import hex.domain.HexColor;
import hex.domain.Player;

/**
 *
 * @author akir
 */
public class GameLogic {

    private Board board;
    private Graph graph;

    public GameLogic(Board board) {
        this.board = board;
        graph = new Graph(board.getVirtualSize() * board.getVirtualSize());
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
     * @param player the player whois playing this move
     * @param x x coordinate
     * @param y y coordinate
     */
    public void playAt(Player p, int x, int y) {

        board.getCellAt(x, y).setColor(p.getColor());

        int cellId = board.getCellAt(x, y).getId();

        for (Cell c : board.getNeighborCells(x, y)) {
            if (c.getColor() == p.getColor()) {
                graph.addEdge(cellId, c.getId(), p.getColor());
            }
        }
    }

    /**
     * Checks if player has won this game.
     *
     * @param p Player
     * @return boolean
     */
    public boolean checkWin(Player p) {
        GameEndChecker gec = new KruskalGameEndChecker(graph, p.getColor());
        boolean isWin = gec.isWin(board, p);
        return isWin;
    }
}
