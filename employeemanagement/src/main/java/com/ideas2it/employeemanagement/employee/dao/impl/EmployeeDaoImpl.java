package com.ideas2it.employeemanagement.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private SessionFactory sessionFactory = DatabaseConnection.getSessionFactoryInstance();	
    EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeDaoImpl.class); 
   
    /** 
     * {@inheritDoc}
     */
    @Override
     public void insertEmployee(Employee employee) throws EmployeeManagementException {
        Transaction transaction = null; 
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
            throw new EmployeeManagementException("Employee Creation failed...!!!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExist(int id) throws EmployeeManagementException {
    	boolean isIdExist = false;
    	try (Session session = sessionFactory.openSession()) {
    		Employee employee = session.get(Employee.class, id);
			if (null != employee) {
				isIdExist = !employee.getIsDeleted();
			}
		} catch (HibernateException e) {
			logger.logError(getStackTrace(e));
			throw new EmployeeManagementException("Something went wrong...!!!");
		}
		return isIdExist;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployee(int id) throws EmployeeManagementException {
        Employee employee = null;
        try (Session session = sessionFactory.openSession()) {
            employee = session.get(Employee.class, id);
            if (null != employee) {
                for (Address address : employee.getAddresses()){}
            }
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
            throw new EmployeeManagementException("Can't fetch employee...!!!");
        }
        return employee; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList) 
    		throws EmployeeManagementException { 
        List<Employee> employees = new ArrayList<Employee>();
        try (Session session = sessionFactory.openSession()) {
        	Query query = session.createQuery("from Employee where id IN :employeeIdList")
        			.setParameter("employeeIdList", employeeIdList);
        	employees = query.getResultList();      
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
        	throw new EmployeeManagementException("Can't fetch employee...!!!");
        }
        return employees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
     public Employee getEmployeeWithProject(int employeeId) throws EmployeeManagementException {
        Employee employee = null;
        try (Session session = sessionFactory.openSession()) {
            employee = session.get(Employee.class, employeeId);
            if (null != employee) {
                for (Project project : employee.getProjects()){} 
                for (Address address : employee.getAddresses()){}
            }
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
        	throw new EmployeeManagementException("Can't fetch employee...!!!");
        }
        return employee; 
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployee() throws EmployeeManagementException {
        List<Employee> employees = new ArrayList<Employee>();  
        try (Session session = sessionFactory.openSession()) {
        	Query query = session.createQuery("from Employee where isDeleted = :isDeleted")
        			.setParameter("isDeleted", false);
        	employees = query.getResultList(); 
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
        	throw new EmployeeManagementException("Can't fetch employees...!!!");
        }
        return employees;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
     public void updateEmployee(Employee employee) throws EmployeeManagementException {
        Transaction transaction = null; 
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();  
        } catch (HibernateException e) {
        	logger.logError(getStackTrace(e));
        	throw new EmployeeManagementException("Something went wrong...!!!");
        }
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