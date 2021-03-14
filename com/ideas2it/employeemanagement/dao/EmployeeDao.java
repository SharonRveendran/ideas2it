package com.ideas2it.employeemanagement.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 13-03-2021
 */
public interface EmployeeDao {
      
    /**
     * Method to get employee from database
     * @param id employee id
     * @return employee details as a string
     */
     public Employee getEmployee(int id) throws SQLException;
 
    /**
     * Method to insert employee to database  
     * @param employee
     */
     public void insertEmployee(Employee employee) throws SQLException ;

    /**
     * Methode to delete employee based on employee id
     * @ param id employee id  
     */
     public void deleteEmployee(int id) throws SQLException;
         
   /**
     * Methode to update employee details
     * @param id Employee id
     * @param name employee name
     * @param designation employee designation
     * @param salary employee salary
     * @param dob employee date of birth
     * @param mobile employee mobile number
     * @param option option to specify the attribute that need to update
     */
    public void updateEmployee(int id, String name, String designation,
            double salary, Date dob, long mobile, String option)
            throws SQLException;
    
    /**
     * Methode to update address
     * @param employeeAddress employee address object
     * @param option user given option for type of address
     */
    public void updateAddress(Address employeeAddress)throws SQLException;
  
    /**
     * Methode to get all employee object as a list
     */
     public List<Employee> getAllEmployee() throws SQLException;
    
    /**
     * Methode to check the id present in database or not
     * @param id employee id
     * @ return true if id present else false
     */
    public boolean isIdExist(int id) throws SQLException;
}