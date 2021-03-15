package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.sessionfactory.DatabaseConnection;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 15-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getDatabaseConnection();	
    PreparedStatement preparedStatement;
    ResultSet resultSet;
  
    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployee(int id) throws SQLException  {
        preparedStatement = connection.prepareStatement
                ("select * from employee inner join address on employee.id"
                + " = address.employee_id where employee.id = ?");
        preparedStatement.setInt(1, id);         
	resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Employee employee = new Employee(resultSet.getString(2),
                      resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(1),
                      resultSet.getLong(3), resultSet.getDate(4),employeeAddresses);	                
        do {
             List<Address> employeeAddresses = new ArrayList<Address>();
             Address employeeAddress = new Address(resultSet.getInt(7), resultSet.getInt(8),
                     resultSet.getString(9), resultSet.getString(10),
                     resultSet.getString(11),resultSet.getString(12),
                     resultSet.getString(13), resultSet.getString(14));
             employeeAddresses.add(employeeAddress);             
        }while(resultSet.next());                  
        return employee;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployee() throws SQLException {
        int flag = 0;
        List<Employee> employees = new ArrayList<Employee>(); 
        preparedStatement = connection.prepareStatement
                ("select * from employee inner join address on "
                + "employee.id = address.employee_id");         
	resultSet = preparedStatement.executeQuery();	
        resultSet.next();               
	do{       
            Employee employee = new Employee(resultSet.getString(2),
                      resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(1),
                      resultSet.getLong(3), resultSet.getDate(4),null);         
            int employeeId = resultSet.getInt(1);
            List<Address> employeeAddresses = new ArrayList<Address>();
            while(employeeId == resultSet.getInt(1)) {
                Address employeeAddress = new Address(resultSet.getInt(7), resultSet.getInt(8),
                         resultSet.getString(9), resultSet.getString(10),
                         resultSet.getString(11),resultSet.getString(12),
                         resultSet.getString(13), resultSet.getString(14));
                employeeAddresses.add(employeeAddress); 
                if ( !resultSet.next()){
                    flag = 1;
                    break;
                }               
            } 
            employee.setEmployeeAddresses(employeeAddresses);
            employees.add(employee);
        } while( 0 == flag);
        return employees;
    }
      
    /**
     * {@inheritdoc}
     */
    @Override
     public void insertEmployee(Employee employee) throws SQLException {
         List<Address> employeeAddresses = new ArrayList<Address>();
         Address employeeAddress;
	 preparedStatement = connection.prepareStatement
                 ("insert into employee values(?, ?, ?, ?, ?, ?)");
         preparedStatement.setInt(1, employee.getId());
	 preparedStatement.setString(2, employee.getName());
	 preparedStatement.setLong(3, employee.getMobile());
         preparedStatement.setDate(4, employee.getDob());
         preparedStatement.setString(5, employee.getDesignation());
	 preparedStatement.setDouble(6, employee.getSalary());
         preparedStatement.executeUpdate();
         preparedStatement = connection.prepareStatement
                 ("select max(id) from employee");
         resultSet = preparedStatement.executeQuery();
         resultSet.next();		
         int employeeId = resultSet.getInt(1);	         
         employeeAddresses = employee.getEmployeeAddresses();
         for (int index = 0; index < employeeAddresses.size(); index++) {
             employeeAddress = employeeAddresses.get(index);
             preparedStatement = connection.prepareStatement
                     ("insert into address values(?, ?, ?, ?, ?, ?, ?, ?)");
             preparedStatement.setInt(1,0);
             preparedStatement.setInt(2,employeeId);
             preparedStatement.setString(3,employeeAddress.getDoorNumber());
             preparedStatement.setString(4,employeeAddress.getStreet());
             preparedStatement.setString(5,employeeAddress.getDistrict());
             preparedStatement.setString(6,employeeAddress.getState());
             preparedStatement.setString(7,employeeAddress.getCountry());
             preparedStatement.setString(8,employeeAddress.getAddressType());
             preparedStatement.executeUpdate();
         }
    }

    /**
     * {@inheritdoc}
     */
    @Override
     public void deleteEmployee(int id) throws SQLException {
         preparedStatement = connection.prepareStatement
                 ("delete from employee where id=?");
         preparedStatement.setInt(1, id);
         preparedStatement.executeUpdate();
         preparedStatement = connection.prepareStatement
                 ("delete from address where employee_id=?");
         preparedStatement.setInt(1, id);
         preparedStatement.executeUpdate();
     }   
         
    /**
     * {@inheritdoc}
     */
    @Override
    public void updateEmployee(int id, String name, String designation,
            double salary, Date dob, long mobile, String option) 
            throws SQLException {
        if ("name".equals(option)) {
    	    preparedStatement = connection.prepareStatement
                    ("update employee set employee_name = ? where id=?");  
           preparedStatement.setString(1, name);
    	}
    	if ("designation".equals(option)) {
    	    preparedStatement = connection.prepareStatement
                    ("update employee set employee_designation = ? where id=?");  
            preparedStatement.setString(1, designation);
    	}
    	if ("salary".equals(option)) {
    	    preparedStatement = connection.prepareStatement
                    ("update employee set employee_salary = ? where id=?");  
            preparedStatement.setDouble(1, salary);
    	}
    	if ("dob".equals(option)) {
    	    preparedStatement = connection.prepareStatement
                    ("update employee set employee_dob = ? where id=?");  
            preparedStatement.setDate(1, dob);
    	}
    	if ("mobile".equals(option)) {
    	    updateMobile(mobile);
    	}
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    } 

    /** 
     * Method to update mobile number
     * @param mobile employee mobile number
     */
    private void updateMobile(long mobile) throws SQLException {
         preparedStatement = connection.prepareStatement
                 ("update employee set employee_mobile = ? where id=?");  
         preparedStatement.setLong(1, mobile);
    }
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) throws SQLException {
        preparedStatement = connection.prepareStatement
                ("select id from employee where id = ?");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateAddress(Address employeeAddress) throws SQLException {
        preparedStatement = connection.prepareStatement
                ("update address set door_number = ?, street = ?, district = ?,"
                + "state = ?,country = ?, address_type= ? where address_id = ?");
        preparedStatement.setString(1,employeeAddress.getDoorNumber());
        preparedStatement.setString(2,employeeAddress.getStreet());
        preparedStatement.setString(3,employeeAddress.getDistrict());
        preparedStatement.setString(4,employeeAddress.getState());
        preparedStatement.setString(5,employeeAddress.getCountry());
        preparedStatement.setString(6,employeeAddress.getAddressType());
        preparedStatement.setInt(7,employeeAddress.getAddressId());
        preparedStatement.executeUpdate();   
    }
}