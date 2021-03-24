package com.ideas2it.employeemanagement.project.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;

/**
 * implementation class of projectDao interface
 * @author Sharon V
 * @created 24-03-2021
 */
public class ProjectDaoImpl implements ProjectDao {
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
   
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean insertProject(Project project) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement
                    ("insert into project(name, manager_name,"
                    + "start_date, end_date) values( ?, ?, ?, ?)");
            preparedStatement.setString(1, project.getName());
	    preparedStatement.setString(2, project.getManagerName());
	    preparedStatement.setDate(3, project.getStartDate());
            preparedStatement.setDate(4, project.getEndDate());
            preparedStatement.executeUpdate();   
        } catch(SQLException e) {
            return false;
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }	

    /**
     * {@inheritdoc}
     */
    @Override
    public Project getProject(int projectId){
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Project project = null;
        try{
            preparedStatement = connection.prepareStatement
                    ("select name, manager_name, start_date, end_date "
                    + "from project where id = ? and is_deleted = 0");
            preparedStatement.setInt(1, projectId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                project = new Project(resultSet.getString("name"),
                        resultSet.getString("manager_name"), resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),null);                
            } else {
                return null;
            }              
        } catch(SQLException e) {
            e.printStackTrace();   
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return project;  
    }	

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getAllProject() {
        List<Project> projects = new ArrayList<Project>();
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Project project = null;
        try{
            preparedStatement = connection.prepareStatement
                    ("select id,name, manager_name, start_date, end_date "
                    + "from project where is_deleted = 0");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                do {
                    project = new Project(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("manager_name"), resultSet.getDate("start_date"),
                            resultSet.getDate("end_date"),null); 
                    projects.add(project);
                } while(resultSet.next());               
            } else {
                return null;
            }              
        } catch(SQLException e) {
            e.printStackTrace();   
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return projects;  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteProject(int projectId) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        boolean deleteStatus = false;;
        try{
            preparedStatement = connection.prepareStatement
                    ("update project set is_deleted = 1 where id = ? and is_deleted = 0"); 
            preparedStatement.setInt(1, projectId);
            deleteStatus = (0 != preparedStatement.executeUpdate());            
        } catch(SQLException e) {
            e.printStackTrace();   
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return deleteStatus;
    }
}