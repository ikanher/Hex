/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

import hex.domain.Cell;

/**
 * Class for representing a Hex board.
 *
 * @author akir
 */
public class Board {
    private Cell[][] board;

    // size of our true playing board
    private int size;

    // board with "ghost" edges to simplify things
    private int virtualSize;

    /**
     * Creates a new Hex board object.
     *
     * Initializes a new Cell[][] array for representing the board.
     *
     * @param size board size
     */
    public Board(int size) {
        this.size = size;

        // size + 2 since we are using "ghost" borders to simplify things
        virtualSize = this.size + 2;

        board = new Cell[virtualSize][virtualSize];

        // initialize board
        int id = 0;
        for (int i = 0; i < virtualSize; i++) {
            for (int j = 0; j < virtualSize; j++) {
                Cell n = new Cell(i, j, id++);
                board[i][j] = n;
            }
        }        
    }

    public int getSize() {
        return size;
    }

    public int getVirtualSize() {
        return virtualSize;
    }    

    /**
     * Returns Cell object at board position.
     *
     * @param x - x coordinate
     * @param y - y coordinate
     * @return Node at x,y
     */
    public Cell getCellAt(int x, int y) {
        return board[x][y];
    }
}
