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
     * Methode to validate date
     * @param date user given date in string format
     * @return valid date
     */
    public Date isValidDate(String date);

    /**
     * Methode to create new project
     * @param name name of project
     * @param managerName name of project manager
     * @param startDate project start date
     * @param endDate project end date
     * @return true for succeessfull project creation else false.
     */
    public boolean createProject(String name, String managerName, Date startDate, Date endDate);

    /**
     * Methode to get project details based on project id
     * @param projectId id of project which need to display
     * @return project details as a string
     */
    public String getProjectDetails(int projectId);

    /**
     * Methode to validate id
     * @param input project id in string format which need to be validate
     * @return true if given id is valid else false
     */
    public boolean isValidId(String input);

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of project details in string format
     */
    public List<String> getAllProjectDetails(boolean isDeleted);

    /**
     * Method to delete project based on projectId
     * @param projectId project id for deletion of project
     * @return true for succeessfull deletion else false
     */
    public boolean deleteProject(int projectId);

    /**
     * Method to recover deleted project
     * @param projectId project id for recover project
     * @return true for successfull recovery else false
     */
    public boolean recoverProject(int projectId);

    /**
     * Method to update project details
     * @param projectId project id for update project
     * @param name project name
     * @param managerName name of projectManager
     * @param startDate project starting date
     * @param endDate project ending date
     * @param option indicating which project details need to update
     * @return true for successfull updation else false
     */
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate, String option);

    /**
     * Methode to get basic details of employees 
     * @return Map of employee id as key and employee's basic details as value
     */
    public Map<Integer, String> getAllEmployeesDetails();

    /**
     * Method to assign employees to project
     * @param employeeIdList list of employee ids
     * @param projectId project id in which we need to assign employees
     * @return true for successfull assignment else false
     */
    public boolean assignEmployee(List<Integer> employeeIdList, int projectId);

    /**
     * Method to get all projects basics details
     * @return map of project id as key and project details as value
     */
    public Map<Integer, String> getAllProjectBasicDetails();

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
     * @return true for successfull remove else false
     */
    public boolean removeEmployee(int projectId, int employeeId);

    /**
     * Method to get details of employees assigned for given project
     * @param projectId project id  
     * @return list of employee details
     */
    public List<String> getEmployeesBasicDetails(int projectId);
}