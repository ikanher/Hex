/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for representing graphs required for tracking Hex game state.
 *
 * It's quite a simple undirected graph where Nodes keep track of their
 * neighbors.
 *
 * @author akir
 */
public class Graph {

    private List<Node> nodes = new ArrayList<>();

    /**
     * Add a new node (vertex) to this graph.
     *
     * @param n the Node object that should be added to the graph
     */
    public void addNode(Node n) {
        this.nodes.add(n);
    }

    /**
     * Adds a new edge from a node to another.
     *
     * Calls Node's addNeighbor methods in both ways, since this is an
     * undirected graph.
     *
     * @param from the source Node object
     * @param to the destination Node object
     */
    public void addEdge(Node from, Node to) {
        from.addNeighbor(to);
        to.addNeighbor(from);
    }
}
