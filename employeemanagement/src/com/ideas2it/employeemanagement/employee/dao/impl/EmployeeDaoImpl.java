package com.ideas2it.employeemanagement.employee.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;
import com.ideas2it.exceptions.CreateFailException;
import com.ideas2it.exceptions.FetchFailException;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private SessionFactory sessionFactory = DatabaseConnection.getSessionFactoryInstance();	
   
    /** 
     * {@inheritDoc}
     * @throws CreateFailException 
     */
    @Override
     public void insertEmployee(Employee employee) throws CreateFailException {
        Session session = null; 
        Transaction transaction = null; 
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e1) {
            throw new CreateFailException("Creation Failed...!!!");
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIdExist(int id) {
        Session session = null; 
        Employee employee = null;
        boolean isIdExist = false;
        Query query;
        try {
            session = sessionFactory.openSession();
            query = session.createQuery("select id from Employee where id = " + id);
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

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public Employee getEmployee(int id) throws FetchFailException {
        Session session = null; 
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, id);
            if (null != employee) {
                for (Address address : employee.getAddresses()){}
            }
        } catch (HibernateException e1) {
            throw new FetchFailException("Can't get Employee");
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
            	e2.printStackTrace();
            }
        }
        return employee; 
    }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList) throws FetchFailException {
        Session session = null; 
        List<Employee> employees = new ArrayList<Employee>();
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.in("id", employeeIdList));
            employees = criteria.list();      
        } catch (HibernateException e1) {
        	throw new FetchFailException("Can't get Employees");
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
            	e2.printStackTrace();
            }
        }
        return employees;
    }

    /**
     * Method to get employee with project from database
     * @param employeeId employee id
     * @return employee details as a string
     * @throws FetchFailException 
     */
     public Employee getEmployeeWithProject(int employeeId) throws FetchFailException {
        Session session = null; 
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, employeeId);
            if (null != employee) {
                for (Project project : employee.getProjects()){} 
            }
        } catch (HibernateException e1) {
        	throw new FetchFailException("Can't get Employees");
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
            	e2.printStackTrace();
            }
        }
        return employee; 
     }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Employee> getAllEmployee() throws FetchFailException {
        Session session = null; 
        List<Employee> employees = new ArrayList<Employee>();  
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("isDeleted",false));
            employees = criteria.list();   
        } catch (HibernateException e1) {
        	throw new FetchFailException("Can't get Employees");
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
            	e2.printStackTrace();
            }
        }
        return employees;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
     public boolean updateEmployee(Employee employee) {
        boolean updateStatus = false;
        Session session = null; 
        Transaction transaction = null; 
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(employee);
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
    public List <Employee> getDeletedEmployees() { 
        List<Employee> employees = new ArrayList<Employee>();
        Session session = null;
        try {
            session = sessionFactory.openSession();  
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("isDeleted",true));
            employees = criteria.list();    
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return employees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map <Integer, Address> getAddressList(int employeeId) {
        Map<Integer, Address> addressList = new HashMap<Integer, Address>();
        Session session = null; 
        Employee employee = null;
        Query query;
        try {
            session = sessionFactory.openSession();
            query = session.createQuery("from Address where employee_id = " + employeeId);
            for (Object object : query.list()) {
                Address address = (Address) object;
                if (!address.getIsDeleted()) {
                    addressList.put(address.getAddressId(), address);
                }
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
        return addressList;
    }
}