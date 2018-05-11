/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

import hex.domain.GameResult;
import hex.domain.Player;
import hex.domain.PlayerStatistics;

/**
 * Database functions for storing and loading game results.
 *
 * @author akir
 */
public class ResultDatabase extends Database {

    private static final int MAX_WINS = 10;

    public ResultDatabase(boolean production) {
        super(production);
    }

    /**
     * Loads from database all the players that Player p has won with their
     * respective number of games lost.
     *
     * @param p Player
     * @return PlayerStatistics object
     */
    public PlayerStatistics getPlayerStatistics(Player p) {
        PlayerStatistics stats = new PlayerStatistics(p);
        // select loser_id, count(loser_id) from GameResult where winner_id = 2 group by loser_id;
        String sql = "SELECT p.name, COUNT(loser_id) as count FROM GameResult gr"
                + " JOIN PLAYER p ON gr.loser_id = p.id"
                + " WHERE winner_id = (SELECT id FROM Player WHERE name = ?)"
                + " GROUP BY loser_id"
                + " ORDER BY count DESC"
                + " LIMIT " + MAX_WINS;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getName());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String loser = rs.getString(1);
                int count = rs.getInt(2);
                stats.addWin(new Player(loser), count);
            }
        } catch (SQLException e) {
            System.err.println("Failed loading player statistics.");
            e.printStackTrace();
        }
        return stats;
    }

    /**
     * Saves game results to database.
     *
     * @param result GameResult object
     */
    public void saveResult(GameResult result) {
        Player blue = result.getRedPlayer();
        createPlayer(blue);

        Player red = result.getBluePlayer();
        createPlayer(red);

        Player winner = result.getWinner();
        updateResults(blue, red, winner);
    }

    private void updateResults(Player p1, Player p2, Player winner) {
        if (p1.equals(winner)) {
            storeResult(winner, p2);
        } else {
            storeResult(winner, p1);
        }
    }

    private void storeResult(Player winner, Player loser) {
        String sql = "INSERT INTO GameResult (winner_id, loser_id) VALUES ("
                + "(SELECT id FROM PLAYER WHERE NAME = ?),"
                + "(SELECT id FROM PLAYER WHERE NAME = ?))";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, winner.getName());
            stmt.setString(2, loser.getName());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Failed saving game result to database.");
            e.printStackTrace();
        }
    }

    /**
     * Creates a player in the database if it does not exist.
     *
     * @param p Player to be created
     */
    public void createPlayer(Player p) {
        if (playerExists(p)) {
            return;
        }
        String sql = "INSERT INTO Player (name) VALUES (?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Failed saving player to database.");
            e.printStackTrace();
        }
    }

    /**
     * Checks if player exists in database.
     *
     * @param p Player
     * @return true if player exists
     */
    public boolean playerExists(Player p) {
        String sql = "SELECT 1 FROM Player WHERE name = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getName());
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            stmt.close();
            rs.close();
            return exists;
        } catch (SQLException e) {
            System.err.println("Failed checking if player exists in database.");
            e.printStackTrace();
        }
        return false;
    }
}
