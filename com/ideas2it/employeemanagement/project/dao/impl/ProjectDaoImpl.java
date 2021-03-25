package com.ideas2it.employeemanagement.project.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
                project = new Project(projectId, resultSet.getString("name"),
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
    public List<Project> getAllProject(int isDeleted) {
        List<Project> projects = new ArrayList<Project>();
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Project project = null;
        try{
            preparedStatement = connection.prepareStatement
                    ("select id,name, manager_name, start_date, end_date "
                    + "from project where is_deleted = ?");
            preparedStatement.setInt(1, isDeleted);
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
        boolean deleteStatus = false;
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

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean recoverProject(int projectId) { 
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        boolean recoverStatus = false;
        try{
            preparedStatement = connection.prepareStatement
                    ("update project set is_deleted = 0 where id = ? and is_deleted = 1"); 
            preparedStatement.setInt(1, projectId);
            recoverStatus = (0 != preparedStatement.executeUpdate());            
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
        return recoverStatus;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProject(Project project, String option) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        boolean updateStatus = false;
        try{
            switch (option) {
                case "name":
                    preparedStatement = connection.prepareStatement
                            ("update project set name = ? where id = ? and is_deleted = 0");
                    preparedStatement.setString(1, project.getName());
                    break;
                case "manager name":
                    preparedStatement = connection.prepareStatement
                            ("update project set manager_name = ? where id = ? and is_deleted = 0");
                    preparedStatement.setString(1, project.getManagerName());
                    break;
                case "start date":
                    preparedStatement = connection.prepareStatement
                            ("update project set start_date = ? where id = ? and is_deleted = 0");
                    preparedStatement.setDate(1, project.getStartDate());
                    break;
                case "end date":
                    preparedStatement = connection.prepareStatement
                            ("update project set end_date = ? where id = ? and is_deleted = 0");
                    preparedStatement.setDate(1, project.getEndDate());
            }
            preparedStatement.setInt(2, project.getId());
            updateStatus = (0 != preparedStatement.executeUpdate());       
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
        return updateStatus;    
    }

    public boolean assignEmployee(int employeeId, int projectId) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        boolean assignStatus = false;
        try{
            preparedStatement = connection.prepareStatement
                    ("insert into employee_project values (?, ?)"); 
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setInt(2, projectId);
            assignStatus = (0 != preparedStatement.executeUpdate());            
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
        return assignStatus;     
    }
}