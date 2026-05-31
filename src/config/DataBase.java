package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private static volatile DataBase instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://zephyr.proxy.rlwy.net:27382/railway?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kdqsyvZfbdogndeAyfIXmrFkQVnNaVxR";

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