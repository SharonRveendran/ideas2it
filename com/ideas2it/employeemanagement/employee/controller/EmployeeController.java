package com.ideas2it.employeemanagement.employee.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;

/**
 * Class for Employee controller
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
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
            double salary,long mobile,Date dob, List<String[]> employeeAddresses) {
        employeeService.createEmployee(name, designation, salary,
                mobile, dob, employeeAddresses);
    }
    
    
 
    
    /**
     * Method to check whether the id is present in collection or not 
     * @param id Employee id
     * @return true if id present in database else return false
     */
    public boolean isIdExist(int id) {
    	return employeeService.isIdExist(id);
    }
    
    /**
     * Method to get the employee details based on employee id
     * @param id Employee id
     * @return Employee details as string
     */
    public String getEmployee(int id) {
    	return employeeService.getEmployee(id);
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
    public int isValidId(String id) {
        return employeeService.isValidId(id);
    }

    /**
     * Method to return all employee details present in collection
     * @return list of all employee delails
     */
    public List<String> getAllEmployeesDetails() {
    	return employeeService.getAllEmployeesDetails();
    }
}
