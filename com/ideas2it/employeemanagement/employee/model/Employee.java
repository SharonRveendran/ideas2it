package com.ideas2it.employeemanagement.employee.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
//import org.hibernate.annotations.Entity;

import com.ideas2it.employeemanagement.employee.model.Address;


/**
 * POJO class for employee providing id,
 * name, designation & salary. 
 * @author Sharon
 * @created 21-03-2021
 */

public class Employee {
    private int id;
    private String name;
    private Date dob;
    private long mobile;
    private String designation;
    private double salary;
    private boolean isDeleted;
    private List<Address> addresses;  
    //private List<Project> projectList;
    
    public Employee() {
    	
    }
    public Employee(String name, String designation, double salary,long mobile, Date dob, List<Address> addresses, boolean isDeleted) {
        this.name = name;
        this.dob = dob;
        this.mobile = mobile; 
    	this.designation = designation;
    	this.salary = salary; 
        this.addresses = addresses;
        this.isDeleted = isDeleted;	
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

    public boolean getIsDeleted() {
	return isDeleted;
    }
    
    public void setIsDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }

    public List<Address> getAddresses() {
	return addresses;
    }
	
    public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
    }

   /* public List<Project> getProjectList() {
	return projectList;
    }
	
    public void setProjectList(List<Project> projectList) {
	this.projectList = projectList;
    }*/
    public String toString() {
    	return "\nEmployee Id             =  " + id + "\nEmployee Name           =  "
                + name + "\nEmployee Designation    =  " + designation
    		+ "\nEmployee Salary         =  " + salary
    		+"\nEmployee Mobile         =  " + mobile + "\nEmployee Date of Birth  =  "
    		+ dob + "\n";
    }
}
