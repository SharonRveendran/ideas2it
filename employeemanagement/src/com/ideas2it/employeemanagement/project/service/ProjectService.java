package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.CreateFailException;
import com.ideas2it.exceptions.DeleteFailException;
import com.ideas2it.exceptions.FetchFailException;
import com.ideas2it.exceptions.NoIdException;
import com.ideas2it.exceptions.UpdateFailException;

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
     */
    public boolean createProject(String name, String managerName, Date startDate, Date endDate) throws CreateFailException;

    /**
     * Method to get project details based on project id
     * @param projectId id of project which need to display
     * @return map of project details 
     * @throws FetchFailException 
     */
    public Map<String, String> getProjectDetails(int projectId) throws FetchFailException;

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of project details as map
     * @throws FetchFailException 
     */
    public List<Map<String, String>> getAllProjectDetails(boolean isDeleted) throws FetchFailException;

    /**
     * Method to delete project based on projectId
     * @param projectId project id for deletion of project
     * @return true for successful deletion else false
     * @throws FetchFailException 
     * @throws DeleteFailException 
     */
    public boolean deleteProject(int projectId) throws FetchFailException, DeleteFailException;

    /**
     * Method to restore deleted project
     * @param projectId project id for recover project
     * @return true for successful recovery else false
     * @throws FetchFailException 
     */
    public boolean restoreProject(int projectId) throws FetchFailException;

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
     */
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate) throws FetchFailException,UpdateFailException;

    /**
     * Method to get basic details of employees 
     * @return list of employee  basic details as list
     * @throws FetchFailException 
     */
    public List<List<String>> getAllEmployeesDetails() throws FetchFailException;

    /**
     * Method to assign employees to project
     * @param employeeIdList list of employee id
     * @param projectId project id in which we need to assign employees
     * @return true for successful assignment else false
     * @throws FetchFailException 
     */
    public boolean assignEmployee(List<Integer> employeeIdList, int projectId) throws FetchFailException;

    /**
     * Method to get all projects basics details
     * @return list project details as list
     * @throws FetchFailException 
     */
    public List<List<String>> getAllProjectBasicDetails() throws FetchFailException;

    /**
     * Method to get project object
     * @param projectId
     * @return project object
     * @throws FetchFailException 
     */
    public Project getProject(int projectId) throws FetchFailException;

    /**
     * Method to get specified projects list
     * @param projectIdList
     * @return project objects list
     * @throws FetchFailException 
     */
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList) throws FetchFailException;

    /**
     * Method to remove assigned employee from project
     * @return true for successful remove else false
     * @throws FetchFailException 
     */
    public boolean removeEmployee(int projectId, int employeeId) throws FetchFailException;

    /**
     * Method to get details of employees assigned for given project
     * @param projectId project id  
     * @return list of employee details as map
     * @throws FetchFailException 
     */
    public List<Map<String, String>> getEmployeesBasicDetails(int projectId) throws FetchFailException;

    /**
     * Method to check id is present in database or not
     * @param projectId project id 
     * @return true if id exist in database else false
     * @throws NoIdException 
     */
	public void isIdExist(int projectId) throws NoIdException;
}