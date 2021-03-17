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
                + " = address.employee_id where employee.id = ? and employee.is_deleted = 0");
        preparedStatement.setInt(1, id);         
	resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Employee employee = new Employee(resultSet.getString(2),
                          resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(1),
                          resultSet.getLong(3), resultSet.getDate(4),null);	                
            List<Address> employeeAddressList = new ArrayList<Address>();
            outer: do {
                while(1 == resultSet.getInt(16)) {
                    if (!resultSet.next()) {
                        break outer;
                    }
                }
                Address employeeAddress = new Address(resultSet.getInt(8), resultSet.getInt(9),
                        resultSet.getString(10), resultSet.getString(11),
                        resultSet.getString(12),resultSet.getString(13),
                        resultSet.getString(14), resultSet.getString(15));
                employeeAddressList.add(employeeAddress);             
            } while(resultSet.next());  
            employee.setEmployeeAddresses(employeeAddressList);                
            return employee;
        } else { 
            return null;
        }
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
                + "employee.id = address.employee_id where employee.is_deleted = 0");         
	resultSet = preparedStatement.executeQuery();	
        if (resultSet.next()) {                     
	    outer: do{  
                List<Address> employeeAddresses = new ArrayList<Address>();
                int employeeId = resultSet.getInt(1);
                Employee employee = new Employee(resultSet.getString(2),
                          resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(1),
                          resultSet.getLong(3), resultSet.getDate(4),null);         
                inner: while(employeeId == resultSet.getInt(1)) {
                    while(1 == resultSet.getInt(16)) {
                        if (!resultSet.next()) {
                            break outer;
                        }
                        if (employeeId != resultSet.getInt(1)) {
                            break inner;
                        }
                    }
                    Address employeeAddress = new Address(resultSet.getInt(8), resultSet.getInt(9),
                             resultSet.getString(10), resultSet.getString(11),
                             resultSet.getString(12),resultSet.getString(13),
                             resultSet.getString(14), resultSet.getString(15));
                    employeeAddresses.add(employeeAddress); 
                    if ( !resultSet.next()){
                        flag = 1;
                        break;
                    }               
                } 
                employee.setEmployeeAddresses(employeeAddresses);
                employees.add(employee);
            } while(0 == flag);
        }
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
                 ("insert into employee values(?, ?, ?, ?, ?, ?, ?)");
         preparedStatement.setInt(1, employee.getId());
	 preparedStatement.setString(2, employee.getName());
	 preparedStatement.setLong(3, employee.getMobile());
         preparedStatement.setDate(4, employee.getDob());
         preparedStatement.setString(5, employee.getDesignation());
	 preparedStatement.setDouble(6, employee.getSalary());
         preparedStatement.setInt(7, 0);
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
                     ("insert into address values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
             preparedStatement.setInt(1,0);
             preparedStatement.setInt(2,employeeId);
             preparedStatement.setString(3,employeeAddress.getDoorNumber());
             preparedStatement.setString(4,employeeAddress.getStreet());
             preparedStatement.setString(5,employeeAddress.getDistrict());
             preparedStatement.setString(6,employeeAddress.getState());
             preparedStatement.setString(7,employeeAddress.getCountry());
             preparedStatement.setString(8,employeeAddress.getAddressType());
             preparedStatement.setInt(9,0);
             preparedStatement.executeUpdate();
         }
    }

    /**
     * {@inheritdoc}
     */
    @Override
     public void deleteEmployee(int id) throws SQLException {
         preparedStatement = connection.prepareStatement
                 ("update employee set is_deleted ='1' where id=?");
         preparedStatement.setInt(1, id);
         preparedStatement.executeUpdate();
         preparedStatement = connection.prepareStatement
                 ("update address set is_deleted ='1' where employee_id=?");
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
    public boolean updateAddress(Address employeeAddress, String input) throws SQLException {
        int option = Integer.parseInt(input);
        preparedStatement = connection.prepareStatement
                ("select id from address where employee_id = ? and is_deleted = 0",
                ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setInt(1, employeeAddress.getEmployeeId());
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        if (resultSet.getRow() >= option) {
            resultSet.first();
            while(option > 1) {
                resultSet.next();
                option--;
            }
            int addressId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement
                    ("update address set door_number = ?, street = ?, district = ?,"
                    + "state = ?,country = ?, type= ? where id = ?");
            preparedStatement.setString(1,employeeAddress.getDoorNumber());
            preparedStatement.setString(2,employeeAddress.getStreet());
            preparedStatement.setString(3,employeeAddress.getDistrict());
            preparedStatement.setString(4,employeeAddress.getState());
            preparedStatement.setString(5,employeeAddress.getCountry());
            preparedStatement.setString(6,employeeAddress.getAddressType());
            preparedStatement.setInt(7,addressId);
            preparedStatement.executeUpdate();
            return true;
        } else { 
            return false;
        }  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public String recoverEmployee(int id) throws SQLException {
       preparedStatement = connection.prepareStatement
                ("select * from employee where id = ?"); 
       preparedStatement.setInt(1,id);
       resultSet = preparedStatement.executeQuery();
       if (!resultSet.next()) {
           return null;
       } else if (0 == resultSet.getInt(7)) {
           return "Employee already present";
       } else {
           preparedStatement = connection.prepareStatement
                  ("update employee set is_deleted = 0 where id = ?"); 
           preparedStatement.setInt(1,id);
           preparedStatement.executeUpdate();
           preparedStatement = connection.prepareStatement
                  ("update address set is_deleted = 0 where employee_id = ?");
           preparedStatement.setInt(1,id);
           preparedStatement.executeUpdate(); 
           return "Recovery Successfull...";
       }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List <Address> getAddressList(int employeeId)throws SQLException {
       preparedStatement = connection.prepareStatement
                ("select * from address where employee_id = ? and is_deleted = 0"); 
       preparedStatement.setInt(1,employeeId);
       resultSet = preparedStatement.executeQuery();
       List <Address> addressList = new ArrayList <Address> ();
       while(resultSet.next()) {
           if(1 == resultSet.getInt(9)) {
               if(!resultSet.next()) {
                   break;
               }
            }
            Address address = new Address(resultSet.getInt(1), resultSet.getInt(2),
                         resultSet.getString(3), resultSet.getString(4),
                         resultSet.getString(5),resultSet.getString(6),
                         resultSet.getString(7), resultSet.getString(8));
            addressList.add(address);
       }
       return addressList;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteAddress(int employeeId, String input)throws SQLException {
       int option = Integer.parseInt(input);
        preparedStatement = connection.prepareStatement
                ("select * from address where employee_id = ? and is_deleted = 0",
                ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setInt(1, employeeId);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        if (resultSet.getRow() >= option) {
            resultSet.first();
            while(option > 1) {
                resultSet.next();
                option--;
            } 
            int addressId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement
                    ("update address set is_deleted = 1 where id = ?");
            preparedStatement.setInt(1, addressId);
            preparedStatement.executeUpdate();
            return true;
        } else { 
            return false;
        }  
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Address> getDeletedAddressList(int employeeId)throws SQLException {
       preparedStatement = connection.prepareStatement
                ("select * from address where employee_id = ? and is_deleted = 1"); 
       preparedStatement.setInt(1,employeeId);
       resultSet = preparedStatement.executeQuery();
       List <Address> addressList = new ArrayList <Address> ();
       while(resultSet.next()) {
            Address address = new Address(resultSet.getInt(1), resultSet.getInt(2),
                         resultSet.getString(3), resultSet.getString(4),
                         resultSet.getString(5),resultSet.getString(6),
                         resultSet.getString(7), resultSet.getString(8));
            addressList.add(address);
       }
       return addressList;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean recoverAddress(int employeeId,int option) throws SQLException {
        preparedStatement = connection.prepareStatement
                ("select id from address where employee_id = ? and is_deleted = 1",
                ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        preparedStatement.setInt(1, employeeId);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        if (resultSet.getRow() >= option) {
            resultSet.first();
            while(option > 1) {
                resultSet.next();
                option--;
            }
            int addressId = resultSet.getInt(1);
            preparedStatement = connection.prepareStatement
                    ("update address set is_deleted = 0 where id = ?"); 
            preparedStatement.setInt(1,addressId);
            preparedStatement.executeUpdate();
            return true;
        } else {
            return false;
        }    
    }
}