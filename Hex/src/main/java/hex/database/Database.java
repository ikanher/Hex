/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author akir
 */
public class Database {

    protected Connection connection;

    public Database(boolean production) {
        try {
            String dbFile = getDatabaseFile(production);
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        } catch (SQLException e) {
            System.err.println("Result saving disabled! Failed to create database connection: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Initializer initializer = new Initializer();
        initializer.initialize(connection);
    }

    public static String getDatabaseFile(boolean production) throws Exception {
        initProperties();
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String dbFile = properties.getProperty("dbFile");
        String dbPath = properties.getProperty("dbPath");
        if (production) {
            return dbPath + File.separator + dbFile;
        } else {
            return dbPath + File.separator + "test-" + dbFile;
        }
    }

    private static void initProperties() throws Exception {
        Path config = Paths.get("config.properties");
        if (!Files.exists(config)) {
            InputStream is = ClassLoader.getSystemResourceAsStream("config.properties");
            Files.copy(is, config);
        }
    }
}
