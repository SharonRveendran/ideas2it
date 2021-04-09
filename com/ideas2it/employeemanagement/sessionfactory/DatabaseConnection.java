package com.ideas2it.employeemanagement.sessionfactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * class for providing database connectivity
 * @author Sharon v
 * @created 21/03/2021
 */
public class DatabaseConnection {
    private static DatabaseConnection databaseConnection = null;
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
}

