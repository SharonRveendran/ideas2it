package com.ideas2it.employeemanagement.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * Class for Employee service
 * @author Sharon V
 * @created 13-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
	
    /**
     * {@inheritdoc}
     */
    @Override
    public void createEmployee(String name, String designation, double salary,
            long mobile, Date dob, List<String[]> employeeAddressesDetails)
            throws SQLException {
        Address employeeAddress;
        String addressType;
        List<Address> employeeAddresses = new ArrayList<Address>();
        for (int index = 0; index < employeeAddressesDetails.size(); index++) {
            employeeAddress = new Address(0, 0, employeeAddressesDetails.get(index)[0],
                        employeeAddressesDetails.get(index)[1],
                        employeeAddressesDetails.get(index)[2],
                        employeeAddressesDetails.get(index)[3],
                        employeeAddressesDetails.get(index)[4],                                                 
                        employeeAddressesDetails.get(index)[5]);
            employeeAddresses.add(employeeAddress);
        } 
    	Employee employee = new Employee(name, designation, salary,
                0, mobile, dob, employeeAddresses); 
        employeeDao.insertEmployee(employee);   
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public String getEmployee(int id) throws SQLException {    
        Employee employee = employeeDao.getEmployee(id);
        return getEmployeeDetails(employee);
    }

    /**
     * {@inheritdoc}
     */
    private String getEmployeeDetails(Employee employee) {
        if (null != employee ) {
            int temporaryAddressCount = 1;
            String addressesDetails = employee.toString();
            List<Address> employeeAddresses = employee.getEmployeeAddresses();
            for (int index = 0; index < employeeAddresses.size(); index++) {
                if ("permanent".equals(employeeAddresses.get(index).getAddressType())) {
                    addressesDetails = addressesDetails +
                            "\nPermanent address\n-----------------\n\n";
                }
                if ("temporary".equals(employeeAddresses.get(index).getAddressType())) {
                    addressesDetails = addressesDetails + "\nTemporary address " +
                            temporaryAddressCount + "\n--------------------\n\n";
                    temporaryAddressCount++;
                }    
                addressesDetails = addressesDetails + employeeAddresses.get(index).toString();
            }
        return addressesDetails;
        } else {
            return null;
        }        
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public void updateEmployee(int id, String name, String designation,
            double salary, Date dob, long mobile, String option)
            throws SQLException {
    	employeeDao.updateEmployee(id, name, designation, salary, dob, mobile, option);
    }
   
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) throws SQLException {
        return employeeDao.isIdExist(id);
    }
     
    /**
     * {@inheritdoc}
     */
    @Override
    public void deleteEmployee(int id) throws SQLException {
    	employeeDao.deleteEmployee(id);
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAll() throws SQLException {
        List<String> employeesDetails = new ArrayList<String>();
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeDao.getAllEmployee();
        for(int index = 0; index < employees.size(); index++) {
            employeesDetails.add(getEmployeeDetails(employees.get(index)));
        }
        return employeesDetails;
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
    public void updateAddress(int addressId, String[] addressDetails) 
            throws SQLException {
        Address employeeAddress = new Address(addressId, 0, addressDetails[0],
                addressDetails[1], addressDetails[2], addressDetails[3],
                addressDetails[4], addressDetails[5]);
        employeeDao.updateAddress(employeeAddress);
    }
}