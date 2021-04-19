package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.project.model.Project;

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
     */
    public boolean createProject(String name, String managerName, Date startDate, Date endDate);

    /**
     * Method to get project details based on project id
     * @param projectId id of project which need to display
     * @return map of project details 
     */
    public Map<String, String> getProjectDetails(int projectId);

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of project details as map
     */
    public List<Map<String, String>> getAllProjectDetails(boolean isDeleted);

    /**
     * Method to delete project based on projectId
     * @param projectId project id for deletion of project
     * @return true for successful deletion else false
     */
    public boolean deleteProject(int projectId);

    /**
     * Method to restore deleted project
     * @param projectId project id for recover project
     * @return true for successful recovery else false
     */
    public boolean restoreProject(int projectId);

    /**
     * Method to update project details
     * @param projectId project id for update project
     * @param name project name
     * @param managerName name of projectManager
     * @param startDate project starting date
     * @param endDate project ending date
     * @return true for successful updation else false
     */
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate);

    /**
     * Method to get basic details of employees 
     * @return list of employee  basic details as list
     */
    public List<List<String>> getAllEmployeesDetails();

    /**
     * Method to assign employees to project
     * @param employeeIdList list of employee id
     * @param projectId project id in which we need to assign employees
     * @return true for successful assignment else false
     */
    public boolean assignEmployee(List<Integer> employeeIdList, int projectId);

    /**
     * Method to get all projects basics details
     * @return list project details as list
     */
    public List<List<String>> getAllProjectBasicDetails();

    /**
     * Method to get project object
     * @param projectId
     * @return project object
     */
    public Project getProject(int projectId);

    /**
     * Method to get specified projects list
     * @param projectIdList
     * @return project objects list
     */
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList);

    /**
     * Method to remove assigned employee from project
     * @return true for successful remove else false
     */
    public boolean removeEmployee(int projectId, int employeeId);

    /**
     * Method to get details of employees assigned for given project
     * @param projectId project id  
     * @return list of employee details as map
     */
    public List<Map<String, String>> getEmployeesBasicDetails(int projectId);

    /**
     * Method to check id is present in database or not
     * @param projectId project id 
     * @return true if id exist in database else false
     */
	public boolean isIdExist(int projectId);
}