
package com.ideas2it.employeemanagement.employee.dao.impl;

import java.sql.Connection;
import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
//import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();	
  
  

      
    /** 
     * {@inheritdoc}
     */
    @Override
     public void insertEmployee(Employee employee) {
        Connection connection = databaseConnection.getDatabaseConnection();
        SessionFactory sessionFactory = databaseConnection.getSessionFactoryInstance();
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(employee);
        t.commit();


    }

    


    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {  
            preparedStatement = connection.prepareStatement
                    ("select id from employee where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            boolean isIdExist = resultSet.next();
            return isIdExist;  
        } catch(SQLException e) {
            e.printStackTrace();
            return false; 
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

   

    

   

   
   

   

}