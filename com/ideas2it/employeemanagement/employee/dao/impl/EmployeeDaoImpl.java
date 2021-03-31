
package com.ideas2it.employeemanagement.employee.dao.impl;

import java.sql.Connection;
import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();	
  
    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployee(int id) {
        Connection connection = databaseConnection.getDatabaseConnection();
        Employee employee = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement
                    ("select * from employee left join address on employee.id"
                    + " = address.employee_id where employee.id = ? and employee.is_deleted = 0");
            preparedStatement.setInt(1, id);         
	    resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                employee = createEmployee(resultSet);	                
                List<Address> employeeAddressList = new ArrayList<Address>();
                do {
                    Address employeeAddress = createAddress(resultSet);
                    employeeAddressList.add(employeeAddress);             
                } while(resultSet.next());  
                employee.setEmployeeAddresses(employeeAddressList);          
            }
        } catch(SQLException e) { 
            e.printStackTrace();    
            return null; 
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        return employee; 
    }
   
    /**
     * Methode to create employee object
     * @param resultSet
     * @return employee object
     */ 
    private Employee createEmployee(ResultSet resultSet)throws SQLException {
	Employee employee = null;
            employee = new Employee(resultSet.getString(2),
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
	Address employeeAddress = null;
            employeeAddress = new Address(resultSet.getInt(8), resultSet.getInt(9),
                        resultSet.getString(10), resultSet.getString(11),
                        resultSet.getString(12),resultSet.getString(13),
                        resultSet.getString(14), resultSet.getString(15));
        return employeeAddress;
    }


    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getAllEmployee() {
        Connection connection = databaseConnection.getDatabaseConnection();
        List<Employee> employees = new ArrayList<Employee>();  
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {  
            boolean resultSetEnd = false;
            preparedStatement = connection.prepareStatement
                    ("select * from employee left join address on employee.id"
                    + "= address.employee_id where employee.is_deleted = 0");         
	    resultSet = preparedStatement.executeQuery();	
            if (resultSet.next()) {             			
	        do{ 
                    List<Address> employeeAddresses = new ArrayList<Address>();			
                    int employeeId = resultSet.getInt(1);
                    Employee employee = createEmployee(resultSet);         
                    while(employeeId == resultSet.getInt(1)) {
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
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        return employees;
    }
      
    /** 
     * {@inheritdoc}
     */
    @Override
     public void insertEmployee(Employee employee) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {  
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement
                    ("insert into employee(name, mobile, dob, designation, salary)" 
                    + "values(?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
	    preparedStatement.setString(1, employee.getName());
	    preparedStatement.setLong(2, employee.getMobile());
            preparedStatement.setDate(3, employee.getDob());
            preparedStatement.setString(4, employee.getDesignation());
	    preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int employeeId = resultSet.getInt(1);
            List<Address> employeeAddresses = employee.getEmployeeAddresses();
            if (insertAddress(employeeAddresses, connection, employeeId)) {
                connection.commit(); 
            } else {
                connection.rollback();
            }  
        } catch(SQLException e) {
             e.printStackTrace();
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 	 
    }

    /**
     * Method to insert address
     * @param employeeAddresses list of  employee  address objects
     * @param connection connection object
     * @param employeeId employee id for address table.
     * @return true for successfull address insertion else false.
     */
    private boolean insertAddress(List<Address> employeeAddresses,
            Connection connection, int employeeId) {
        PreparedStatement preparedStatement = null;
        try { 
            connection.setAutoCommit(false); 
            preparedStatement = connection.prepareStatement
                    ("insert into address(employee_id, door_number, street, district,"
                    + "state, country, type) values(?, ?, ?, ?, ?, ?, ?)");	         
            for (Address employeeAddress : employeeAddresses) {
                preparedStatement.setInt(1,employeeId);
                preparedStatement.setString(2,employeeAddress.getDoorNumber());
                preparedStatement.setString(3,employeeAddress.getStreet());
                preparedStatement.setString(4,employeeAddress.getDistrict());
                preparedStatement.setString(5,employeeAddress.getState());
                preparedStatement.setString(6,employeeAddress.getCountry());
                preparedStatement.setString(7,employeeAddress.getAddressType());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    /**
     * {@inheritdoc}
     */
    @Override
     public boolean deleteEmployee(int id) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        boolean deleteStatus = false;
        try {  
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement
                    ("update employee set is_deleted ='1' where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement
                    ("update address set is_deleted ='1' where employee_id=?");
            preparedStatement.setInt(1, id);
            int rowCount = preparedStatement.executeUpdate();
            if (0 != rowCount) {
                preparedStatement = connection.prepareStatement
                    ("delete from employee_project where employee_id=?");
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                deleteStatus = true;
            } else {
                connection.rollback();
            }
        } catch(SQLException e) {
             e.printStackTrace();
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        return deleteStatus;
     }   
         
    /**
     * {@inheritdoc}
     */
    @Override
    public void updateEmployee(Employee employee) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        try {  
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
        } catch(SQLException e) {
             e.printStackTrace();
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }      
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {  
            preparedStatement = connection.prepareStatement
                    ("select id from employee where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            boolean isIdExist = resultSet.next();
            return isIdExist;  
        } catch(SQLException e) {
            e.printStackTrace();
            return false; 
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateAddress(Address employeeAddress, int addressId) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        try {  
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
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean recoverEmployee(int id) {
       Connection connection = databaseConnection.getDatabaseConnection();
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       boolean recoverStatus = false;
       try {  
           preparedStatement = connection.prepareStatement
                   ("select * from employee where id = ? and is_deleted = 1"); 
           preparedStatement.setInt(1,id);
           resultSet = preparedStatement.executeQuery();
           if (resultSet.next()) {
               preparedStatement = connection.prepareStatement
                      ("update employee set is_deleted = 0 where id = ?"); 
               preparedStatement.setInt(1,id);
               preparedStatement.executeUpdate();
               preparedStatement = connection.prepareStatement
                      ("update address set is_deleted = 0 where employee_id = ?");
               preparedStatement.setInt(1,id);               
               recoverStatus = (0 != preparedStatement.executeUpdate()); 
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return recoverStatus; 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Map <Integer, Address> getAddressList(int employeeId) {
       Connection connection = databaseConnection.getDatabaseConnection();
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       try {  
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
       } catch(SQLException e) {
           e.printStackTrace();
           return null;
       } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteAddress(int addressId) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        try {  
            preparedStatement = connection.prepareStatement
                    ("update address set is_deleted = 1 where id = ?");
            preparedStatement.setInt(1, addressId);
            preparedStatement.executeUpdate(); 
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public Map <Integer, Address> getDeletedAddressList(int employeeId) {
       Connection connection = databaseConnection.getDatabaseConnection();
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       try {  
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
       } catch(SQLException e) {
           e.printStackTrace();
           return null;
       } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void recoverAddress(int addressId) {
       Connection connection = databaseConnection.getDatabaseConnection();
       PreparedStatement preparedStatement = null;
       try {  
           preparedStatement = connection.prepareStatement
                   ("update address set is_deleted = 0 where id = ?"); 
           preparedStatement.setInt(1,addressId);
           preparedStatement.executeUpdate();
       } catch(SQLException e) {
           e.printStackTrace();
       } finally {
            try{
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public List <Employee> getDeletedEmployees() {
       Connection connection = databaseConnection.getDatabaseConnection();
       PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       try {  
           List <Employee> deletedEmployees = new ArrayList <Employee> ();
           preparedStatement = connection.prepareStatement
                   ("select * from employee where employee.is_deleted = 1");        
	   resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               Employee employee = createEmployee(resultSet);
               deletedEmployees.add(employee);
           }
           return deletedEmployees;    
        } catch(SQLException e) {
           e.printStackTrace();
           return null; 
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public boolean assignProjects(Employee employee) {
        int employeeId = employee.getId();
        List<Project> projects = employee.getProjectList();
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        boolean assignStatus = false;
        try{
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement
                    ("insert into employee_project values (?, ?)"); 
            for (Project project : projects) {
                preparedStatement.setInt(1, employeeId);
                preparedStatement.setInt(2, project.getId());
                preparedStatement.addBatch();
            }
            assignStatus = (0 != preparedStatement.executeBatch().length);  
            connection.commit();
        }catch (BatchUpdateException e1) {
            assignStatus = false; 
            try {
                connection.rollback();
            } catch (SQLException e4) {
                e4.printStackTrace();
            }   
        }         
        catch (SQLException e2) {
            e2.printStackTrace();   
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
        return assignStatus;     
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public List<Integer> getProjectIdList(int employeeId) {
        List<Integer> projectIdList = new ArrayList<Integer>();
        Connection connection = databaseConnection.getDatabaseConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
       try {  
           preparedStatement = connection.prepareStatement
                   ("select project_id from employee_project where employee_id = ?");        
	   preparedStatement.setInt(1, employeeId);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               projectIdList.add(resultSet.getInt(1));
           }
        } catch(SQLException e) {
           e.printStackTrace(); 
        } finally {
            try{
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        return projectIdList;
    }

    /**
     * {@inheritdoc} 
     */
    @Override
    public boolean removeProject(Employee employee) {
        Connection connection = databaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = null;
        boolean removeStatus = false;
        try{
            preparedStatement = connection.prepareStatement
                    ("delete from employee_project where employee_id = ? and project_id = ?");
            preparedStatement.setInt(1, employee.getId()); 
            preparedStatement.setInt(2, employee.getProjectList().get(0).getId());
            removeStatus = (0 != preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();   
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return removeStatus;
    }
}