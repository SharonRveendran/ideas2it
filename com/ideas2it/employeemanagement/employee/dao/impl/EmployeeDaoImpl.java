
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

import org.hibernate.Criteria;

import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;



import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private SessionFactory sessionFactory = DatabaseConnection.getSessionFactoryInstance();	
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
   
    /** 
     * {@inheritdoc}
     */
    @Override
     public void insertEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        return (null == employee) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        for (Address address : employee.getAddresses()){}
        session.close();
        return employee; 
    }

    /**
     * Method to get employee with project from database
     * @param employeeId employee id
     * @return employee details as a string
     */
     public Employee getEmployeeWithProject(int employeeId) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, employeeId);
        for (Project project : employee.getProjects()){}
        session.close();
        return employee; 
     }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployee() {
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<Employee>();  
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("isDeleted",false));
        employees = criteria.list();
        return employees;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
     public boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employee);
        transaction.commit();
        session.close();
        return true;
     }
   
    /**
     * {@inheritdoc} 
     */
    @Override
    public List <Employee> getDeletedEmployees() { 
        Session session = sessionFactory.openSession();
        List<Employee> employees = new ArrayList<Employee>();  
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("isDeleted",true));
        employees = criteria.list();
        return employees;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Map <Integer, Address> getAddressList(int employeeId) {
        Session session = sessionFactory.openSession();
        Map<Integer, Address> addressList = new HashMap<Integer, Address>();
        Employee employee = session.get(Employee.class, employeeId);
        for (Address address : employee.getAddresses()) {
            if (!address.getIsDeleted()) {
                addressList.put(address.getAddressId(), address);
            }
        }
        return addressList;
    }
   
    /**
     * {@inheritdoc} 
     */
    @Override
    public List<Integer> getProjectIdList(int employeeId) {
        /*Session session = sessionFactory.openSession();
        List<Integer> projectIdList = new ArrayList<Integer>();
        Query query = session.createQuery("select id from employee_project where id =" + employeeId);
        projectIdList = query.list();
        return projectIdList;*/
        /*List<Integer> projectIdList = new ArrayList<Integer>();
        Connection connection = databaseConnection.getDatabaseConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
       try {  
           preparedStatement = connection.prepareStatement
                   ("select project_id from employee_project where employee_id = ?");        
	   preparedStatement.setInt(1, employeeId);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               projectIdList.add(resultSet.getInt(1));
           }
        } catch(SQLException e) {
           e.printStackTrace(); 
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        return projectIdList;*/
        return null;
    }

}