package com.ideas2it.employeemanagement.project.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.employeemanagement.project.service.ProjectService;

/**
 * Class for Project controller
 * @author Sharon V
 * @created 27-03-2021
 */
public class ProjectController {
    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * Method to validate date
     * @param date User given date string
     * @return valid date
     */
    public Date isValidDate(String date) {
    	return projectService.isValidDate(date);
    }
  
    /**
     * Methode to create new project
     * @param name name of project
     * @param managerName name of project manager
     * @param startDate project start date
     * @param endDate project end date
     * @return true for succeessfull project creation else false.
     */
    public boolean createProject(String name, String managerName, Date startDate, Date endDate) {
        return projectService.createProject(name, managerName, startDate, endDate);
    } 

    /**
     * Methode to validate id
     * @param input project id in string format which need to be validate
     * @return true if given id is valid else false
     */
    public boolean isValidId(String input) {
        return projectService.isValidId(input);
    }

    /**
     * Methode to get project details based on project id
     * @param projectId id of project which need to display
     * @return project details as a string
     */
    public String getProject(int projectId) {
        return projectService.getProject(projectId);
    }

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of employee details in string format
     */
    public List<String> getAllProject(int isDeleted) {
        return projectService.getAllProject(isDeleted);
    }

    /**
     * Method to delete project based on projectId
     * @param projectId project id for deletion of project
     * @return true for succeessfull deletion else false
     */
    public boolean deleteProject(int projectId) {
        return projectService.deleteProject(projectId);
    }

    /**
     * Method to recover deleted project
     * @param projectId project id for recover project
     * @return true for successfull recovery else false
     */
    public boolean recoverProject(int projectId) {
        return projectService.recoverProject(projectId);
    }

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
            Date startDate, Date endDate, String option) {
        return projectService.updateProject(projectId, name,
                managerName, startDate, endDate, option);
    }
    
    /**
     * Methode to get basic details of employees 
     * @return List of employee's basic details
     */
    public Map<Integer, String> getAllEmployeesDetails() {
        return projectService.getAllEmployeesDetails();
    }
   
    /**
     * Method to assign employees to project
     * @param employeeIdList list of employee ids
     * @param projectId project id in which we need to assign employees
     * @return true for successfull assignment else false
     */
    public boolean assignEmployee(List<Integer> employeeIdList, int projectId) {
        return projectService.assignEmployee(employeeIdList, projectId);
    }

    /**
     * Method to get all projects basics details
     * @return map of project id as key and project details as value
     */
    public Map<Integer, String> getAllProjectBasicDetails() {
        return projectService.getAllProjectBasicDetails();
    }
}