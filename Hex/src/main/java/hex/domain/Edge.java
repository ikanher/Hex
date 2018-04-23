/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

/**
 * Class representing an edge in graph.
 *
 */
public class Edge {

    private int from;
    private int to;
    private HexColor color;

    public Edge(int from, int to, HexColor color) {
        this.from = from;
        this.to = to;
        this.color = color;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public HexColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Edge(" + from + ", " + to + ", " + color + ")";
    }
}
