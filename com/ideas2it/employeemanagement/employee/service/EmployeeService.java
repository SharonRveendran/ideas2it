package com.ideas2it.employeemanagement.employee.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Employee;

/**
 * Class for Employee service
 * @author Sharon V
 * @created 21-03-2021
 */
public interface EmployeeService {
	
    /**
     * Method to create employee
     * @param name  Employee Name
     * @param designation  Employee Designation
     * @param salary  Employee salary
     * @param mobile  Employee Mobile number
     * @param dob  Employee Date of birth 
     * @param employeeAddresses list of employee address details 
     * @return  employee id
     */
   public void createEmployee(String name, String designation,
           double salary, long mobile, Date dob,
           List<String[]> employeeAddresses);
    
   /**
     * Method to return employee details based on employee id
     * @param id Employee id
     * @return employee object if employee present else return null
     */
   public String getEmployee(int id);
        
   
    /**
     * Method to check whether the id is present in collection or not 
     * @param id Employee id
     * @return true if id present in collection else return false
     */
    public boolean isIdExist(int id);
     
   
    	
    
    /**
     * Method to validate date
     * @param date input date as string
     * @return valid date
     */
    public Date isValidDate(String date);
    	
    /**
     * Methode to validate mobile number
     * @param input user given input for mobile
     * @return valid mobile number
     */
    public long isValidMobile(String input);
        
    /** 
     * This methode will validate employee salary
     * @param input user given input for salary
     * @return valid employee salary
     */
    public double isValidSalary(String input);
        
    /**
     * This method will validate employee id
     * @param id employee id
     * @return valid employee id
     */
    public int isValidId(String id);
    
    /**
     * Method to return all employee details present in collection
     * @return list of employee details
     */
    public List<String> getAllEmployeesDetails();
  
    

  
}
