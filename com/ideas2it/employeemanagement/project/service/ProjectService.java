package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;

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
     * Methode to validate id
     * @param input project id in string format which need to be validate
     * @return true if given id is valid else false
     */
    public boolean isValidId(String input);

    /**
     * Methode to get project details based on project id
     * @param projectId id of project which need to display
     * @return project details as a string
     */
    public String getProject(int projectId);

    /**
     * Method to display all project present in database
     * @return list of project details in string format
     */
    public List<String> getAllProject();

    /**
     * Method to delete project based on projectId
     * @param projectId project id for deletion of project
     * @return true for succeessfull deletion else false
     */
    public boolean deleteProject(int projectId);
}