package com.ideas2it.employeemanagement.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    private SessionFactory sessionFactory = DatabaseConnection.getSessionFactoryInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertProject(Project project) {
        boolean insertStatus = true;
        Session session = null; 
        Transaction transaction = null; 
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();    
        } catch (HibernateException e1) {
            insertStatus = false;
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return insertStatus;
    }	
  
    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProject(int projectId){
        Session session = null; 
        Project project = null;
        try {
            session = sessionFactory.openSession();
            project = session.get(Project.class, projectId);      
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return project;
    }  

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList) {
        Session session = null; 
        Project project = null;
        List<Project> projects = new ArrayList<Project>();
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.in("id", projectIdList));
            projects = criteria.list();      
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return projects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProjectWithEmployee(int projectId){
        Project project = null;
        Session session = null; 
        try {
            session = sessionFactory.openSession();
            project = session.get(Project.class, projectId);
            if (null != project) {
            	for (Employee employee : project.getEmployees()) {}
            }
            
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return project;
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getAllProject(boolean isDeleted) {
        List<Project> projects = new ArrayList<Project>();  
        Session session = null;  
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq("isDeleted",isDeleted));
            for (Object object : criteria.list()) {
                projects.add((Project)object);
            }
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return projects;   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getAllProjectWithEmployee() {
        List<Project> projects = new ArrayList<Project>();
        Session session = null; 
        try {
            session = sessionFactory.openSession();  
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq("isDeleted",false));
            for (Object object : criteria.list()) {
                Project project = (Project) object;
                for (Employee employee : project.getEmployees()) {}
                projects.add(project);
            }
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return projects;   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(Project project) {
        boolean updateStatus = false;
        Session session = null; 
        Transaction transaction = null; 
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
            updateStatus = true;
        } catch (Exception e1) {
            updateStatus = false;
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return updateStatus;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isIdExist(int projectId) {
		 Session session = null; 
	        Employee employee = null;
	        boolean isIdExist = false;
	        Query query;
	        try {
	            session = sessionFactory.openSession();
	            query = session.createQuery("select id from Project where id = " + projectId);
	            isIdExist = null != query.uniqueResult() ? true : false;
	        } catch (HibernateException e1) {
	            e1.printStackTrace();
	        } finally {
	            try {
	                session.close();
	            } catch (HibernateException e2) {
	                e2.printStackTrace();
	            }
	        }
	        return isIdExist;
	} 
}