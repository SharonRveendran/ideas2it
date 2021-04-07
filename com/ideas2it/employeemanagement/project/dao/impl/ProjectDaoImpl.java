package com.ideas2it.employeemanagement.project.dao.impl;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.Criteria;

import org.hibernate.criterion.Restrictions;

import com.ideas2it.employeemanagement.employee.model.Employee;
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
    private SessionFactory sessionFactory = DatabaseConnection.getSessionFactoryInstance();
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean insertProject(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
        session.close();
        return true;
    }	
  
    /**
     * {@inheritdoc}
     */
    @Override
    public Project getProject(int projectId){
        Session session = sessionFactory.openSession();
        Project project = session.get(Project.class, projectId);
        session.close();
        return project;
    }  

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getAllProject(boolean isDeleted) {
        Session session = sessionFactory.openSession();
        List<Project> projects = new ArrayList<Project>();  
        Criteria criteria = session.createCriteria(Project.class);
        criteria.add(Restrictions.eq("isDeleted",isDeleted));
        for (Object object : criteria.list()) {
            projects.add((Project)object);
        }
        return projects;   
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProject(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
        session.close();
        return true;
    } 
}