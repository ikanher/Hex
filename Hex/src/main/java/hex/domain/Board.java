/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

import java.util.ArrayList;
import java.util.List;

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

        // + 2 since we are using "ghost" borders to simplify things
        virtualSize = this.size + 2;

        // indexing starts from 1, so let's go to virtutalSize + 1
        board = new Cell[virtualSize + 1][virtualSize + 1];

        // initialize board
        for (int i = 1; i <= virtualSize; i++) {
            for (int j = 1; j <= virtualSize; j++) {
                Cell c = new Cell(i, j, calculateCellId(i, j));
                if (i == 1 || i == virtualSize) {
                    c.setColor(HexColor.BLUE);
                }
                if (j == 1 || j == virtualSize) {
                    c.setColor(HexColor.RED);
                }
                board[i][j] = c;
            }
        }
    }

    public int calculateCellId(int x, int y) {
        return (y * virtualSize) + x - virtualSize;
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

    /**
     * Returns List of neighbor cells.
     *
     * @param x - x coordinate
     * @param y - y coordinate
     * @return List of Cell objects
     */
    public List<Cell> getNeighborCells(int x, int y) {
        List<Cell> ret = new ArrayList<>();
        ret.add(getCellAt(x, y - 1));
        ret.add(getCellAt(x + 1, y - 1));
        ret.add(getCellAt(x - 1, y));
        ret.add(getCellAt(x + 1, y));
        ret.add(getCellAt(x, y + 1));
        ret.add(getCellAt(x - 1, y + 1));
        return ret;
    }

    public List<Cell> topCells() {
        List<Cell> ret = new ArrayList<>();
        for (int i = 1; i <= virtualSize; i++) {
            ret.add(getCellAt(i, 1));
        }
        return ret;
    }

    public List<Cell> bottomCells() {
        List<Cell> ret = new ArrayList<>();
        for (int i = 1; i <= virtualSize; i++) {
            ret.add(getCellAt(i, virtualSize));
        }
        return ret;
    }

    public List<Cell> leftCells() {
        List<Cell> ret = new ArrayList<>();
        for (int i = 1; i <= virtualSize; i++) {
            ret.add(getCellAt(1, i));
        }
        return ret;
    }

    public List<Cell> rightCells() {
        List<Cell> ret = new ArrayList<>();
        for (int i = 1; i <= virtualSize; i++) {
            ret.add(getCellAt(virtualSize, i));
        }
        return ret;
    }
}
