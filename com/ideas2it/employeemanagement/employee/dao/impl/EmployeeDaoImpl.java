package com.ideas2it.employeemanagement.employee.dao.impl;

import java.sql.Date;
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

/**
 * Class to interact with database
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private SessionFactory sessionFactory = DatabaseConnection.getSessionFactoryInstance();	
   
    /** 
     * {@inheritdoc}
     */
    @Override
     public void insertEmployee(Employee employee) {
        Session session = null; 
        Transaction transaction = null; 
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) {
        Session session = null; 
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, id);
        } catch (HibernateException e1) {
            e1.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (HibernateException e2) {
                e2.printStackTrace();
            }
        }
        return (null == employee) ? false : true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployee(int id) {
        Session session = null; 
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, id);
            for (Address address : employee.getAddresses()){}    
        } catch (HibernateException e1) {
            e1.printStackTrace();
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
     * Method to get employee with project from database
     * @param employeeId employee id
     * @return employee details as a string
     */
     public Employee getEmployeeWithProject(int employeeId) {
        Session session = null; 
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, employeeId);
        for (Project project : employee.getProjects()){}   
        } catch (HibernateException e1) {
            e1.printStackTrace();
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
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployee() {
        Session session = null; 
        List<Employee> employees = new ArrayList<Employee>();  
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("isDeleted",false));
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
     * {@inheritdoc}
     */
    @Override
     public boolean updateEmployee(Employee employee) {
        boolean updateStatus = true;
        Session session = null; 
        Transaction transaction = null; 
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();   
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
     * {@inheritdoc} 
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
     * {@inheritdoc}
     */
    @Override
    public Map <Integer, Address> getAddressList(int employeeId) {
        Map<Integer, Address> addressList = new HashMap<Integer, Address>();
        Session session = null; 
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, employeeId);
            for (Address address : employee.getAddresses()) {
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