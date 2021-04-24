package com.ideas2it.employeemanagement.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
        } catch (HibernateException | NullPointerException e) {
        	logger.logError(e);
            throw new EmployeeManagementException("Insertion failed...!!!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExist(int id) throws EmployeeManagementException {
    	boolean isIdExist = false;
    	try (Session session = sessionFactory.openSession()) {
			isIdExist = session.get(Employee.class, id) != null;
		} catch (HibernateException | NullPointerException e) {
			logger.logError(e);
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
        } catch (HibernateException | NullPointerException e) {
        	logger.logError(e);
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
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.in("id", employeeIdList));
            employees = criteria.list();      
        } catch (HibernateException | NullPointerException e) {
        	logger.logError(e);
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
            }
        } catch (HibernateException | NullPointerException e) {
        	logger.logError(e);
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
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("isDeleted", false));
            employees = criteria.list();   
        } catch (HibernateException | NullPointerException e) {
        	logger.logError(e);
        	throw new EmployeeManagementException("Can't fetch employees...!!!");
        }
        return employees;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
     public boolean updateEmployee(Employee employee) throws EmployeeManagementException {
        boolean updateStatus = false;
        Transaction transaction = null; 
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();  
            updateStatus = true;
        } catch (HibernateException | NullPointerException e) {
        	logger.logError(e);
            updateStatus = false;
        }
        return updateStatus;
     }
}