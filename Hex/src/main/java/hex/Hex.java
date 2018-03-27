/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex;

import hex.logic.Color;
import hex.logic.Graph;
import hex.logic.Player;

/**
 *
 * @author akir
 */
public class Hex {
    
    private static int SIZE = 5;
    
    public static void main(String[] args) {
        Graph g = new Graph(SIZE);
        g.print();
        
        Player p1 = new Player("Player 1", Color.RED);
        Player p2 = new Player("Player 2", Color.BLUE);

        g.setStone(p1, 1, 1);
        g.setStone(p2, 5, 5);

        g.print();
    }
}
