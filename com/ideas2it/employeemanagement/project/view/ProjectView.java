package com.ideas2it.employeemanagement.project.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.project.controller.ProjectController;

/**
 * Class for Project view
 * @author Sharon V
 * @created 27-03-2021
 */
public class ProjectView {
    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();

    /**
     * This method will collect the input option from user
     * and perfom CRUD operation
     */
    public void start() {
        String message = "\n1 : Create new project        |  "
	        + "3 : Display all existing projects |  5 : Update project     "
		+ "       |  7 : Assign Employee in project   |\n2 : Display existing project  | "
		+ " 4 : Delete project                |  6 : Recover deleted project   |  8 : Exit          "
		+ "               |\n-------------------------------------------------------------------"
	        + "----------------------------------------------------------------------\n";	
        System.out.println("\nSelect your option");
        String option;
        do {
            System.out.println(message); 
            option = scanner.nextLine();
            switch (option) {
   	        case "1":
   	            createProject();
   		    break;
   	        case "2":
   	            displayProject();
   		    break;	
   	        case "3":
   	            displayAllProject();
   		    break;
   	        case "4":
   	            deleteProject();
   		    break;
   	        case "5":
   	            updateProject();
   	            break;
   	        case "6":
   	            recoverProject();
   	            break;
                case "7":
                    assignEmployee();
                    break;
                case "8":
   	            System.out.println(Constants.END_MESSAGE);
   	            break;
   	        default:
   	            System.out.println(Constants.INVALID_DETAILS);
   	    } 	 
	} while(!"8".equals(option));  
    }

    /**
     * Method to create project
     */
    private void createProject() {
        System.out.print("\n.......... Enter project details ..........\n\n");
    	System.out.print("Project name              : ");
    	String name = scanner.nextLine();
    	System.out.print("\nManagerName               : ");
    	String managerName = scanner.nextLine();
        System.out.print("\nEnter project start date\n(YYYY-MM-DD)              : ");
    	Date startDate = getAndValidateDate();
        System.out.print("\nEnter project end date\n(YYYY-MM-DD)              : ");
        Date endDate = getAndValidateDate();
        if (projectController.createProject(name, managerName, startDate, endDate)) {
            System.out.println("\nProject created successfully...!!!");
        } else {
            System.out.println("Something went wrong");
        }
    }

    /**
     * Method to get and validate the start date of project
     * @return Employee date of birth
     */
    private Date getAndValidateDate() {
        String date;
        Date validDate;    
        do {
            date = scanner.nextLine();
            validDate = projectController.isValidDate(date);
	    if (null == validDate) { 
	        System.out.print("\nPlease provide valid date\n(YYYY-MM-DD)              : ");
	        date = "invalidDate";
            }
        } while("invalidDate".equals(date));
        return validDate;
    }

    /**
     * Method to display project based on project id
     */
    private void displayProject() {
        System.out.print("\nEnter project id : ");
        int projectId = getValidId();
        String projectDetails = projectController.getProject(projectId);
        if (null == projectDetails) {
            System.out.println("No project exist with given id");
        } else {
            System.out.println("\n............. PROJECT DETAILS ..............\n"
                    + projectDetails);
        }
    }

    /**
     * Method to display all project present in database
     */
    private void displayAllProject() {
        int isDeleted = 0;
        List<String> projectDetailsList = projectController.getAllProject(isDeleted);
        if (null == projectDetailsList) {
            System.out.println("No projects present in database");
        } else {
            System.out.println("\n.............Existing Projects Details ...............");
            for(String projectDetails : projectDetailsList) {
                System.out.println(projectDetails);
            }
        }
    }

    /**
     * Methode to get valid id
     * @return projectId valid project id
     */
    private int getValidId() {
        int projectId = 0;
        String input;
        do {
            input = scanner.nextLine();
            if (projectController.isValidId(input)) {
                projectId = Integer.parseInt(input);
            } else {
                System.out.println("Please enter valid id");
                input = null;
            }
        } while(null == input);
        return projectId;
    }

    /**
     * Method to delete project based on projectId
     */
    private void deleteProject() {
        System.out.print("\nEnter project id : ");
        int projectId = getValidId();
        if (projectController.deleteProject(projectId)) {
            System.out.println("Project deleted Successfully...!!!");
        } else {
            System.out.println("No project exist with the given id");
        }
    }

    /**
     * Method to recover deleted project
     */
    private void recoverProject(){
        int isDeleted = 1;
        List<String> projectDetailsList = projectController.getAllProject(isDeleted);
        if (null == projectDetailsList) {
            System.out.println("All projects present in database");
        } else {
            System.out.println("\n....... Deleted projects are given below ........\n");
            for(String projectDetails : projectDetailsList) {
                System.out.println(projectDetails);
            }
            System.out.print("\nEnter project id : ");
            int projectId = getValidId();
            if(projectController.recoverProject(projectId)) {
                System.out.println("Recovery successfull...!!!");
            } else {
                System.out.println("Wrong project id");
            }
        }
    }
      
    /**
     * Method to update existing project
     */
    private void updateProject() {
        displayAllProject();
        System.out.print("\nEnter project id : ");
        int projectId = getValidId();
        String updateMessage = "\n1 : name          |    3 : Start date   "
                + "|\n2 : ManagerName   |    4 : End date     "
                + "|\n-----------------------------------------";
        System.out.println("\nSelect your option which  need to update");
        String option = "";
        do {
            System.out.println(updateMessage);
            option = scanner.nextLine();
    	    switch (option) {
    	         case "1":
    	    	    updateName(projectId);
    	    	    break;
    	    	 case "2":
    	    	    updateManagerName(projectId);
    	    	    break;
    	         case "3":
    	    	    updateStartDate(projectId);
    	    	    break;
    	         case "4":
    	            updateEndDate(projectId);
    	            break;
                 default:
                    option = null;
                    System.out.println("Please select valid option");
    	    }
        } while (null == option);   
    }  
    
    /**      
     * Methode to update project name
     * @param projectId project id for update project details
     */
    private void updateName(int projectId) {
        System.out.print("\nEnter new name : ");
        String name = scanner.nextLine();
        if (projectController.updateProject(projectId, name, null, null, null, "name")) {
            System.out.println("\nProject updated successfully");
        } else {
            System.out.println("\nNo project exist with given id");
        }
    }    

    /**      
     * Methode to update project manager name
     * @param projectId project id for update project details
     */
    private void updateManagerName(int projectId) {
        System.out.print("\nEnter new manager name : ");
        String managerName = scanner.nextLine();
        if (projectController.updateProject(projectId, null, managerName, null, null, "manager name")) {
            System.out.println("\nProject updated successfully");
        } else {
            System.out.println("\nNo project exist with given id");
        }
    }

    /**      
     * Methode to update project start date
     * @param projectId project id for update project details
     */
    private void updateStartDate(int projectId) {
        System.out.print("\nEnter new project start date : ");
        Date startDate = getAndValidateDate();
        if (projectController.updateProject(projectId, null, null, startDate, null, "start date")) {
            System.out.println("\nProject updated successfully");
        } else {
            System.out.println("\nNo project exist with given id");
        }
    }
   
    /**      
     * Methode to update project name
     * @param projectId project id for update project details
     */
    private void updateEndDate(int projectId) {
        System.out.print("\nEnter new project end date : ");
        Date endDate = getAndValidateDate();
        if (projectController.updateProject(projectId, null, null, null, endDate, "end date")) {
            System.out.println("\nProject updated successfully");
        } else {
            System.out.println("\nNo project exist with given id");
        }
    }

    /**
     * Method to assign employees to the project
     */
    private void assignEmployee() {
        Map<Integer, String> employeesDetails = projectController.getAllEmployeesDetails();
        Map<Integer, String> projectsDetails = projectController.getAllProjectBasicDetails();
        List<Integer> employeeIdList = new ArrayList<Integer>();
        if (0 != projectsDetails.size()) {
            Set<Integer> projectIdSet = projectsDetails.keySet();
            System.out.println("................. LIST OF PROJECTS ..................");
            for (int projectsId : projectIdSet) {
                System.out.println(projectsDetails.get(projectsId));
            }
            System.out.print("\nEnter project id : ");
            int projectId = getValidId();
            if (projectIdSet.contains(projectId)) {
                if (0 != employeesDetails.size()) {
                    Set<Integer> employeeIdSet = employeesDetails.keySet();
                    System.out.println("\n................. LIST OF EMPLOYEES ..................");
                    for (int employeesId : employeeIdSet) {
                        System.out.println(employeesDetails.get(employeesId));
                    }
                    String input = null;
                    do {
                        System.out.print("\nEnter employee id : ");
                        int employeeId = getValidId();
                    
                        if (employeeIdSet.contains(employeeId)) {
                            employeeIdList.add(employeeId);
                        } else {
                            System.out.println("\nNo employee Available with given employee id");
                        } 
                        System.out.println("\nDo you want to add more employees ?\n1 : Yes\n2 : No");
                        input = scanner.nextLine();
                    } while ("1".equals(input));
                    if (projectController.assignEmployee(employeeIdList, projectId)) {
                            System.out.println("\nEmployee assigned successfully...!!!");
                    } else { 
                            System.out.println("\nEmployee Assignment Failed...Because Some Employee already assigned");
                    }
                } else {
                    System.out.println("No employee availabale");
                }
             } else {
                 System.out.println("\nNo project Available with given project id");
             }
        } else {
            System.out.println("No Project availabale");
        }
    }







}