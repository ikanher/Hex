/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import java.util.stream.IntStream;

/**
 * Class for representing a Hex board.
 *
 * @author akir
 */
public class Board {
    private Node[][] board;
    private Graph graph;

    // size of our true playing board
    private int SIZE;

    // board with "ghost" edges to simplify things
    private int VIRTUAL_SIZE;

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
        SIZE = size;

        // size + 2 since we are using "ghost" borders to simplify things
        VIRTUAL_SIZE = SIZE+2;

        board = new Node[VIRTUAL_SIZE][VIRTUAL_SIZE];
        graph = new Graph();
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
    public Node getNodeAt(int x, int y) {
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
        for (int i = 0; i < VIRTUAL_SIZE; i++) {
            for (int j = 0; j < VIRTUAL_SIZE; j++) {
                Node n = new Node(i, j);
                board[i][j] = n;
            }
        }

        // initialize neighbors
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                Node from = board[i][j];

                // nodes above
                graph.addEdge(from, board[i][j-1]);
                graph.addEdge(from, board[i+1][j-1]);

                // node on the sides
                graph.addEdge(from, board[i-1][j]);
                graph.addEdge(from, board[i+1][j]);

                // nodes below
                graph.addEdge(from, board[i][j+1]);
                graph.addEdge(from, board[i-1][j+1]);
            }
        }
    }

    private void checkValidCoordinates(int x, int y) {
        if (x < 1 || x > SIZE) {
            throw new IllegalStateException("x: " + x + ", y: " + y + " is not on board.");
        }
        if (y < 1 || y > SIZE) {
            throw new IllegalStateException("x: " + x + ", y: " + y + " is not on board.");
        }
    }
}
