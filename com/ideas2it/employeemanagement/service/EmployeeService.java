package com.ideas2it.employeemanagement.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.model.Employee;

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
           List<String[]> employeeAddresses) throws SQLException;
    
    /**
     * Method to return employee details based on employee id
     * @param id Employee id
     * @return employee object if employee present else return null
     */
    public String getEmployee(int id) throws SQLException;
        
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
            throws ClassNotFoundException, SQLException;
   
    /**
     * Method to check whether the id is present in collection or not 
     * @param id Employee id
     * @return true if id present in collection else return false
     */
    public boolean isIdExist(int id) throws SQLException;
     
    /**
     * Method to delete the Employee based on employee id
     * @param id Employee id
     */
    public void deleteEmployee(int id) throws SQLException;
    	
    /**
     * Method to return all employee details present in collection
     * @return list of employee details
     */
    public List<String> getAll() throws SQLException;
        
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
     * Methode to update address
     * @param addressId employee address id
     * @param addressDetails array of address details
     * @return true for successfull address updation else false 
     */
    public boolean updateAddress(int addressId, String[] addressDetails)
            throws SQLException ;   
    
    /**
     * Methode to recover deleted employee
     * @param id employee id
     */
    public String recoverEmployee(int id) throws SQLException;

    /**
     * Method to get addressList of a employee
     * @param employeeId
     * @return list of employee address strings
     */
    public Map <Integer, String> getAddressList(int employeeId) throws SQLException;

    /**
     * Method to delete employee address
     * @param addressId
     * @return true for successfull address deletion else false
     */
    public boolean deleteAddress(int addressId)throws SQLException;

    /**
     * Methode to get deleted address list
     * @param employeeId
     * @return map of deleted address and address id
     */
    public Map <Integer, String> getDeletedAddressList(int employeeId)throws SQLException;

    /**
     * Method to recover deleted employee
     * @param addressId
     * @return true for successfull recovery
     */
    public boolean recoverAddress(int addressId) throws SQLException;

    /**
     * Method to get all deleted employees
     * @return list of  deleted employees 
     */
    public List <String> getDeletedEmployees()throws SQLException;
}
