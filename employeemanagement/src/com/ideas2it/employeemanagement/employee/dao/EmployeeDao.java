package com.ideas2it.employeemanagement.employee.dao;

import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.exceptions.EmployeeManagementException;

/**
 * Interface to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public interface EmployeeDao {
    
    /**
     * Method to insert employee to database  
     * @param employee
     * @throws CreateFailException 
     */
     public void insertEmployee(Employee employee) throws EmployeeManagementException;
  
    /**
     * Method to check the id present in database or not
     * @param id employee id
     * @return true if id present else false
     * @throws EmployeeManagementException 
     */
    public boolean isIdExist(int id) throws EmployeeManagementException;

    /**
     * Method to get employee from database
     * @param id employee id
     * @return employee details as a string
     * @throws FetchFailException 
     */
    public Employee getEmployee(int id) throws EmployeeManagementException;

    /**
     * Method to return specified employee list
     * @param id Employee id
     * @return employee object if employee present else return null
     * @throws FetchFailException 
     */
    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList) throws EmployeeManagementException;

    /**
     * Method to get all employee object as a list
     * @throws FetchFailException 
     */
    public List<Employee> getAllEmployee() throws EmployeeManagementException; 

    /**
     * Method to delete employee based on employee id
     * @param employee employee object  
     * @return true for successful deletion
     */
    public boolean updateEmployee(Employee employee);

    /**
     * Method to get employee with project from database
     * @param employeeId employee id
     * @return employee details as a string
     * @throws FetchFailException 
     */
    public Employee getEmployeeWithProject(int employeeId) throws EmployeeManagementException;
}