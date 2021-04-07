package com.ideas2it.employeemanagement.sessionfactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class for providing database connectivity
 * @author Sharon v
 * @created 21/03/2021
 */
public class DatabaseConnection {private static DatabaseConnection databaseConnection = null;
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;
    private DatabaseConnection() {
    }
	
    /**
     * Method to return databaseConnection object
     * @return DatabaseConnection object
     */
    public static SessionFactory getSessionFactoryInstance() {
       if (null == sessionFactory) {
           Configuration configuration = new Configuration();

           configuration.configure("resources/hibernate/property/hibernate.cfg.xml");
		
           sessionFactory = configuration.buildSessionFactory();
       }
       return sessionFactory;
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
                    ("jdbc:mysql://localhost:3306/employeemanagementnew",
                    "root", "25562556");
        } catch(SQLException e) {
                System.out.println("Can't connect to database");
        }
        return connection;
    }

}

