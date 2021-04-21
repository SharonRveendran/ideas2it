package com.ideas2it.employeemanagement.employee.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.exceptions.CreateFailException;
import com.ideas2it.exceptions.DeleteFailException;
import com.ideas2it.exceptions.FetchFailException;
import com.ideas2it.exceptions.NoIdException;
import com.ideas2it.exceptions.UpdateFailException;

/**
 * Interface for Employee service
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
           List<String[]> employeeAddresses) throws CreateFailException;
    
    /**
     * Method to return employee details based on employee id
     * @param id Employee id
     * @return map of employee details
     * @throws FetchFailException 
     */
    public Map<String, String> getEmployee(int id) throws FetchFailException;
           
    /**
     * Method to check whether the id is present in collection or not 
     * @param id Employee id
     */
    public void isIdExist(int id) throws NoIdException;
    
    /**
     * Method to return all employee details present in collection
     * @return List of employees details
     * @throws FetchFailException 
     */
    public List<Map<String,String>> getAllEmployeesDetails() throws FetchFailException;
  
    /**
     * Method to delete the Employee based on employee id
     * @param id Employee id
     * @return true for successful deletion else false
     * @throws FetchFailException 
     * @throws DeleteFailException 
     */
    public boolean deleteEmployee(int id) throws FetchFailException, DeleteFailException;    

    /**
     * Method to restore deleted employee
     * @param id employee id
     * @return recovery status
     * @throws FetchFailException 
     */
    public boolean restoreEmployee(int id) throws FetchFailException;

    /**
     * Method to get all deleted employees
     * @return list of  deleted employees 
     */
    public List <String> getDeletedEmployees();

    /**
     * Method to update employee details
     * @param id Employee id
     * @param name employee name
     * @param designation employee designation
     * @param salary employee salary
     * @param dob employee date of birth
     * @param mobile employee mobile number
     * @param addresses list of employee addresses
     * @throws FetchFailException 
     * @throws UpdateFailException 
     */
    public void updateEmployee(int id, String name, String designation,
            double salary, Date dob, long mobile, List<String[]> addresses) throws FetchFailException, UpdateFailException; 
  
    /**
     * Method to get addressList of a employee
     * @param employeeId
     * @return list of employee address strings
     */
    public Map <Integer, String> getAddressList(int employeeId);

    /**
     * Method to get projects basic details
     * @return list of Project  basic details
     * @throws FetchFailException 
     */
    public List<List<String>> getAllProjectsBasicDetails() throws FetchFailException;

    /**
     * Method to get all employee basic details
     * @return map of employee id as key and basic details as value
     * @throws FetchFailException 
     */
    public List<List<String>> getAllEmployeeBasicDetails() throws FetchFailException;

    /**
     * Method to assign projects to employee
     * @param projectIdList list of project id which need to assign
     * @param employeeId employee id 
     * @throws FetchFailException 
     */
    public boolean assignProject(List<Integer> projectIdList, int employeeId) throws FetchFailException;

    /**
     * Method to get assigned projects details of specified employee
     * @param employeeId employee id to get the assigned projects details
     * @throws FetchFailException 
     * @ return list of project basic details as map
     */
    public List<Map<String, String>> getProjectsBasicDetails(int employeeId) throws FetchFailException;

    /**
     * Method to remove assigned project of employee
     * @param employeeId
     * @param projectId
     * @return true for successful removing of project
     * @throws FetchFailException 
     */
    public boolean removeProject(int employeeId, int projectId) throws FetchFailException;

    /**
     * @param id employee id
     * @return Employee object
     * @throws FetchFailException 
     */
    public Employee getEmployeeObject(int id) throws FetchFailException;
    
    /**
     * Method to return specified employee list
     * @param id Employee id
     * @return employee object if employee present else return null
     * @throws FetchFailException 
     */
    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList) throws FetchFailException;
}
