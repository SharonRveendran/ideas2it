package com.ideas2it.employeemanagement.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * Class for Employee controller
 * @author Sharon V
 * @created 18-03-2021
 */
public class EmployeeController {
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    
    /**
     * Method to create employee
     * @param name  Employee name
     * @param designation Employee designation
     * @param salary  Employee salary
     * @param mobile  Employee mobile number
     * @param dob  Employee date of birth
     * @param employeeAddresses list of employee addresses
     * @return  employee id
     */
    public void createEmployee(String name, String designation,
            double salary,long mobile,Date dob, List<String[]> employeeAddresses) 
            throws SQLException {
        employeeService.createEmployee(name, designation, salary,
                mobile, dob, employeeAddresses);
    }
    
    /**
     * Method to get the employee details based on employee id
     * @param id Employee id
     * @return Employee details as string
     */
    public String getEmployee(int id) throws SQLException {
    	return employeeService.getEmployee(id);
    }
    
    /**
     * Methode to update the employee name
     * @param id Employee id
     * @param employeeName Name of employee
     * @return true for successful updation of name else return false
     */
    public void updateName(int id, String employeeName) throws SQLException {
    	employeeService.updateEmployee(id, employeeName,
                null, 0l, null, 0l, "name");
    }
    
    /**
     * Method to check whether the id is present in collection or not 
     * @param id Employee id
     * @return true if id present in database else return false
     */
    public boolean isIdExist(int id) throws SQLException {
    	return employeeService.isIdExist(id);
    }
    
    /**
     * Method to update Employee designation
     * @param id Employee id
     * @param designation Employee Designation   
     */
    public void updateDesignation(int id, String designation) 
            throws SQLException {
    	employeeService.updateEmployee(id, null, designation,
                0l, null, 0l, "designation");
    }
    
    /**
     * Method to update Employee salary
     * @param id Employee id
     * @param employeeSalary Salary of Employee
     */
    public void updateSalary(int id, double employeeSalary)
            throws SQLException {
    	employeeService.updateEmployee(id, null, null,
                employeeSalary, null, 0l, "salary");
    }
    
    /**
     * Method to update Employee date of birth
     * @param id Employee id
     * @param dob Employee date of birth
     */
    public void updateDob(int id, Date dob) throws SQLException{
    	employeeService.updateEmployee(id, null, null, 0l, dob, 0l, "dob");
    }
    
    /**
     * Method to update employee mobile number
     * @param id Employee id
     * @param mobile Employee mobile number
     */
    public void updateMobile(int id, long mobile) throws SQLException {
    	employeeService.updateEmployee(id, null, null,
                0l, null, mobile, "mobile");
    }
    
    /**
     * Method to delete the Employee based on employee id
     * @param id Employee id
     */
    public void deleteEmployee(int id) throws SQLException {
    	employeeService.deleteEmployee(id);
    }

    /**
     * Method to return all employee details present in collection
     * @return list of all employee delails
     */
    public List<String> getAll() throws SQLException {
    	return employeeService.getAll();
    }
    
    /**
     * Method to validate date
     * @param date User given date string
     * @return valid date
     */
    public Date isValidDate(String date) {
    	return employeeService.isValidDate(date);
    }

    /**
     * Method to validate mobile number
     * @param input user given number
     * @return Mobile number
     */
    public long isValidMobile(String input) {
        return employeeService.isValidMobile(input);
    }
    
    /**
     * Method to validate employee salary.
     * @param input user given salary
     * @return valid salary
     */
     public double isValidSalary(String input) {
         return employeeService.isValidSalary(input);
     }
     
    /**
     * This method will validate employee id
     * @param id employee id
     * @return valid employee id
     */
    public int isValidId(String id) throws SQLException {
        return employeeService.isValidId(id);
    }

    /**
     * Methode to update address
     * @param addressId employee address id
     * @param addressDetails array of address details
     * @return true for successfull address upodation else false
     */
    public boolean updateAddress(int addressId, String[] addressDetails)
            throws SQLException {
        return employeeService.updateAddress(addressId, addressDetails);
    }

    /**
     * Methode to recover deleted employee
     * @param id employee id
     * @return recovery status string
     */
    public String recoverEmployee(int id) throws SQLException {
        return employeeService.recoverEmployee(id);
    }
    
    /**
     * Method to get addressList of a employee
     * @param employeeId
     * @return list of employee address strings
     */
    public Map<Integer, String> getAddressList(int employeeId) throws SQLException {
        return employeeService.getAddressList(employeeId);
    }

    /**
     * Method to delete employee address
     * @param addressId
     * @return true for successfull address deletion else false
     */
    public boolean deleteAddress(int addressId)throws SQLException {
        return employeeService.deleteAddress(addressId);
    }
    
    /**
     * Methode to get deleted address list
     * @param employeeId
     * @return map of deleted address with address id 
     */
    public Map<Integer, String> getDeletedAddressList(int employeeId)throws SQLException {
        return employeeService.getDeletedAddressList(employeeId);
    }

    /**
     * Method to recover deleted employee
     * @param addressId
     * @return true for successfull recovery
     */
    public boolean recoverAddress(int addressId) throws SQLException {
        return employeeService.recoverAddress(addressId);
    }

    /**
     * Method to get all deleted employees
     * @return list of  deleted employees 
     */
    public List<String> getDeletedEmployees()throws SQLException {
        return employeeService.getDeletedEmployees();
    }
}
