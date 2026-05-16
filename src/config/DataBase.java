package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private static volatile DataBase instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Admin123!";

    private DataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("[DB] Connexion établie.");
        } catch (ClassNotFoundException e) {
            System.out.println("[DB] Driver introuvable: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[DB] Erreur connexion: " + e.getMessage());
        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            synchronized (DataBase.class) {
                if (instance == null) {
                    instance = new DataBase();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}