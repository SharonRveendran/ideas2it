package com.ideas2it.employeemanagement.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;

/**
 * POJO class for employee providing id,
 * name, designation & salary. 
 * @author Sharon
 * @created 13-03-2021
 */
public class Employee {
    private int id;
    private String name;
    private Date dob;
    private long mobile;
    private String designation;
    private double salary;
    private List<Address> employeeAddresses = new ArrayList<Address>();  
    
    public Employee(String name, String designation, double salary,
            int id, long mobile, Date dob, List<Address> employeeAddresses) {
    	this.id = id;
        this.name = name;
        this.dob = dob;
        this.mobile = mobile; 
    	this.designation = designation;
    	this.salary = salary; 
        this.employeeAddresses = employeeAddresses;	
    }
 
    public int getId() {
	return id;
    }
    
    public void setId(int id) {
	this.id = id;
    }
    
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }

    public Date getDob() {
	return dob;
    }

    public void setDob(Date dob) {
	this.dob = dob;
    }

    public long getMobile() {
	return mobile;
    }

    public void setMobile(long mobile) {
	this.mobile = mobile;
    }  
	
    public String getDesignation() {
	return designation;
    }
	
    public void setDesignation(String designation ) {
	this.designation = designation;
    }
		
    public double getSalary() {
	return salary;
    }
	
    public void setSalary(double salary) {
	this.salary = salary;
    } 

    public List<Address> getEmployeeAddresses() {
	return employeeAddresses;
    }
	
    public void setEmployeeAddresses(List<Address> employeeAddresses) {
	this.employeeAddresses = employeeAddresses;
    }
	
    public String toString() {
    	return "\nEmployee Id             =  " + id + "\nEmployee Name           =  "
                + name + "\nEmployee Designation    =  " + designation
    		+ "\nEmployee Salary         =  " + salary
    		+"\nEmployee Mobile         =  " + mobile + "\nEmployee Date of Birth  =  "
    		+ dob + "\n";
    }
}
