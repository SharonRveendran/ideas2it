package com.ideas2it.employeemanagement.project.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.project.controller.ProjectController;

/**
 * Class for Project view
 * @author Sharon V
 * @created 24-03-2021
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
			+ "       |  7 : Exit\n2 : Display existing project  | "
			+ " 4 : Delete project                |  6 : Recover deleted project   |";	
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
   	            //displayAllProject();
   	            break;
   	        case "6":
   	            //recoverProject();
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
        int projectId = getValidId();
        String projectDetails = projectController.getProject(projectId);
        if (null == projectDetails) {
            System.out.println("No project exist with given id");
        } else {
            System.out.println("\n............. Project Details ..............\n" + projectDetails);
        }
    }

    /**
     * Method to display all project present in database
     */
    private void displayAllProject() {
        List<String> projectDetailsList = projectController.getAllProject();
        if (null == projectDetailsList) {
            System.out.println("No projects present in database");
        } else {
            System.out.println("\n............. Projects Details ...............");
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
        System.out.print("\nEnter project id : ");
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
        int projectId = getValidId();
        if (projectController.deleteProject(projectId)) {
            System.out.println("Project deleted Successfully...!!!");
        } else {
            System.out.println("No project exist with the given id");
        }
    }
    








}