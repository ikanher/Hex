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

    /**
     * Class representing an edge in graph.
     *
     */
    private static class Edge {

        private int from;
        private int to;
        private HexColor color;

        public Edge(int from, int to, HexColor color) {
            this.from = from;
            this.to = to;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Edge(" + from + ", " + to + ", " + color + ")";
        }
    }

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
     * Adds a new edge from a vertex to another.
     *
     * @param from the source vertex id
     * @param to the destination vertex id
     */
    public void addEdge(int from, int to) {
        Edge e = new Edge(from, to, HexColor.WHITE);
        adjacencyList[from].add(e);
        adjacencyList[to].add(e);
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
