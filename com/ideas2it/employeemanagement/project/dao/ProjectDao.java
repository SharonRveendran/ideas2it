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
    public List<Project> getAllProject(int isDeleted);

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
     * @param project project object
     * @param option indicating which project details need to update
     * @return true for successfull updation else false
     */
    public boolean updateProject(Project project, String option);

     /**
     * Method to assign employees to project
     * @param project project pojo object
     * @return true for successfull assignment else false
     */
    public boolean assignEmployee(Project project);

    /**
     * Method to get all projects basics details
     * @param projectId project id
     * @return List of employee id assigtned for given project id
     */
    public List<Integer> getEmployeesId(int projectId);

    /**
     * Method to remove employees from project
     * @param project project pojo object
     * @return true for successfull remove else false
     */
    public boolean removeEmployee(Project project);
}