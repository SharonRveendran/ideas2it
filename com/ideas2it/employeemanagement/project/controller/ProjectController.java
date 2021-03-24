package com.ideas2it.employeemanagement.project.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.employeemanagement.project.service.ProjectService;

/**
 * Class for Project controller
 * @author Sharon V
 * @created 24-03-2021
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
     * @return list of employee details in string format
     */
    public List<String> getAllProject() {
        return projectService.getAllProject();
    }

    /**
     * Method to delete project based on projectId
     * @param projectId project id for deletion of project
     * @return true for succeessfull deletion else false
     */
    public boolean deleteProject(int projectId) {
        return projectService.deleteProject(projectId);
    }
}