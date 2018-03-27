/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author akir
 */
public class Graph {

    private class Node {
        private Color color = Color.WHITE;

        @Override
        public String toString() {
            switch (this.color) {
                case RED:
                    return "R";
                case BLUE:
                    return "B";
                default:
                    return ".";
            }
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }
    
    private List<Node>[] adjacencyList;
    private static int SIZE;

    public Graph(int size) {
        SIZE = size;        
        this.init();
    }
    
    private void init() {
        // initialize graph adjacency list
        adjacencyList = new ArrayList[SIZE + 2];
        for (int i = 0; i < SIZE + 2; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        
        // size + 2, since we are adding "ghost edges" to simplify things
        for (int i = 0; i < SIZE + 2; i++) {
            for (int j = 0; j < SIZE + 2; j++) {
                adjacencyList[i].add(new Node());
            }
        }
    }

    public void setStone(Player p, int x, int y) {
        if (x < 1 || x > SIZE) {
            throw new IllegalStateException("x: " + x + ", y: " + y + " is not on board.");
        }
        if (y < 1 || y > SIZE) {
            throw new IllegalStateException("x: " + x + ", y: " + y + " is not on board.");
        }
        adjacencyList[x].get(y).setColor(p.getColor());
    }
    
    public void print() {
        System.out.println("Board: ");
        for (int i = 1; i <= SIZE; i++) {

            // print hex board like shape
            StringBuilder sb = new StringBuilder(i + ": ");
            IntStream.range(0, i).forEach(x -> sb.append(" "));
            System.out.print(sb.toString());

            for (int j = 1; j <= SIZE; j++) {
                System.out.print(adjacencyList[i].get(j));
            }
            System.out.println();
        }
    }
}
