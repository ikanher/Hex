/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import hex.domain.Board;
import hex.domain.Cell;
import hex.domain.HexColor;
import hex.domain.Player;

/**
 * Union-Find based Hex game end checker.
 *
 * Logic for checking if Blue won:
 *
 * Red and Blue both has ghost cells on the board. First adds Blue's ghost cells
 * (left and right end) to the Union-Find.
 *
 * Then goes through for all the rest of the blue player's and unions them with
 * the neighboring blue cells
 *
 * Finally checks if left and right ghost edges are in the same union. If they
 * are Blue won the game.
 *
 * Same logic for Red player, but using top and bottom ghost cells.
 *
 * @author akir
 */
public class UnionFindEndGameChecker implements GameEndChecker {

    /**
     * Basic Union-Find data structure.
     */
    private class UnionFind {

        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];

            for (int i = 1; i <= n; i++) {
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

    /**
     * Returns true if Player p won the game
     *
     * @param b playing board
     * @param p player
     * @return true if p won the game, otherwise false
     */
    public boolean isWin(Board b, Player p) {
        if (p.getColor() == HexColor.BLUE) {
            return checkBlueWin(p, b);
        }
        return checkRedWin(p, b);
    }

    private boolean checkBlueWin(Player p, Board b) {
        UnionFind uf = new UnionFind(b.getVirtualSize() * b.getVirtualSize());

        // union left and right end ghost cells
        Cell cl = b.getLeftGhostCells().get(0);
        b.getLeftGhostCells().forEach(c -> uf.union(cl.getId(), c.getId()));
        Cell cr = b.getRightGhostCells().get(0);
        b.getRightGhostCells().forEach(c -> uf.union(cr.getId(), c.getId()));

        // union player cells with their neighbors
        unionPlayerCells(uf, b, p);

        // if left end and right end belong to same union, then blue won
        if (uf.find(cl.getId()) == uf.find(cr.getId())) {
            return true;
        }

        return false;
    }

    private boolean checkRedWin(Player p, Board b) {
        UnionFind uf = new UnionFind(b.getVirtualSize() * b.getVirtualSize());

        // union top and bottom end ghost cells
        Cell ct = b.getTopGhostCells().get(0);
        b.getTopGhostCells().forEach(c -> uf.union(ct.getId(), c.getId()));
        Cell cb = b.getBottomGhostCells().get(0);
        b.getBottomGhostCells().forEach(c -> uf.union(cb.getId(), c.getId()));

        // union player cells with their neighbors
        unionPlayerCells(uf, b, p);

        // if top and bottom end belong to same union, then red won
        if (uf.find(cb.getId()) == uf.find(ct.getId())) {
            return true;
        }

        return false;
    }

    private void unionPlayerCells(UnionFind uf, Board b, Player p) {
        // for all player's cells add neighbors to union find
        for (Cell c : b.getPlayerCells(p)) {
            for (Cell neighbor : b.getNeighborCells(p, c.getX(), c.getY())) {
                uf.union(c.getId(), neighbor.getId());
            }
        }
    }
}
