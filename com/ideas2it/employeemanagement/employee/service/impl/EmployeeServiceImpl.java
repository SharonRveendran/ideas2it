package com.ideas2it.employeemanagement.employee.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
//import com.ideas2it.employeemanagement.project.model.Project;
//import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
//import com.ideas2it.employeemanagement.project.service.ProjectService;


/**
 * Class for Employee service
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
    /**
     * {@inheritdoc}
     */
    @Override
    public void createEmployee(String name, String designation, double salary,
            long mobile, Date dob, List<String[]> employeeAddressList) {
        Address employeeAddress;
        String addressType;
        List<Address> employeeAddresses = new ArrayList<Address>();
        for (String employeeAddressArray[] : employeeAddressList) {
            employeeAddress = new Address(employeeAddressArray[0],
                        employeeAddressArray[1], employeeAddressArray[2],
                        employeeAddressArray[3], employeeAddressArray[4],                                                 
                        employeeAddressArray[5],false);
            employeeAddresses.add(employeeAddress);
        } 
    	Employee employee = new Employee(name, designation, salary, mobile, dob, employeeAddresses,false); 
        employeeDao.insertEmployee(employee);   
    }

    

    

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) {
        return employeeDao.isIdExist(id);
    }
     
   
 
    
    /**
     * {@inheritdoc}
     */
    @Override
    public Date isValidDate(String date) {
    	Date dob = null;
        try {
	    dob = Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
        return dob;
    }
 
    /**
     * {@inheritdoc}
     */
    @Override
    public long isValidMobile(String input) {
        long mobile;
        if (Pattern.matches("[7-9][0-9]{9}", input)) {
       	    try {
                mobile = Long.parseLong(input); 
            } catch (NumberFormatException e) {
                return 0;
            }
      	} else {
      	   return 0;
      	}
        return mobile;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public double isValidSalary(String input) {
        double employeeSalary;
        try {
            employeeSalary = Double.parseDouble(input);          
        } catch (NumberFormatException e) {
            return 0;
        }
        return employeeSalary;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int isValidId(String id) {
        int employeeId;
        try {
 	    employeeId = Integer.parseInt(id); 
    	} catch (NumberFormatException e) {
            return 0;
 	}
        return employeeId;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public String getEmployee(int id) {    
        Employee employee = employeeDao.getEmployee(id);
        return getEmployeeDetails(employee);
    }
   
    /**
     * @param employee employee object
     */
    private String getEmployeeDetails(Employee employee) {
        if (null != employee ) {
            int temporaryAddressCount = 1;
            String employeeDetails = employee.toString();
            List<Address> employeeAddresses = employee.getAddresses();
            
                for (int index = 0; index < employeeAddresses.size(); index++) {
                    if ("permanent".equals(employeeAddresses.get(index).getAddressType())) {
                        employeeDetails = employeeDetails +
                                "\nPermanent address\n-----------------\n\n";
                    }
                    if ("temporary".equals(employeeAddresses.get(index).getAddressType())) {
                        employeeDetails = employeeDetails + "\nTemporary address " +
                                temporaryAddressCount + "\n--------------------\n\n";
                        temporaryAddressCount++;
                    } 
                    if (null != employeeAddresses.get(index).getDoorNumber()) { 
                        employeeDetails = employeeDetails + (employeeAddresses.get(index)).toString();
                    } 
               }
            
        return employeeDetails;
        } else {
            return null;
        }        
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAllEmployeesDetails() {
        List<String> employeesDetails = new ArrayList<String>();
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeDao.getAllEmployee();
        for(int index = 0; index < employees.size(); index++) {
            employeesDetails.add(getEmployeeDetails(employees.get(index)));
        }
        return employeesDetails;
    }

   
  
    

   
}
