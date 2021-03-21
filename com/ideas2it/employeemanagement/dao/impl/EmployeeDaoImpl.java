package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.dao.EmployeeDao;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.sessionfactory.DatabaseConnection;

/**
 * Class to interact with database
 * @author Sharon V
 * @created 18-03-2021
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
                ("select * from employee left join address on employee.id"
                + " = address.employee_id where employee.id = ? and employee.is_deleted = 0");
        preparedStatement.setInt(1, id);         
	resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Employee employee = createEmployee(resultSet);	                
            List<Address> employeeAddressList = new ArrayList<Address>();
            outer: do {
                while(1 == resultSet.getInt(16)) {
                    if (!resultSet.next()) {
                        break outer;
                    }
                }
                Address employeeAddress = createAddress(resultSet);
                employeeAddressList.add(employeeAddress);             
            } while(resultSet.next());  
            employee.setEmployeeAddresses(employeeAddressList);                
            return employee;
        } else { 
            return null;
        }
    }
   
    /**
     * Methode to create employee object
     * @param resultSet
     * @return employee object
     */ 
    private Employee createEmployee(ResultSet resultSet)throws SQLException {
        Employee employee = new Employee(resultSet.getString(2),
                          resultSet.getString(5), resultSet.getDouble(6), resultSet.getInt(1),
                          resultSet.getLong(3), resultSet.getDate(4),null);
        return employee;
    }

    /**
     * Methode to create address object
     * @param resultSet
     * @return address object
     */ 
    private Address createAddress(ResultSet resultSet)throws SQLException {
        Address employeeAddress = new Address(resultSet.getInt(8), resultSet.getInt(9),
                        resultSet.getString(10), resultSet.getString(11),
                        resultSet.getString(12),resultSet.getString(13),
                        resultSet.getString(14), resultSet.getString(15));
        return employeeAddress;
    }


    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployee() throws SQLException {
        boolean resultSetEnd = false;
        List<Employee> employees = new ArrayList<Employee>(); 
        preparedStatement = connection.prepareStatement
                ("select * from employee left join address on employee.id"
                + "= address.employee_id where employee.is_deleted = 0");         
	resultSet = preparedStatement.executeQuery();	
        if (resultSet.next()) {                     
	    outer: do{  
                List<Address> employeeAddresses = new ArrayList<Address>();
                int employeeId = resultSet.getInt(1);
                Employee employee = createEmployee(resultSet);         
                inner: while(employeeId == resultSet.getInt(1)) {
                    while(1 == resultSet.getInt(16)) {
                        if (!resultSet.next()) {
                            break outer;
                        }
                        if (employeeId != resultSet.getInt(1)) {
                            break inner;
                        }
                    }
                    Address employeeAddress = createAddress(resultSet);
                    employeeAddresses.add(employeeAddress); 
                    if ( !resultSet.next()){
                        resultSetEnd = true;
                        break;
                    }               
                } 
                employee.setEmployeeAddresses(employeeAddresses);
                employees.add(employee);
            } while(resultSetEnd == false);
        }
        return employees;
    }
      
    /**
     * {@inheritdoc}
     */
    @Override
     public void insertEmployee(Employee employee) throws SQLException {
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
         List<Address> employeeAddresses = employee.getEmployeeAddresses();
         try {
             insertAddress(employeeAddresses);
         } catch (Exception e) {
            e.printStackTrace();
         }
    }

    /**
     * Method to insert address
     * @param employee employee object
     */
    private void insertAddress(List<Address> employeeAddresses)throws Exception {
        Address employeeAddress;
        preparedStatement = connection.prepareStatement
                 ("select max(id) from employee");
         resultSet = preparedStatement.executeQuery();
         resultSet.next();		
         int employeeId = resultSet.getInt(1);
         preparedStatement = connection.prepareStatement
                     ("insert into address(employee_id, door_number, street, district,"
                     + "state, country, type, is_deleted) values(?, ?, ?, ?, ?, ?, ?, ?)");	         
         for (int index = 0; index < employeeAddresses.size(); index++) {
             employeeAddress = employeeAddresses.get(index);
             preparedStatement.setInt(1,employeeId);
             preparedStatement.setString(2,employeeAddress.getDoorNumber());
             preparedStatement.setString(3,employeeAddress.getStreet());
             preparedStatement.setString(4,employeeAddress.getDistrict());
             preparedStatement.setString(5,employeeAddress.getState());
             preparedStatement.setString(6,employeeAddress.getCountry());
             preparedStatement.setString(7,employeeAddress.getAddressType());
             preparedStatement.setInt(8,0);
             preparedStatement.addBatch();
         }
         preparedStatement.executeBatch();
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
    public void updateEmployee(Employee employee) 
            throws SQLException {
        preparedStatement = connection.prepareStatement
                    ("update employee set name = ?, dob = ?, mobile = ?,"
                    + "designation = ?, salary = ? where id=?");  
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setDate(2, employee.getDob());
        preparedStatement.setLong(3, employee.getMobile());
        preparedStatement.setString(4, employee.getDesignation());
        preparedStatement.setDouble(5, employee.getSalary());
        preparedStatement.setInt(6, employee.getId());
        preparedStatement.executeUpdate();
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
    public boolean updateAddress(Address employeeAddress, int addressId)
            throws SQLException {
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
    public Map <Integer, Address> getAddressList(int employeeId)throws SQLException {
       preparedStatement = connection.prepareStatement
                ("select * from address where employee_id = ? and is_deleted = 0"); 
       preparedStatement.setInt(1,employeeId);
       resultSet = preparedStatement.executeQuery();
       Map <Integer, Address> addressList = new HashMap <Integer, Address> ();
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
            addressList.put(resultSet.getInt(1), address);
       }
       return addressList;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteAddress(int addressId)throws SQLException {
            preparedStatement = connection.prepareStatement
                    ("update address set is_deleted = 1 where id = ?");
            preparedStatement.setInt(1, addressId);
            preparedStatement.executeUpdate();
            return true;
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public Map <Integer, Address> getDeletedAddressList(int employeeId)
            throws SQLException {
       preparedStatement = connection.prepareStatement
                ("select * from address where employee_id = ? and is_deleted = 1"); 
       preparedStatement.setInt(1,employeeId);
       resultSet = preparedStatement.executeQuery();
       Map <Integer, Address> addressList = new HashMap <Integer, Address> ();
       while(resultSet.next()) {
            Address address = new Address(resultSet.getInt(1), resultSet.getInt(2),
                         resultSet.getString(3), resultSet.getString(4),
                         resultSet.getString(5),resultSet.getString(6),
                         resultSet.getString(7), resultSet.getString(8));
            addressList.put(resultSet.getInt(1), address);
       }
       return addressList;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean recoverAddress(int addressId) throws SQLException {
            preparedStatement = connection.prepareStatement
                    ("update address set is_deleted = 0 where id = ?"); 
            preparedStatement.setInt(1,addressId);
            preparedStatement.executeUpdate();
            return true;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List <Employee> getDeletedEmployees()throws SQLException {
        List <Employee> deletedEmployees = new ArrayList <Employee> ();
        preparedStatement = connection.prepareStatement
                ("select * from employee where employee.is_deleted = 1");        
	resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Employee employee = createEmployee(resultSet);
            deletedEmployees.add(employee);
        }
        return deletedEmployees;
    } 
}