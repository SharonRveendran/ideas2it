package com.ideas2it.employeemanagement.sessionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class for providing database connectivity
 * @author Sharon v
 * @created 21/03/2021
 */
public class DatabaseConnection {
    private static DatabaseConnection databaseConnection = null;
    private static Connection connection = null;
    private DatabaseConnection() {
    }
	
    /**
     * Method to return databaseConnection object
     * @return DatabaseConnection object
     */
    public static DatabaseConnection getInstance() {
       return null == databaseConnection ? databaseConnection 
               = new DatabaseConnection() : databaseConnection;
    }

    /**
     * Method to nprovide database connection object
     * @return database connection object
     */
    public Connection getDatabaseConnection() {
        try {            
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/employeemanagement",
                    "root", "25562556");
        } catch(SQLException e) {
                System.out.println("Can't connect to database");
        }
        return connection;
    }
}	