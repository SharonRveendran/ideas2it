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
   	            assignProjects();
   	            break;
                case "8":
   	            displayAssignedProjects();
   	            break;
                case "9":
   	            removeProject();
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
     * Method to display all employee details present in collection
     */
    private void displayAll() {
        List<String> employeesDetails = employeeController.getAllEmployeesDetails();
        if (0 == employeesDetails.size()) {
            System.out.println(Constants.NO_EMPLOYEE);
        } else {
            for(String employeeDetails : employeesDetails) {
                System.out.println(employeeDetails);
            }
        }
    }
    
    /**
     * Method to delete employee based on employee id
     */
    private void deleteEmployee() { 
    	int id = getAndValidateId() ;  
    	if (employeeController.isIdExist(id)) {
            if (employeeController.deleteEmployee(id)) {
                System.out.println(Constants.SUCCESSFULL_DELETION);
            } else {
                System.out.println("Deletion failed...!!!");
            }   
        } else {
            System.out.println(Constants.NO_EMPLOYEE);
        }
    }    
    
    /**
     * Method to recover a deleted employee
     */
    private void recoverEmployee() {
        int employeeId; 
        List <String> deletedEmployees = employeeController.getDeletedEmployees();
        if (0 != deletedEmployees.size()) {
            System.out.println("Deleted employees are given below");
            for(int index = 0; index < deletedEmployees.size(); index++) {
                System.out.println((index + 1) + " :\n    " + deletedEmployees.get(index));
            }
            employeeId = getAndValidateId();
            String recoveryStatus = employeeController.recoverEmployee(employeeId);
            if (null == recoveryStatus) {
                System.out.println("\nNo employee to recover with given id");
            } else {
                System.out.println(recoveryStatus);
            }
        } else {
            System.out.println("\nNo employees to recover");
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
                    employeeController.updateAddress(employeeId, addressId, addressDetails);
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
     * Method to assign projects to employee
     */
    private void assignProjects() {
        Map<Integer, String> projectsBasicsDetails = employeeController.getAllProjectsBasicDetails();System.out.println("ssssssssssssssssssssssss");
        Map<Integer, String> employeeBasicsDetails = employeeController.getAllEmployeeBasicDetails();
        List<Integer> projectIdList = new ArrayList<Integer>();
        if (0 != employeeBasicsDetails.size()) {
            Set<Integer> employeeIdSet = employeeBasicsDetails.keySet();
            System.out.println("................. LIST OF EMPLOYEES ..................\n");
            for (int employeeId : employeeIdSet) {
                System.out.println(employeeBasicsDetails.get(employeeId));
            }
            int employeeId = getAndValidateId();
            if (employeeIdSet.contains(employeeId)) {
                if (0 != projectsBasicsDetails.size()) {
                    Set<Integer> projectIdSet = projectsBasicsDetails.keySet();
                    System.out.println("\n................. LIST OF PROJECTS ..................");
                    for (int projectId : projectIdSet) {
                        System.out.println(projectsBasicsDetails.get(projectId));
                    }
 
                    String input = null;
                    do {
                        int projectId = getAndValidateId();
                    
                        if (projectIdSet.contains(projectId)) {
                            projectIdList.add(projectId);
                        } else {
                            System.out.println("\nNo project Available with given project id");
                        } 
                        System.out.println("\nDo you want to add more projects ?\n1 : Yes\n2 : No");
                        input = scanner.nextLine();
                    } while ("1".equals(input));
                    if (employeeController.assignProject(projectIdList, employeeId)) {
                            System.out.println("\nProjects assigned successfully...!!!");
                    } else { 
                            System.out.println("\nProject assignment failed because Some Projects already assigned");
                    }
                } else {
                    System.out.println("No project availabale");
                }
            } else {
                 System.out.println("\nNo employee Available with given employee id");
            }
        } else {
            System.out.println("No Employees availabale");
        }
    } 

    /**
     * Methode to display projects assigned to employee
     */
    private void displayAssignedProjects() {
        Map<Integer, String> employeeBasicsDetails = employeeController.getAllEmployeeBasicDetails();  
        if (0 != employeeBasicsDetails.size()) {
            Set<Integer> employeeIdSet = employeeBasicsDetails.keySet();
            System.out.println("................. LIST OF EMPLOYEES ..................\n");
            for (int employeeId : employeeIdSet) {
                System.out.println(employeeBasicsDetails.get(employeeId));
            }
            int employeeId = getAndValidateId();
            if (employeeIdSet.contains(employeeId)) {
                List<String> projectsBasicDetails = employeeController.getProjectsBasicDetails(employeeId);
                if (0 != projectsBasicDetails.size()) {
                    System.out.println("\n.............LIST OF ASSIGNED PROJECTS.................\n");
                    for (String projectBasicDetails : projectsBasicDetails) {
                        System.out.println(projectBasicDetails);
                    }
                } else {
                    System.out.println("\nNo projects assigned for given employee");
                }
            } else {
                System.out.println("No employee exist with given id");
            }
        } else {
            System.out.println("No Employees availabale");
        } 
    } 
  
     /**
     * Method to remove assigned projects of employee
     */
    private void removeProject() {
        System.out.print("\n.........Enter employee details............\n");
        int employeeId = getAndValidateId();
        List<String> projectsBasicDetails = employeeController.getProjectsBasicDetails(employeeId);
        if (0 == projectsBasicDetails.size()) {
            System.out.println("No project exist with given id");
        } else {
            for (String projectBasicDetails : projectsBasicDetails) {
                System.out.println("\n............. PROJECT DETAILS ..............\n"
                        + projectBasicDetails);
            }
            System.out.println("\n........Enter project details.......");
            int projectId = getAndValidateId();
            if (employeeController.removeProject(employeeId, projectId)) {
                System.out.println("Project removed successfully....!!!"); 
            } else {
                System.out.println("Project removing Failed due to invalid details...!!!");   
            }
        }     
    }
}