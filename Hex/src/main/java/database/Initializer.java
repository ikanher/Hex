/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 * Initializes a database if necessary.
 *
 * Creates tables GameResult and Player if they do not exists in the database.
 *
 * @author akir
 */
public class Initializer {

    /**
     * Initialize database.
     *
     * Creates tables GameResult and Player if they do not exist.
     *
     * @param connection Database connection
     */
    public void initialize(Connection connection) {
        try {
            init(connection);
        } catch (SQLException sqlException) {
            System.err.println("Error while initializing database: " + sqlException.toString());
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println("Failed closing database conenction: " + e.toString());
            }
        }
    }

    private void init(Connection c) throws SQLException {
        createPlayerTable(c);
        createGameResultTable(c);
    }

    private void createPlayerTable(Connection c) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Player ("
                + "id integer PRIMARY KEY NOT NULL,"
                + "name varchar(50) NOT NULL"
                + ")";
        executeSql(c, sql);
    }

    private void createGameResultTable(Connection c) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS GameResult ("
                + "id integer PRIMARY KEY NOT NULL,"
                + "winner_id integer NOT NULL,"
                + "loser_id integer NOT NULL,"
                + "FOREIGN KEY (winner_id) REFERENCES Player (id),"
                + "FOREIGN KEY (loser_id) REFERENCES Player (id)"
                + ")";
        executeSql(c, sql);
    }

    private void executeSql(Connection c, String sql) throws SQLException {
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.execute();
        stmt.close();
    }
}
