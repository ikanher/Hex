/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import hex.domain.Graph;
import hex.domain.HexColor;
import hex.domain.Cell;
import hex.domain.Player;

/**
 * Class for representing a Hex board.
 *
 * @author akir
 */
public class Board {
    private Cell[][] board;
    private Graph graph;

    // size of our true playing board
    private int size;

    // board with "ghost" edges to simplify things
    private int virtualSize;

    /**
     * Creates a new Hex board object.
     *
     * Initializes a new int[][] array for representing the board.
     *
     * Also creates a new @see Graph that matches the board and knows
     * about the Hex cell neighbors etc.
     *
     * @param size board size
     */
    public Board(int size) {
        this.size = size;

        // size + 2 since we are using "ghost" borders to simplify things
        virtualSize = this.size + 2;

        board = new Cell[virtualSize][virtualSize];
        graph = new Graph(virtualSize * virtualSize);
        init();
    }

    /**
     * Returns the graph that is connected to this Hex board.
     *
     * @return Graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Returns node at board position.
     *
     * @param x - x coordinate
     * @param y - y coordinate
     * @return Node at x,y
     */
    public Cell getNodeAt(int x, int y) {
        checkValidCoordinates(x, y);
        return board[x][y];
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
        checkValidCoordinates(x, y);
        board[x][y].setColor(p.getColor());
    }

    /**
     * Checks if this board position is still playable
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if board position is free, else false
     */
    public boolean isFree(int x, int y) {
        if (board[x][y].getColor() == HexColor.WHITE) {
            return true;
        }
        return false;
    }

    private void init() {
        // initialize board with nodes
        int id = 0;
        for (int i = 0; i < virtualSize; i++) {
            for (int j = 0; j < virtualSize; j++) {
                Cell n = new Cell(i, j, id++);
                board[i][j] = n;
            }
        }

        // initialize neighbors
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {

                Cell from = board[i][j];

                // nodes above
                graph.addEdge(from.getId(), board[i][j - 1].getId());
                graph.addEdge(from.getId(), board[i + 1][j - 1].getId());

                // node on the sides
                graph.addEdge(from.getId(), board[i - 1][j].getId());
                graph.addEdge(from.getId(), board[i + 1][j].getId());

                // nodes below
                graph.addEdge(from.getId(), board[i][j + 1].getId());
                graph.addEdge(from.getId(), board[i - 1][j + 1].getId());
            }
        }
    }

    private void checkValidCoordinates(int x, int y) {
        if (x < 1 || x > size) {
            throw new IllegalStateException("x: " + x + ", y: " + y + " is not on board.");
        }
        if (y < 1 || y > size) {
            throw new IllegalStateException("x: " + x + ", y: " + y + " is not on board.");
        }
    }
}
