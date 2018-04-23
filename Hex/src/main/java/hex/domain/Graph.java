/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for representing an undirected graph.
 *
 * Uses adjacency lists for storing edges of color red, blue or white (empty).
 *
 * @author akir
 */
public class Graph {

    private int N;
    private List<Edge>[] adjacencyList;

    /**
     * Creates a new undirected graph.
     *
     * @param n size of the graph
     */
    public Graph(int n) {
        N = n;

        adjacencyList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    /**
     * Returns number of vertices
     *
     * @return number of vertices
     */
    public int V() {
        return N;
    }

    /**
     * Adds a new edge from a vertex to another.
     *
     * @param from the source vertex id
     * @param to the destination vertex id
     * @param c color of the edge, default: HexColor.WHITE
     */
    public void addEdge(int from, int to, HexColor c) {
        addDirectedEdge(from, to, c);
        //addDirectedEdge(to, from, c);
    }

    public void addDirectedEdge(int from, int to, HexColor c) {
        if (from == to) {
            return;
        }

        Edge edge = new Edge(from, to, c);

        boolean exists = adjacencyList[from]
                .stream()
                .anyMatch(e -> e.getFrom() == from && e.getTo() == to);

        if (exists) {
            return;
        }
        adjacencyList[from].add(edge);
    }

    /**
     * Returns all edges of this graph.
     *
     * @return List<Edge> edges
     */
    public List<Edge> edges() {
        List<Edge> ret = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            ret.addAll(adjacencyList[i]);
        }
        return ret;
    }
}
