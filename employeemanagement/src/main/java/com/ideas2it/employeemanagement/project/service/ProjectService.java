package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.EmployeeManagementException;

/**
 * Interface for Project service
 * @author Sharon V
 * @created 24-03-2021
 */
public interface ProjectService {

    /**
     * Method to create new project
     * @param name name of project
     * @param managerName name of project manager
     * @param startDate project start date
     * @param endDate project end date
     * @return true for successful project creation else false.
     * @throws CreateFailException 
     * @throws EmployeeManagementException 
     */
    public boolean createProject(String name, String managerName, Date startDate, Date endDate) 
    		throws EmployeeManagementException;

    /**
     * Method to get project details based on project id
     * @param projectId id of project which need to display
     * @return map of project details 
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public Map<String, String> getProjectDetails(int projectId) 
    		throws EmployeeManagementException;

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of project details as map
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public List<Map<String, String>> getAllProjectDetails(boolean isDeleted) 
    		throws EmployeeManagementException;

    /**
     * Method to delete project based on projectId
     * @param projectId project id for deletion of project
     * @return true for successful deletion else false
     * @throws FetchFailException 
     * @throws DeleteFailException 
     * @throws EmployeeManagementException 
     */
    public boolean deleteProject(int projectId) throws EmployeeManagementException;

    /**
     * Method to restore deleted project
     * @param projectId project id for recover project
     * @return true for successful recovery else false
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public boolean restoreProject(int projectId) throws EmployeeManagementException;

    /**
     * Method to update project details
     * @param projectId project id for update project
     * @param name project name
     * @param managerName name of projectManager
     * @param startDate project starting date
     * @param endDate project ending date
     * @return true for successful updation else false
     * @throws FetchFailException 
     * @throws DeleteFailException 
     * @throws UpdateFailException 
     * @throws EmployeeManagementException 
     */
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate) throws EmployeeManagementException;

    /**
     * Method to get basic details of employees 
     * @return list of employee  basic details as list
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public List<List<String>> getAllEmployeesDetails() throws EmployeeManagementException;

    /**
     * Method to assign employees to project
     * @param employeeIdList list of employee id
     * @param projectId project id in which we need to assign employees
     * @return true for successful assignment else false
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public boolean assignEmployee(List<Integer> employeeIdList, int projectId) 
    		throws EmployeeManagementException;

    /**
     * Method to get all projects basics details
     * @return list project details as list
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public List<List<String>> getAllProjectBasicDetails() throws EmployeeManagementException;

    /**
     * Method to get project object
     * @param projectId
     * @return project object
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public Project getProject(int projectId) throws EmployeeManagementException;

    /**
     * Method to get specified projects list
     * @param projectIdList
     * @return project objects list
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList) 
    		throws EmployeeManagementException;

    /**
     * Method to remove assigned employee from project
     * @return true for successful remove else false
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public boolean removeEmployee(int projectId, int employeeId) 
    		throws EmployeeManagementException;

    /**
     * Method to get details of employees assigned for given project
     * @param projectId project id  
     * @return list of employee details as map
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public List<Map<String, String>> getEmployeesBasicDetails(int projectId) 
    		throws EmployeeManagementException;

    /**
     * Method to check id is present in database or not
     * @param projectId project id 
     * @return true if id exist in database else false
     * @throws NoIdException 
     */
	public boolean isIdExist(int projectId) throws EmployeeManagementException;
	
	/**
	 * Method to get the stack trace of exception as string
	 * @param e Exception object
	 * @return stack trace of exception as string 
	 */
	public String getStackTrace(Exception e);
}