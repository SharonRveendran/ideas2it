package com.ideas2it.employeemanagement.project.dao;

import java.util.List;

import com.ideas2it.employeemanagement.project.model.Project;

/**
 * Interface to interact with database
 * @author Sharon V
 * @created 24-03-2021
 */
public interface ProjectDao {

    /**
     * Methode to insert project to database
     * @param project project object
     * @return true for succeessfull project creation else false.
     */
    public boolean insertProject(Project project);  
    
    /**
     * Methode to get project object based on project id
     * @param projectId id of project which need to display
     * @return project object
     */
    public Project getProject(int projectId);

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of project objects 
     */
    public List<Project> getAllProject(boolean isDeleted);

     /**
     * Method to delete project based on projectId
     * @param project project object for delete project
     * @return true for succeessfull deletion else false
     */
    public boolean updateProject(Project project);

}