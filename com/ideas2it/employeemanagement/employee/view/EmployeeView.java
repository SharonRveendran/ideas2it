package com.ideas2it.employeemanagement.employee.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.employee.controller.EmployeeController;

/**
 * Class for Employee view
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    
    /**
     * This method will collect the input option from user
     * and perfom CRUD operation
     */
    public void start() {	
        String option;
        do {
            System.out.println(Constants.CRUD_OPTION); 
            option = scanner.nextLine();
            switch (option) {
   	        case "1":
   	            createEmployee();
   		    break;
   	        case "2":
   	            //displayEmployee();
   		    break;	
   	        case "3":
   	           // updateEmployee();
   		    break;
   	        case "4":
   	           // deleteEmployee();
   		    break;
   	        case "5":
   	           // displayAll();
   	            break;
   	        case "6":
   	           // recoverEmployee();
   	            break;
                case "7":
   	          //  assignProjects();
   	            break;
                case "8":
   	           // displayAssignedProjects();
   	            break;
                case "9":
   	           // removeProject();
   	            break;
                case "10":
   	            System.out.println(Constants.END_MESSAGE);
   	            break;
   	        default:
   	            System.out.println(Constants.INVALID_DETAILS);
   	    } 	 
	} while(!"10".equals(option));   
    }
    
    /**
     * Method to create employee
     */
    private void createEmployee() {
        int permanentAddressCount = 0;
    	System.out.println(Constants.GET_NAME_MESSAGE);
    	String name = scanner.nextLine();
    	System.out.println(Constants.GET_DESIGNATION_MESSAGE);
    	String designation = scanner.nextLine();
    	double salary = getAndValidateSalary();
    	long mobile = getAndValidateMobile();
    	Date dob = getDob();
        employeeController.createEmployee(name, designation, salary,
                mobile, dob, createAddresses());
        System.out.println(Constants.SUCCESSFULL_CREATION);
    }

    /**
     * Methode to create employee addresses
     */
    private List<String[]> createAddresses() {
        String option = "";
        int permanentAddressCount = 0;
        List<String[]> employeeAddresses = new ArrayList<String[]>();
        do {
            System.out.println(Constants.GET_ADDRESS_TYPE_MESSAGE);
            String addressChoice = scanner.nextLine();
            switch (addressChoice) {
                case "1":
                    permanentAddressCount++;
                    if (1 == permanentAddressCount) {
                        String addressDetails[] = getAddress(addressChoice);
    	                employeeAddresses.add(addressDetails);
                        System.out.println("Press 1 to add more address\n"
                                + "Press other key to continue");
                        option = scanner.nextLine();
                    } else {
                        System.out.println("You already have a permanent address");
                    }
                    break;
                case "2":
                    String addressDetails[] = getAddress(addressChoice);
    	            employeeAddresses.add(addressDetails);
                    System.out.println("Press 1 to add more address\n"
                             + "Press other key to continue");
                    option = scanner.nextLine();
                    break;
                default:
   	            System.out.println(Constants.INVALID_DETAILS);
                    option = "1";
            }
        } while ("1".equals(option));
        return employeeAddresses;
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
     * Method to get and validate the employee id
     * @return employee id
     */
    private int getAndValidateId() {
        System.out.println(Constants.GET_ID_MESSAGE);
        String input;
        int id = 0;
        do {
            input = scanner.nextLine();
            id = employeeController.isValidId(input); 
            if (0 == id) {
                System.out.println(Constants.INVALID_DETAILS);
            }     
    	} while(0 == id);
    	return id;
    }
	
    
    /**
     * Method to get and validate Employee salary
     * @return valid salary
     */
    private double getAndValidateSalary() {
    	System.out.println(Constants.GET_SALARY_MESSAGE);
    	String input;
    	do {  
            input = scanner.nextLine();	    
            if(0 == employeeController.isValidSalary(input)) {
                System.out.println(Constants.INVALID_DETAILS);
                input = null;          
            } 
        } while (null == input);
    	return employeeController.isValidSalary(input);
    }
    
    
    
    /**
     * Method to get and validate Employee mobile number
     * @return valid mobile number
     */
    private long getAndValidateMobile() {
        String input;
    	long mobile = 1;
    	System.out.println(Constants.GET_MOBILE_MESSAGE);
        do {  	    
            input = scanner.nextLine();
      	    if (0 == employeeController.isValidMobile(input)) {
                System.out.println(Constants.INVALID_DETAILS);
      	        mobile = 0;
      	    } else {
                mobile = employeeController.isValidMobile(input);
            }
         } while (0 == mobile);
         return mobile;      
    }
    
    
     /**
     * Method to get the date of birth of employee
     * @return Employee date of birth
     */
    private Date getDob() {
        String date;
        System.out.println(Constants.GET_DATE_MESSAGE);      
        do {
            date = scanner.nextLine();
	    if (null == employeeController.isValidDate(date)) { 
	        System.out.println(Constants.INVALID_DETAILS);
	        date = "invalidDate";
            }
        } while("invalidDate".equals(date));
        return employeeController.isValidDate(date);
    }

    

    
    
    
    

   

  
}