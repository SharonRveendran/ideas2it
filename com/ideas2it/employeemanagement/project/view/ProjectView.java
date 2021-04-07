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
        String message = "\n1 : Create new project            |  4 : Delete project     "
                + "           |  7 : Assign Employee in project     |\n2 : Display existing project"
                + "      |  5 : Update project                |  8 : Remove Employee from Project   "
                + "|\n3 : Display all existing projects |  6 : Recover deleted project       |  9 : Exit "
                + "                          |\n---------------------------------------------------"
                + "-----------------------------------------------------------\n";	
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
   	           // updateProject();
   	            break;
   	        case "6":
   	            recoverProject();
   	            break;
                case "7":
                   // assignEmployee();
                    break;
                case "8":
                    //removeEmployee();
                    break;
                case "9":
   	            System.out.println(Constants.END_MESSAGE);
   	            break;
   	        default:
   	            System.out.println(Constants.INVALID_DETAILS);
   	    } 	 
	} while(!"9".equals(option));  
    }

    /**
     * Method to create project
     */
    private void createProject() {
        System.out.print("\n.......... ENTER PROJECT DETAILS ..........\n\n");
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
        String projectDetails = projectController.getProjectDetails(projectId);
        if (null == projectDetails) {
            System.out.println("No project exist with given id");
        } else {
            System.out.println("\n............. PROJECT DETAILS ..............\n"
                    + projectDetails);
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
     * Method to display all project present in database
     */
    private void displayAllProject() {
        boolean isDeleted = false;
        List<String> projectDetailsList = projectController.getAllProjectDetails(isDeleted);
        if (null == projectDetailsList) {
            System.out.println("No projects present in database");
        } else {
            System.out.println("\n.............EXISTING PROJECT DETAILS ...............");
            for(String projectDetails : projectDetailsList) {
                System.out.println(projectDetails);
            }
        }
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
        boolean isDeleted = true;
        List<String> projectDetailsList = projectController.getAllProjectDetails(isDeleted);
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
}