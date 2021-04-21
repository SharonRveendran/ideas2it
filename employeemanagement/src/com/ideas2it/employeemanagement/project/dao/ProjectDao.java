package com.ideas2it.employeemanagement.project.dao;

import java.util.List;

import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.CreateFailException;
import com.ideas2it.exceptions.FetchFailException;

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
     */
    public boolean insertProject(Project project) throws CreateFailException;  
    
    /**
     * Method to get project object based on project id
     * @param projectId id of project which need to display
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
     * Method to get project object with employee object list based on project id
     * @param projectId id of project which need to display
     * @return project object
     * @throws FetchFailException 
     */
    public Project getProjectWithEmployee(int projectId) throws FetchFailException;

    /**
     * Method to display all project present in database
     * @param isDeleted indicating the project is deleted or not
     * @return list of project objects 
     * @throws FetchFailException 
     */
    public List<Project> getAllProject(boolean isDeleted) throws FetchFailException;

     /**
     * Method to delete project based on projectId
     * @param project project object for delete project
     * @return true for successful deletion else false
     */
    public boolean updateProject(Project project);

    /**
     * Method to display all project present in database
     * @return list of project objects 
     * @throws FetchFailException 
     */
    public List<Project> getAllProjectWithEmployee() throws FetchFailException;

    /**
     * Method to check the id is present in database or not
     * @param projectId project id
     * @return true if id present in database else false
     */
	public boolean isIdExist(int projectId);
}