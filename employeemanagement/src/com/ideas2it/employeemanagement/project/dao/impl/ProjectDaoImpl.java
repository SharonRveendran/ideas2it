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
     * @throws CreateFailException 
     */
    @Override
    public boolean insertProject(Project project) throws EmployeeManagementException {
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
            logger.logError(e1);
            throw new EmployeeManagementException("ProjectCreation failed...");
        } finally {
            try {
            	if (null != session) {
            		session.close();
            	}
            } catch (HibernateException e2) {
            	logger.logError(e2);
            }
        }
        return insertStatus;
    }	
  
    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public Project getProject(int projectId) throws EmployeeManagementException{
        Session session = null; 
        Project project = null;
        try {
            session = sessionFactory.openSession();
            project = session.get(Project.class, projectId);      
        } catch (HibernateException e1) {
        	logger.logError(e1);
            throw new EmployeeManagementException ("Can't fetch project...!!!");
        } finally {
            try {
            	if (null != session) {
            		session.close();
            	}
            } catch (HibernateException e2) {
            	logger.logError(e2);
            }
        }
        return project;
    }  

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList) 
    		throws EmployeeManagementException {
        Session session = null; 
        Project project = null;
        List<Project> projects = new ArrayList<Project>();
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.in("id", projectIdList));
            projects = criteria.list();      
        } catch (HibernateException e1) {
        	logger.logError(e1);
        	throw new EmployeeManagementException("Can't fetch project...!!!");
        } finally {
            try {
            	if (null != session) {
            		session.close();
            	}
            } catch (HibernateException e2) {
            	logger.logError(e2);
            }
        }
        return projects;
    }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public Project getProjectWithEmployee(int projectId) 
    		throws EmployeeManagementException {
        Project project = null;
        Session session = null; 
        try {
            session = sessionFactory.openSession();
            project = session.get(Project.class, projectId);
            if (null != project) {
            	for (Employee employee : project.getEmployees()) {}
            }         
        } catch (HibernateException e1) {
        	logger.logError(e1);
        	throw new EmployeeManagementException ("Can't fetch project...!!!");
        } finally {
            try {
            	if (null != session) {
            		session.close();
            	}
            } catch (HibernateException e2) {
            	logger.logError(e2);
            }
        }
        return project;
    } 

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Project> getAllProject(boolean isDeleted) 
    		throws EmployeeManagementException {
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
        	logger.logError(e1);
        	throw new EmployeeManagementException ("Can't fetch projects...!!!");
        } finally {
            try {
            	if (null != session) {
            		session.close();
            	}
            } catch (HibernateException e2) {
            	logger.logError(e2);
            }
        }
        return projects;   
    }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Project> getAllProjectWithEmployee() throws EmployeeManagementException {
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
        	logger.logError(e1);
        	throw new EmployeeManagementException ("Can't fetch projects...!!!");
        } finally {
            try {
            	if (null != session) {
            		session.close();
            	}
            } catch (HibernateException e2) {
            	logger.logError(e2);
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
        	logger.logError(e1);
            updateStatus = false;
        } finally {
            try {
            	if (null != session) {
            		session.close();
            	}
            } catch (HibernateException e2) {
            	logger.logError(e2);
            }
        }
        return updateStatus;
    }

    /**
     * {@inheritDoc}
     * @throws EmployeeManagementException 
     */
	@Override
	public boolean isIdExist(int projectId) throws EmployeeManagementException {
		Session session = null;
		Employee employee = null;
		boolean isIdExist = false;
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("select id from Project where id = " + projectId);
			isIdExist = null != query.uniqueResult() ? true : false;
		} catch (HibernateException e1) {
			logger.logError(e1);
			throw new EmployeeManagementException("Something went wrong...!!!");
		} finally {
			try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e2) {
				logger.logError(e2);
			}
		}
		return isIdExist;
	}
}