package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase {
	

	
	
	    private static DataBase instance;
	    private Connection connection;

	    private final String URL = "jdbc:mysql://localhost:3306/restaurant_db";

	    private final String USERNAME = "root";

	    private final String PASSWORD = "";

	    private DataBase() {

	        try {

	            connection = DriverManager.getConnection(
	                    URL,
	                    USERNAME,
	                    PASSWORD
	            );

	            System.out.println("Database connected.");

	        } catch (SQLException e) {

	            e.printStackTrace();
	        }
	    }

	    public static DataBase getInstance() {

	        if(instance == null) {
	            instance = new DataBase();
	        }

	        return instance;
	    }

	    public Connection getConnection() {
	        return connection;
	    
	}
	  
}


