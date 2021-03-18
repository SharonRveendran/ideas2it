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
 * @created 18-03-2021
 */
public class EmployeeView {
    Constants constants = new Constants();
    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
    
    /**
     * This method will collect the input option from user
     * and perfom CRUD operation
     */
    public void start() throws SQLException {	
        String option;
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
   	            recoverEmployee();
   	            break;
                case "7":
   	            System.out.println(constants.endMassege);
   	            break;
   	        default:
   	            System.out.println(constants.invalidDetails);
   	    } 	 
	} while(!"7".equals(option));   
    }
    
    /**
     * Method to create employee
     */
    private void createEmployee() throws SQLException {
        int permanentAddressCount = 0;
    	System.out.println(constants.getNameMessage);
    	String name = scanner.nextLine();
    	System.out.println(constants.getDesignationMessage);
    	String designation = scanner.nextLine();
    	double salary = getAndValidateSalary();
    	long mobile = getAndValidateMobile();
    	Date dob = getDob();
        employeeController.createEmployee(name, designation, salary,
                mobile, dob, createAddresses());
        System.out.println(constants.successfullCreation);
    }

    /**
     * Methode to create employee addresses
     */
    private List<String[]> createAddresses() {
        String option = "";
        int permanentAddressCount = 0;
        List<String[]> employeeAddresses = new ArrayList<String[]>();
        do {
            System.out.println(constants.addressType);
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
   	            System.out.println(constants.invalidDetails);
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
            String option;
            do {
                System.out.println(constants.updateOption);
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
                       System.out.println(constants.invalidDetails);
    	        }
            } while (null == option);
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
    	if (employeeController.isIdExist(id)) {
            System.out.println("Select your option\n1 : Whole employee\n2 : Address only");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    employeeController.deleteEmployee(id);
                    System.out.println(constants.successfullDeletion);
                    break;
                case "2":
                    deleteAddress(id);
                    break;
                default:
                    System.out.println(constants.invalidDetails);
            }
         } else {
             System.out.println(constants.noEmployee);
         }
     }

    /**
     * Method to delete address
     */
    private void deleteAddress(int employeeId)throws SQLException {
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
                if (employeeController.deleteAddress(addressId)) {
                    System.out.println("\nAddress deleted successfully");
                } else { 
                    System.out.println(constants.invalidDetails);
                }
             } else {
                 System.out.println(constants.invalidDetails);
             }
         } else {
             System.out.println("No address exist for given employee");
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
    private void updateAddress(int employeeId) throws SQLException {
        Map<Integer, String> addressList = employeeController.getAddressList(employeeId);
        List<Integer> employeeIdList = new ArrayList<Integer>(addressList.keySet());
        System.out.println("Which address you want to update ?");
        if (0 != addressList.size()) {  
            int index;              
            for(index = 0; index < employeeIdList.size(); index++) {
                System.out.println((index + 1) + " :\n    "
                        + addressList.get(employeeIdList.get(index)));
            }
            int input;
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                input = index + 1;
            }
            if (input < index) {
                int addressId = employeeIdList.get(input - 1);              
                String addressDetails[] = getAddress(null);
                if (employeeController.updateAddress(addressId, addressDetails)) {
                    System.out.println("Address updated successfully...");
                } else {
                    System.out.println(constants.invalidDetails);
                }
            } else {
                System.out.println(constants.invalidDetails);
            }  
        } else {
            System.out.println(constants.invalidDetails);
        }
    }

    /**
     * Method to recover a deleted employee
     */
    private void recoverEmployee() throws SQLException{
        System.out.println("Select your option\n1 : whole employee\n2 : Address only");
        String input1 = scanner.nextLine();
        int employeeId; 
        switch (input1) {
            case "1":
                List <String> deletedEmployees = employeeController.getDeletedEmployees();
                System.out.println("Deleted employees are given below");
                for(int index = 0; index < deletedEmployees.size(); index++) {
                    System.out.println((index + 1) + " :\n    " + deletedEmployees.get(index));
                }
                employeeId = getAndValidateId();
                String recoveryStatus = employeeController.recoverEmployee(employeeId);
                if (null == recoveryStatus) {
                    System.out.println(constants.invalidDetails);
                } else {
                    System.out.println(recoveryStatus); 
                }   
                break;
            case "2":
                employeeId = getAndValidateId();
                recoverAddress(employeeId);
                break;
            default:
                 System.out.println(constants.invalidDetails);
        }
    }
    
    /**
     * Methode to recover employee address
     */
    private void recoverAddress(int employeeId)throws SQLException {
        Map <Integer, String>  deletedAddressList = employeeController
                .getDeletedAddressList(employeeId); 
        List <Integer> deletedAddressIdList 
                = new ArrayList<Integer> (deletedAddressList.keySet());   
        if (0 != deletedAddressList.size()) {
            System.out.println("Which address you want to recover ?");
            for(int index = 0; index < deletedAddressIdList.size(); index++) {
                System.out.println((index + 1) + " :\n    "
                        + deletedAddressList.get(deletedAddressIdList.get(index)));
            }
            int input2 = Integer.parseInt(scanner.nextLine());
            int addressId = deletedAddressIdList.get(input2 - 1);
            if(employeeController.recoverAddress(addressId)) {
                System.out.println("Address recoverd successfully");
            } else {
                System.out.println(constants.invalidDetails);
            }         
        } else {
            System.out.println(constants.invalidDetails);
        }
    }             
}