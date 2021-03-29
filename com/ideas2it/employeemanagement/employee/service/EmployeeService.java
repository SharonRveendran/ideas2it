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
            double salary, Date dob, long mobile, String option);
   
    /**
     * Method to check whether the id is present in collection or not 
     * @param id Employee id
     * @return true if id present in collection else return false
     */
    public boolean isIdExist(int id);
     
    /**
     * Method to delete the Employee based on employee id
     * @param id Employee id
     * @return true for successfull deletion else false
     */
    public boolean deleteEmployee(int id);
    	
    /**
     * Method to return all employee details present in collection
     * @return list of employee details
     */
    public List<String> getAll();
        
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
     */
    public void updateAddress(int addressId, String[] addressDetails);   
    
    /**
     * Methode to recover deleted employee
     * @param id employee id
     */
    public String recoverEmployee(int id);

    /**
     * Method to get addressList of a employee
     * @param employeeId
     * @return list of employee address strings
     */
    public Map <Integer, String> getAddressList(int employeeId);

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
    public Map <Integer, String> getDeletedAddressList(int employeeId);

    /**
     * Method to recover deleted employee
     * @param addressId
     */
    public void recoverAddress(int addressId);

    /**
     * Method to get all deleted employees
     * @return list of  deleted employees 
     */
    public List <String> getDeletedEmployees();
   
    /**
     * Method to get all employee objects present in database
     * @return list of employee objects
     */
    public List<Employee> getAllEmployees();
  
    /**
     * @param id employee id
     * @return Emnployee object
     */
    public Employee getEmployeeObject(int id);

    /**
     * Method to get projects basic details
     * @return map of Project id as key and project basic details as value
     */
    public Map<Integer, String> getAllProjectsBasicDetails();

    /**
     * Method to get all employee basic details
     * @return map of employee id as key and basic details as value
     */
    public Map<Integer, String> getAllEmployeeBasicDetails();

    /**
     * Method to assign projects to employee
     * @param projectIdList list of project ids which need to assign
     * @param employeeId employee id 
     */
    public boolean assignProject(List<Integer> projectIdList, int employeeId);

    /**
     * Method to get assigned projects details of specified employee
     * @param employeeId employee id to get the assigned projects details
     * @ return list of project basic details
     */
    public List<String> getProjectsBasicDetails(int employeeId);
}
