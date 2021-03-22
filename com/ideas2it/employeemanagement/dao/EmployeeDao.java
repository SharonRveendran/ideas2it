package com.ideas2it.employeemanagement.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public interface EmployeeDao {
      
    /**
     * Method to get employee from database
     * @param id employee id
     * @return employee details as a string
     */
     public Employee getEmployee(int id);
 
    /**
     * Method to insert employee to database  
     * @param employee
     */
     public void insertEmployee(Employee employee);

    /**
     * Methode to delete employee based on employee id
     * @ param id employee id  
     */
     public void deleteEmployee(int id);
         
   /**
     * Methode to update employee details
     * @param employee employee object
     */
    public void updateEmployee(Employee employee);
    
    /**
     * Methode to update address
     * @param employeeAddress employee address object
     * @param addressId
     * @return true for successfull employee updation else false
     */
    public void updateAddress(Address employeeAddress, int addressId);
  
    /**
     * Methode to get all employee object as a list
     */
     public List<Employee> getAllEmployee();
    
    /**
     * Methode to check the id present in database or not
     * @param id employee id
     * @ return true if id present else false
     */
    public boolean isIdExist(int id);

    /**
     * Methode to recover deleted employee
     * @param id employee id
     */
    public String recoverEmployee(int id);

    /**
     * Methode to get addressList of employee
     * @param employeeId
     * @return employee address list.
     */
    public Map <Integer, Address> getAddressList(int employeeId); 
  
    /**
     * Method to delete employee address
     * @param addressId
     */
    public void deleteAddress(int addressId);

    /**
     * Methode to get deleted address list
     * @param employeeId
     * @return map of deleted address and address id
     */
    public Map <Integer, Address> getDeletedAddressList(int employeeId);

    /**
     * Method to recover deleted employee
     * @param addressId
     */
    public void recoverAddress(int addressId);

    /**
     * Method to get all deleted employees
     * @return list of  deleted employees 
     */
    public List <Employee> getDeletedEmployees();
}