/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

/**
 *
 * @author akir
 */
public class Player {
    private String name;
    private HexColor color;

    public Player(String name, HexColor color) {
        this.name = name;
        this.color = color;
    }

    public HexColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + color + ")";
    }
}
