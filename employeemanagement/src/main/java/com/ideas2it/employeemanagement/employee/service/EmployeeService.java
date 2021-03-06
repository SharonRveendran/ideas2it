package com.ideas2it.employeemanagement.employee.service;

import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.EmployeeManagementException;

/**
 * Interface for Employee service
 * @author Sharon V
 * @created 21-03-2021
 */
public interface EmployeeService {
	
//    /**
//     * Method to create employee
//     * @param name  Employee Name
//     * @param designation  Employee Designation
//     * @param salary  Employee salary
//     * @param mobile  Employee Mobile number
//     * @param dob  Employee Date of birth 
//     * @param employeeAddresses list of employee address details 
//     * @return  employee id
//     * @throws EmployeeManagementException 
//     */
//    public void createEmployee(String name, String designation,
//           double salary, long mobile, Date dob,
//           List<String[]> employeeAddresses) throws EmployeeManagementException;
//    
//    /**
//     * Method to return employee details based on employee id
//     * @param id Employee id
//     * @return map of employee details
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public Map<String, String> getEmployee(int id) throws EmployeeManagementException;
//           
//    /**
//     * Method to check whether the id is present in collection or not 
//     * @param id Employee id
//     * @return treu if id exist else false
//     * @throws EmployeeManagementException 
//     */
//    public boolean isIdExist(int id) throws EmployeeManagementException;
//    
//    /**
//     * Method to return all employee details present in collection
//     * @return List of employees details
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public List<Map<String,String>> getAllEmployeesDetails() throws EmployeeManagementException;
//  
//    /**
//     * Method to delete the Employee based on employee id
//     * @param id Employee id
//     * @return true for successful deletion else false
//     * @throws FetchFailException 
//     * @throws DeleteFailException 
//     * @throws EmployeeManagementException 
//     */
//    public boolean deleteEmployee(int id) throws EmployeeManagementException;    
//
//    /**
//     * Method to restore deleted employee
//     * @param id employee id
//     * @return recovery status
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public boolean restoreEmployee(int id) throws EmployeeManagementException;
//
//    /**
//     * Method to update employee details
//     * @param id Employee id
//     * @param name employee name
//     * @param designation employee designation
//     * @param salary employee salary
//     * @param dob employee date of birth
//     * @param mobile employee mobile number
//     * @param addresses list of employee addresses
//     * @throws FetchFailException 
//     * @throws UpdateFailException 
//     * @throws EmployeeManagementException 
//     */
//    public void updateEmployee(int id, String name, String designation,
//            double salary, Date dob, long mobile, List<String[]> addresses) 
//            		throws EmployeeManagementException; 
//  
//    /**
//     * Method to get projects basic details
//     * @return list of Project  basic details
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public List<List<String>> getAllProjectsBasicDetails() throws EmployeeManagementException;
//
//    /**
//     * Method to get all employee basic details
//     * @return map of employee id as key and basic details as value
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public List<List<String>> getAllEmployeeBasicDetails() throws EmployeeManagementException;
//
//    /**
//     * Method to assign projects to employee
//     * @param projectIdList list of project id which need to assign
//     * @param employeeId employee id 
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public boolean assignProject(List<Integer> projectIdList, int employeeId) 
//    		throws EmployeeManagementException;
//
//    /**
//     * Method to get assigned projects details of specified employee
//     * @param employeeId employee id to get the assigned projects details
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     * @ return list of project basic details as map
//     */
//    public List<Map<String, String>> getProjectsBasicDetails(int employeeId) 
//    		throws EmployeeManagementException;
//
//    /**
//     * Method to remove assigned project of employee
//     * @param employeeId
//     * @param projectId
//     * @return true for successful removing of project
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public boolean removeProject(int employeeId, int projectId) 
//    		throws EmployeeManagementException;
//
//    /**
//     * @param id employee id
//     * @return Employee object
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public Employee getEmployeeObject(int id) throws EmployeeManagementException;
//    
//    /**
//     * Method to return specified employee list
//     * @param id Employee id
//     * @return employee object if employee present else return null
//     * @throws FetchFailException 
//     * @throws EmployeeManagementException 
//     */
//    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList) 
//    		throws EmployeeManagementException;

	/**
	 * Method to get the stack trace of exception as string
	 * @param e Exception object
	 * @return stack trace of exception as string 
	 */
	public String getStackTrace(Exception e);
	
	/**
	 * Method to insert employee to database
	 * @param employee employee model object
	 * @throws EmployeeManagementException
	 */
	public void insertEmployee(Employee employee) throws EmployeeManagementException;

	/**
	 * Method to get Employee model object
	 * @param employeeId employee id 
	 * @return employee model object
	 * @throws EmployeeManagementException 
	 */
	public Employee getEmployee(int employeeId) throws EmployeeManagementException;

	/**
	 * Method to get all employee objects
	 * @return List of employee objects
	 * @throws EmployeeManagementException
	 */
	public List<Employee> getAllEmployees() throws EmployeeManagementException;

	/**
	 * Method to check whether the id is present in collection or not
	 * @param id Employee id 
	 * @return true if id exist else false * @throws
	 * @throws EmployeeManagementException
	 */
	public boolean isIdExist(int id) throws EmployeeManagementException;
	
	/**
	 * Method to delete the Employee based on employee id
	 * @param id Employee id
	 * @return true for successful deletion else false
	 * @throws EmployeeManagementException
	 */
	public void deleteEmployee(int id) throws EmployeeManagementException;

	/**
	 * Method to restore deleted employee
	 * @param id employee id
	 * @return recovery status
	 * @throws EmployeeManagementException
	 */
	public boolean restoreEmployee(int id) throws EmployeeManagementException;

	/**
	 * Method to update employee details.
	 * @param employee employee object
	 * @throws EmployeeManagementException 
	 */
	public void updateEmployee(Employee employee) throws EmployeeManagementException;

	/**
	 * Method to get available projects for assign employees
	 * @param employeeId employee id
	 * @return list of available project objects
	 * @throws EmployeeManagementException 
	 */
	public List<Project> getAvailableProject(int employeeId) throws EmployeeManagementException;

	/**
	 * Method to assign projects to employee
	 * @param projectIdList list of project id which need to assign
	 * @param employeeId    employee id
	 * @throws FetchFailException
	 * @throws EmployeeManagementException
	 */
	public void assignProject(List<Integer> projectIdList, int employeeId)
			throws EmployeeManagementException;

	/**
	 * Method to get assigned projects
	 * @param employeeId employee id
	 * @return list of assigned projects
	 * @throws EmployeeManagementException
	 */
	public List<Project> getAssignedProjects(int employeeId) throws EmployeeManagementException;

	/**
	 * Method to unassign projects
	 * @param projectsIdList list of projects ids need to be removed
	 * @param employeeId employee id
	 * @throws EmployeeManagementException
	 */
	public void unAssignProject(List<Integer> projectsIdList, int employeeId) throws EmployeeManagementException;
}
