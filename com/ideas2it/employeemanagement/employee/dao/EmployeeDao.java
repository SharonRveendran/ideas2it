package com.ideas2it.employeemanagement.employee.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public interface EmployeeDao {
    
 
    /**
     * Method to insert employee to database  
     * @param employee
     */
     public void insertEmployee(Employee employee);

   
  
    
   
    
    /**
     * Methode to check the id present in database or not
     * @param id employee id
     * @ return true if id present else false
     */
    public boolean isIdExist(int id);

    /**
     * Method to get employee from database
     * @param id employee id
     * @return employee details as a string
     */
     public Employee getEmployee(int id);

    /**
     * Methode to get all employee object as a list
     */
     public List<Employee> getAllEmployee(); 

    /**
     * Methode to delete employee based on employee id
     * @param employee employee object  
     * @return true for successfull deletion
     */
     public boolean updateEmployee(Employee employee);
    
    /**
     * Method to get all deleted employees
     * @return list of  deleted employees 
     */
    public List <Employee> getDeletedEmployees();

    /**
     * Methode to get addressList of employee
     * @param employeeId
     * @return employee address list.
     */
    public Map <Integer, Address> getAddressList(int employeeId); 

    /**
     * Methode to get id of all projects assigned to the given employee
     * @param employeeId
     * @return list of project ids
     */
    public List<Integer> getProjectIdList(int employeeId);

    /**
     * Method to get employee with project from database
     * @param employeeId employee id
     * @return employee details as a string
     */
     public Employee getEmployeeWithProject(int employeeId);

}