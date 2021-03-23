package com.ideas2it.employeemanagement.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.controller.EmployeeController;

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
        System.out.println("\n\n........... Welcome to employee management system ...........\n");	
        String option;
        do {
            System.out.println(Constants.CRUD_OPTION); 
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
   	            recoverEmployee();
   	            break;
                case "7":
   	            System.out.println(Constants.END_MESSAGE);
   	            break;
   	        default:
   	            System.out.println(Constants.INVALID_DETAILS);
   	    } 	 
	} while(!"7".equals(option));   
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
    
    /**
     * Method to display employee based on employee id
     */
    private void displayEmployee() {
        int id = getAndValidateId();
    	String employeeDetails = employeeController.getEmployee(id);
    	if(null == employeeDetails) {
    	    System.out.println(Constants.NO_EMPLOYEE);
    	} else {
    	    System.out.println(employeeDetails);
    	}
    }

    /**
     * Method to update the employee details
     */
    private void updateEmployee() {
    	int id = getAndValidateId();
    	if (employeeController.isIdExist(id)) {	
            String option;
            do {
                System.out.println(Constants.UPDATE_OPTION);
                option = scanner.nextLine();
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
    	    	       updateAddress(id);
                       break;
                   default:
                       option = null;
                       System.out.println(Constants.INVALID_DETAILS);
    	        }
            } while (null == option);
    	} else {
    	    System.out.println(Constants.NO_EMPLOYEE);
    	}		
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
     * This method will update the employee name
     * @param id the employee id
     */
    private void updateName(int id) {
    	System.out.println(Constants.GET_NAME_MESSAGE);
        String employeeName = scanner.nextLine();
        if(employeeController.isIdExist(id)) {
            employeeController.updateName(id, employeeName);
            System.out.println(Constants.SUCCESSFULL_UPDATION);
        } else {
            System.out.println(Constants.NO_EMPLOYEE);
        }  
    }
    
    /**
     * This method will update the employee Designation
     * @param id the employee id
     */
    private void updateDesignation(int id) {
    	System.out.println(Constants.GET_DESIGNATION_MESSAGE);
        String designation = scanner.nextLine();
        employeeController.updateDesignation(id, designation);
        System.out.println(Constants.SUCCESSFULL_UPDATION);
    }
    
    /**
     * This method will update the employee Salary
     * @param id the employee id
     */
    private void updateSalary(int id) {
    	double employeeSalary = getAndValidateSalary();
        employeeController.updateSalary(id, employeeSalary);
        System.out.println(Constants.SUCCESSFULL_UPDATION);
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
     * This method will update the employee Date of birth
     * @param id the employee id
     */
    private void updateDob(int id) {
    	System.out.println(Constants.GET_DATE_MESSAGE);
    	String date;
    	do {
            date = scanner.nextLine();
	    if (null == employeeController.isValidDate(date)) { 
                System.out.println(Constants.INVALID_DETAILS);
	        date = "invalidDate";
            }
        } while("invalidDate".equals(date));
	employeeController.updateDob(id, employeeController.isValidDate(date));
	System.out.println(Constants.SUCCESSFULL_UPDATION);
    }
    
    /**
     * This method will update the employee Mobile number
     * @param id the employee id
     */
    private void updateMobile(int id) {
    	String input;
    	long mobile = getAndValidateMobile();
        employeeController.updateMobile(id, mobile);
        System.out.println(Constants.SUCCESSFULL_UPDATION);
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
     * Method to delete employee based on employee id
     */
    private void deleteEmployee() { 
    	int id = getAndValidateId() ;  
    	if (employeeController.isIdExist(id)) {
            System.out.println("Select your option\n1 : Whole employee\n2 : Address only");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    employeeController.deleteEmployee(id);
                    System.out.println(Constants.SUCCESSFULL_DELETION);
                    break;
                case "2":
                    deleteAddress(id);
                    break;
                default:
                    System.out.println(Constants.INVALID_DETAILS);
            }
         } else {
             System.out.println(Constants.NO_EMPLOYEE);
         }
     }

    /**
     * Method to delete address
     */
    private void deleteAddress(int employeeId) {
        Map <Integer, String> addressList = employeeController.getAddressList(employeeId);
        if (0 != addressList.size()) {
            List <Integer> employeeIdList = new ArrayList <Integer> (addressList.keySet());
            System.out.println("Which address you want to delete ?");
            int index;
            for(index = 0; index < employeeIdList.size(); index++) {
                System.out.println((index + 1) + " :\n    "
                        + addressList.get(employeeIdList.get(index)));
            }
            int input;
            try {
                input =Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                input = index +1;
            }
            if (input <= index) {
                int addressId = employeeIdList.get(input - 1);
                employeeController.deleteAddress(addressId);
                System.out.println("\nAddress deleted successfully");
             } else {
                 System.out.println(Constants.INVALID_DETAILS);
             }
         } else {
             System.out.println("No address exist for given employee");
         } 
    }

    /**
     * Method to display all employee details present in collection
     */
    private void displayAll() {
        List<String> employeesDetails = employeeController.getAll();
        if (0 == employeesDetails.size()) {
            System.out.println(Constants.NO_EMPLOYEE);
        } else {
            for(String employeeDetails : employeesDetails) {
                System.out.println(employeeDetails);
            }
        }
    }

    /**
     * Methode to update employee address
     */
    private void updateAddress(int employeeId) {
        Map<Integer, String> addressList = employeeController.getAddressList(employeeId);
        List<Integer> employeeIdList = new ArrayList<Integer>(addressList.keySet());
        System.out.println("Which address you want to update ?");
        if (0 != addressList.size()) { 
            int input; 
            do {
                int index;              
                for(index = 0; index < employeeIdList.size(); index++) {
                    System.out.println((index + 1) + " :\n    "
                            + addressList.get(employeeIdList.get(index)));
                }
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch(NumberFormatException e) {
                    input = index + 1;
                }
                if (input <= index) {
                    int addressId = employeeIdList.get(input - 1);              
                    String addressDetails[] = getAddress(null);
                    employeeController.updateAddress(addressId, addressDetails);
                    System.out.println("Address updated successfully...");
                } else {
                    System.out.println(Constants.INVALID_DETAILS);
                    input = 0;
                }
            } while (0 == input); 
        } else {
            System.out.println("No address present for the given employee");
        }
    }

    /**
     * Method to recover a deleted employee
     */
    private void recoverEmployee() {
        System.out.println("Select your option\n1 : whole employee\n2 : Address only");
        String input = scanner.nextLine();
        int employeeId; 
        switch (input) {
            case "1":
                List <String> deletedEmployees = employeeController.getDeletedEmployees();
                System.out.println("Deleted employees are given below");
                for(int index = 0; index < deletedEmployees.size(); index++) {
                    System.out.println((index + 1) + " :\n    " + deletedEmployees.get(index));
                }
                employeeId = getAndValidateId();
                String recoveryStatus = employeeController.recoverEmployee(employeeId);
                if (null == recoveryStatus) {
                    System.out.println(Constants.INVALID_DETAILS);
                } else {
                    System.out.println(recoveryStatus); 
                }   
                break;
            case "2":
                employeeId = getAndValidateId();
                recoverAddress(employeeId);
                break;
            default:
                 System.out.println(Constants.INVALID_DETAILS);
        }
    }
    
    /**
     * Methode to recover employee address
     */
    private void recoverAddress(int employeeId) {
        int input;
        Map <Integer, String>  deletedAddressList = employeeController
                .getDeletedAddressList(employeeId); 
        List <Integer> deletedAddressIdList 
                = new ArrayList<Integer> (deletedAddressList.keySet());   
        if (0 != deletedAddressList.size()) {
            int index;
            System.out.println("Which address you want to recover ?");
            do {
                for(index = 0; index < deletedAddressIdList.size(); index++) {
                    System.out.println((index + 1) + " :\n    "
                            + deletedAddressList.get(deletedAddressIdList.get(index)));
                }
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    input = index +1;
                }
                if (input <= index) {
                    int addressId = deletedAddressIdList.get(input - 1);
                    employeeController.recoverAddress(addressId);
                    System.out.println("Address recoverd successfully");                   
                } else {
                    input = 0;
                    System.out.println(Constants.INVALID_DETAILS);
                } 
            } while (0 == input);        
        } else {
            System.out.println(Constants.INVALID_DETAILS);
        }
    }             
}