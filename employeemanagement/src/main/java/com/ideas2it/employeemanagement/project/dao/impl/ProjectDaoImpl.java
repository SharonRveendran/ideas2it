package com.ideas2it.employeemanagement.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * implementation class of projectDao interface
 * @author Sharon V
 * @created 24-03-2021
 */
public class ProjectDaoImpl implements ProjectDao {
    private SessionFactory sessionFactory = DatabaseConnection.getSessionFactoryInstance();
    EmployeeManagementLogger logger = new EmployeeManagementLogger(ProjectDaoImpl.class); 

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertProject(Project project) throws EmployeeManagementException {
        boolean insertStatus = true;
        Transaction transaction = null; 
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();    
        } catch (HibernateException e) {
            insertStatus = false;
            logger.logError(getStackTrace(e));
            throw new EmployeeManagementException("ProjectCreation failed...");
        }
        return insertStatus;
    }	
  
    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProject(int projectId) throws EmployeeManagementException{ 
        Project project = null;
        try (Session session = sessionFactory.openSession()) {
            project = session.get(Project.class, projectId);      
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
            throw new EmployeeManagementException ("Can't fetch project...!!!");
        }
        return project;
    }  

    /**
     * {@inheritDoc} 
     */
    @Override
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList) 
    		throws EmployeeManagementException {
        List<Project> projects = new ArrayList<Project>();
        try (Session session = sessionFactory.openSession()) {
        	Query query = session.createQuery("from Project where id IN :projectIdList")
        			.setParameter("projectIdList", projectIdList);
        	projects = query.getResultList();     
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
        	throw new EmployeeManagementException("Can't fetch project...!!!");
        }
        return projects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Project getProjectWithEmployee(int projectId) 
    		throws EmployeeManagementException {
        Project project = null;
        try (Session session = sessionFactory.openSession()) {
            project = session.get(Project.class, projectId);
            if (null != project) {
            	for (Employee employee : project.getEmployees()) {}
            }         
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
        	throw new EmployeeManagementException ("Can't fetch project...!!!");
        }
        return project;
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getAllProject(boolean isDeleted) 
    		throws EmployeeManagementException {
        List<Project> projects = new ArrayList<Project>();   
        try (Session session = sessionFactory.openSession()) {
        	Query query = session.createQuery("from Project where isDeleted = :isDeleted")
        			.setParameter("isDeleted", false);
        	projects = query.getResultList(); 
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
        	throw new EmployeeManagementException ("Can't fetch projects...!!!");
        } 
        return projects;   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(Project project) {
        boolean updateStatus = false;
        Transaction transaction = null; 
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
            updateStatus = true;
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
            updateStatus = false;
        }
        return updateStatus;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isIdExist(int projectId) throws EmployeeManagementException {
		boolean isIdExist = false;
		try (Session session = sessionFactory.openSession()) {
			isIdExist = session.get(Project.class, projectId) != null;
		} catch (HibernateException e) {
			logger.logError(getStackTrace(e));
			throw new EmployeeManagementException("Something went wrong...!!!");
		}
		return isIdExist;
	}
	
	/**
	 * Method to get the stack trace of exception as string
	 * @param e Exception object
	 * @return stack trace of exception as string 
	 */
    private String getStackTrace(Exception e) {
    	StackTraceElement[] stack = e.getStackTrace();
		String logMessage = e.getMessage();
		for (StackTraceElement stackTraceElement : stack) {
			logMessage = logMessage + "\n" + stackTraceElement.toString();
		}
		return logMessage;
    }
}