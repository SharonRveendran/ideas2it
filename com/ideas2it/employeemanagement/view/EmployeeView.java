package com.ideas2it.employeemanagement.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.controller.EmployeeController;

/**
 * Class for Employee view
 * @author Sharon V
 * @created 13-03-2021
 */
public class EmployeeView {
    Constants constants = new Constants();
    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
    
    /**
     * This method will collect the input option from user
     * and perfom CRUD operation
     */
    public void getInput() throws SQLException {
	String option = "0";
        do {
            System.out.println(constants.crudOption); 
            option = scanner.nextLine();
            switch (option) {
   	        case "1":
   	            createEmployee();
   		    break;
   	        case "2":
   	            displayEmployee();
   		    break;	
   	        case "3":
   	            updateEmployee();
   		    break;
   	        case "4":
   	            deleteEmployee();
   		    break;
   	        case "5":
   	            displayAll();
   	            break;
   	        case "6":
   	            System.out.println(constants.endMassege);
   	            break;
   	        default:
   	            System.out.println(constants.invalidDetails);
   	    } 	 
	} while(!"6".equals(option));   
    }
    
    /**
     * Method to create employee
     */
    private void createEmployee() throws SQLException {
        List<String[]> employeeAddresses = new ArrayList<String[]>();
        int permanentAddressCount = 0;
        String option = "1";
    	System.out.println(constants.getNameMessage);
    	String name = scanner.nextLine();
    	System.out.println(constants.getDesignationMessage);
    	String designation = scanner.nextLine();
    	double salary = getAndValidateSalary();
    	long mobile = getAndValidateMobile();
    	Date dob = getDob();
        do {
            System.out.println("\nSelect type of address\n1 : Permanent\n2 : Temporary");
            String addressChoice = scanner.nextLine();
            if ("1".equals(addressChoice)) {
                permanentAddressCount++;
            }  
            if (1 == permanentAddressCount||"2".equals(addressChoice)) {
                String addressDetails[] = getAddress(addressChoice);
    	        employeeAddresses.add(addressDetails);
                System.out.println("Press 1 to add more address"
                         + "\nPress other key to continue");
                option = scanner.nextLine();
            } else {
                System.out.println("You already have a permanent address");
            }
        } while("1".equals(option));
        employeeController.createEmployee(name, designation, salary,
                mobile, dob, employeeAddresses);
        System.out.println(constants.successfullCreation);
    }
     
    /**
     * Method to get Address details
     * @param option user given option for address type
     * @return array of employee address details
     */
    private String[] getAddress(String option) { 
        String addressDetails[] = new String[6];    
        System.out.println("Enter door number");
        addressDetails[0] = scanner.nextLine();
        System.out.println("Enter street");
        addressDetails[1] = scanner.nextLine();
        System.out.println("Enter District");
        addressDetails[2] = scanner.nextLine();
        System.out.println("Enter State");
        addressDetails[3] = scanner.nextLine();
        System.out.println("Enter Country");
        addressDetails[4] = scanner.nextLine();
        if ("1".equals(option)) {
            addressDetails[5] = "permanent";
        } else {
            addressDetails[5] = "temporary";
        }
        return addressDetails;
    }

    /**
     * Method to get the date of birth of employee
     * @return Employee date of birth
     */
    private Date getDob() {
        String date;
        System.out.println(constants.getDateMessage);      
        do {
            date = scanner.nextLine();
	    if (null == employeeController.isValidDate(date)) { 
	        System.out.println(constants.invalidDetails);
	        date = "invalidDate";
            }
        } while("invalidDate".equals(date));
        return employeeController.isValidDate(date);
    }
    
    /**
     * Method to display employee based on employee id
     */
    private void displayEmployee() throws SQLException{
        int id = getAndValidateId();
    	String employeeDetails = employeeController.getEmployee(id);
    	if(null == employeeDetails) {
    	    System.out.println(constants.noEmployee);
    	} else {
    	    System.out.println(employeeDetails);
    	}
    }
    /**
     * Method to update the employee details
     */
    private void updateEmployee() throws SQLException {
    	int id = getAndValidateId();
    	if (employeeController.isIdExist(id)) {	
            System.out.println(constants.updateOption);
            String option = scanner.nextLine();
    	    switch (option) {
    	        case "1":
    	    	    updateName(id);
    	    	    break;
    	    	case "2":
    	    	    updateDesignation(id);
    	    	    break;
    	    	case "3":
    	    	    updateSalary(id);
    	    	    break;
    	    	case "4":
    	    	    updateDob(id);
    	    	    break;
    	    	case "5":
    	    	   updateMobile(id);
                   break;
                case "6":
    	    	   updateAddress();
                   break;
                default:
                   System.out.println(constants.invalidDetails);
    	    }
    	} else {
    	    System.out.println(constants.noEmployee);
    	}		
    }

    /**
     * Method to get and validate the employee id
     * @return employee id
     */
    private int getAndValidateId() throws SQLException {
        System.out.println(constants.getIdMessage);
        String input;
        int id = 0;
        do {
            input = scanner.nextLine();
            id = employeeController.isValidId(input); 
            if (0 == id) {
                System.out.println(constants.invalidDetails);
            }     
    	} while(0 == id);
    	return id;
    }
	
    /**
     * This method will update the employee name
     * @param id the employee id
     */
    private void updateName(int id) throws SQLException {
    	System.out.println(constants.getNameMessage);
        String employeeName = scanner.nextLine();
        if(employeeController.isIdExist(id)) {
            employeeController.updateName(id, employeeName);
            System.out.println(constants.successfullUpdation);
        } else {
            System.out.println(constants.noEmployee);
        }  
    }
    
    /**
     * This method will update the employee Designation
     * @param id the employee id
     */
    private void updateDesignation(int id) throws SQLException {
    	System.out.println(constants.getDesignationMessage);
        String designation = scanner.nextLine();
        employeeController.updateDesignation(id, designation);
        System.out.println(constants.successfullUpdation);
    }
    
    /**
     * This method will update the employee Salary
     * @param id the employee id
     */
    private void updateSalary(int id) throws SQLException {
    	double employeeSalary = getAndValidateSalary();
        employeeController.updateSalary(id, employeeSalary);
        System.out.println(constants.successfullUpdation);
    }
    
    /**
     * Method to get and validate Employee salary
     * @return valid salary
     */
    private double getAndValidateSalary() {
    	System.out.println(constants.getSalaryMessage);
    	String input;
    	do {  
            input = scanner.nextLine();	    
            if(0 == employeeController.isValidSalary(input)) {
                System.out.println(constants.invalidDetails);
                input = null;          
            } 
        } while (null == input);
    	return employeeController.isValidSalary(input);
    }
    
    /**
     * This method will update the employee Date of birth
     * @param id the employee id
     */
    private void updateDob(int id) throws SQLException {
    	System.out.println(constants.getDateMessage);
    	String date;
    	do {
            date = scanner.nextLine();
	    if (null == employeeController.isValidDate(date)) { 
                System.out.println(constants.invalidDetails);
	        date = "invalidDate";
            }
        } while("invalidDate".equals(date));
	employeeController.updateDob(id, employeeController.isValidDate(date));
	System.out.println(constants.successfullUpdation);
    }
    
    /**
     * This method will update the employee Mobile number
     * @param id the employee id
     */
    private void updateMobile(int id) throws SQLException {
    	String input;
    	long mobile = getAndValidateMobile();
        employeeController.updateMobile(id, mobile);
        System.out.println(constants.successfullUpdation);
    }
    
    /**
     * Method to get and validate Employee mobile number
     * @return valid mobile number
     */
    private long getAndValidateMobile() {
        String input;
    	long mobile = 1;
    	System.out.println(constants.getMobileMessage);
        do {  	    
            input = scanner.nextLine();
      	    if (0 == employeeController.isValidMobile(input)) {
                System.out.println(constants.invalidDetails);
      	        mobile = 0;
      	    } else {
                mobile = employeeController.isValidMobile(input);
            }
         } while (0 == mobile);
         return mobile;      
    }
    
    /**
     * Method to delete employee based on employee id
     */
    private void deleteEmployee() throws SQLException { 
    	int id = getAndValidateId() ;
        if (0 == id) {
            System.out.println(constants.invalidDetails);  
    	} else if (employeeController.isIdExist(id)) {
            employeeController.deleteEmployee(id);
            System.out.println(constants.successfullDeletion);
    	} else {
    		System.out.println(constants.noEmployee);
    	}
    }
    
    /**
     * Method to display all employee details present in collection
     */
    private void displayAll() throws SQLException {
        List<String> employeesDetails = employeeController.getAll();
        if (0 == employeesDetails.size()) {
            System.out.println(constants.noEmployee);
        } else {
            for(String employeeDetails : employeesDetails) {
                System.out.println(employeeDetails);
            }
        }
    }
    /**
     * Methode to update employee address
     */
    private void updateAddress() throws SQLException {
        String input;
        int addressId;
        System.out.println("\nSelect address type\n1 : Permanant\n2 : Temporary");
        String option = scanner.nextLine();
        System.out.println("\nEnter your address id");       
        do {
            input = scanner.nextLine();
            addressId = employeeController.isValidId(input); 
            if (0 == addressId) {
                System.out.println(constants.invalidDetails);
            }     
    	} while(0 == addressId);
        String addressDetails[] = getAddress(option);
        employeeController.updateAddress(addressId, addressDetails);
        System.out.println(constants.successfullUpdation);
    }		
}
