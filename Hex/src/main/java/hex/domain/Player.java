/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

import java.util.Objects;

/**
 * Simple domain class for representing a player in Hex game.
 *
 * @author akir
 */
public class Player {
    private String name;
    private HexColor color;

    public Player(String name) {
        this.name = name;
    }

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
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return name + " (" + color + ")";
    }
}
