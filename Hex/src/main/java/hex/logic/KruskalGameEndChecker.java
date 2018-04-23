/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import hex.domain.Board;
import hex.domain.Cell;
import hex.domain.Edge;
import hex.domain.Graph;
import hex.domain.HexColor;
import hex.domain.Player;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author akir
 */
public class KruskalGameEndChecker implements GameEndChecker {

    private class UnionFind {

        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 1; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int a) {
            while (parent[a] != a) {
                a = parent[a];
            }
            return a;
        }

        public void union(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) {
                return;
            }

            if (size[a] > size[b]) {
                parent[b] = a;
                size[a] += size[b];
            } else {
                parent[a] = b;
                size[b] += size[a];
            }
        }

        public boolean connected(int a, int b) {
            return find(a) == find(b);
        }
    }

    private Graph g;
    private UnionFind uf;
    private Set<Edge> mst;

    public KruskalGameEndChecker(Graph g, HexColor c) {
        uf = new UnionFind(g.V());
        mst = new HashSet<>();

        for (Edge e : g.edges()) {

            // if this edge is not the color that we want, skip it
            if (e.getColor() != c) {
                continue;
            }

            int v = e.getFrom();
            int u = e.getTo();

            if (!uf.connected(u, v)) {
                mst.add(e);
                uf.union(u, v);
            }
        }
    }

    public boolean isWin(Board b, Player p) {
        if (p.getColor() == HexColor.BLUE) {
            return checkBlueWin(b);
        }
        return checkRedWin(b);
    }

    private boolean checkRedWin(Board b) {
        List<Cell> top = b.topCells();
        if (!isConnected(top)) {
            return false;
        }

        List<Cell> bottom = b.bottomCells();
        if (!isConnected(bottom)) {
            return false;
        }
        return true;
    }

    private boolean checkBlueWin(Board b) {
        List<Cell> left = b.leftCells();
        if (!isConnected(left)) {
            return false;
        }

        List<Cell> right = b.rightCells();
        if (!isConnected(right)) {
            return false;
        }
        return true;
    }

    private boolean isConnected(List<Cell> cells) {
        for (Cell c : cells) {
            if (mstContains(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean mstContains(Cell c) {
        for (Edge e : mst) {
            if (e.getFrom() == c.getId() || e.getTo() == c.getId()) {
                return true;
            }
        }
        return false;
    }
}
