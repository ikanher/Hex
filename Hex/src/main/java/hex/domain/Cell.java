/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

/**
 * Class for a Hex cell
 *
 * @author akir
 */
public class Cell {

    private HexColor color = HexColor.WHITE;
    private int x;
    private int y;
    private int id;

    /**
     * Create a new Node at position x, y
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param id numeric identifier for this Cell
     */
    public Cell(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setColor(HexColor color) {
        this.color = color;
    }

    public HexColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Cell(" + id + ", at: " + x + "," + y + ", color: " + color + ")";
    }
}
