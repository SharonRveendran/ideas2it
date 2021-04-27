package com.ideas2it.employeemanagement.project.dao;

import java.util.List;

import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.EmployeeManagementException;

/**
 * Interface to interact with database
 * @author Sharon V
 * @created 24-03-2021
 */
public interface ProjectDao {

    /**
     * Method to insert project to database
     * @param project project object
     * @return true for successful project creation else false.
     * @throws CreateFailException 
     * @throws EmployeeManagementException 
     */
    public boolean insertProject(Project project) throws EmployeeManagementException;  
    
    /**
     * Method to get project object based on project id
     * @param projectId id of project which need to display
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
     * Method to get project object with employee object list based on project id
     * @param projectId id of project which need to display
     * @return project object
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public Project getProjectWithEmployee(int projectId) throws EmployeeManagementException;

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of project objects 
     * @throws FetchFailException 
     * @throws EmployeeManagementException 
     */
    public List<Project> getAllProject(boolean isDeleted) throws EmployeeManagementException;

     /**
     * Method to delete project based on projectId
     * @param project project object for delete project
     * @return true for successful deletion else false
     */
    public boolean updateProject(Project project);

    /**
     * Method to check the id is present in database or not
     * @param projectId project id
     * @return true if id present in database else false
     * @throws EmployeeManagementException 
     */
	public boolean isIdExist(int projectId) throws EmployeeManagementException;
}