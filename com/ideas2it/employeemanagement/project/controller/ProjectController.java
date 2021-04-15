package com.ideas2it.employeemanagement.project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.employeemanagement.project.service.ProjectService;

/**
 * Class for Project controller
 * @author Sharon V
 * @created 27-03-2021
 */
public class ProjectController extends HttpServlet{
    private ProjectService projectService = new ProjectServiceImpl();
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	PrintWriter out = response.getWriter();
    	String option = request.getServletPath();
        switch (option) {
   	    	case "/create_project":
   	    		createProject(request, response);
   	    	case "/delete_project":
   	    		deleteProject(Integer.parseInt(request.getParameter("id")),request, response);
   	    	case "/restore_project":
   	    		recoverProject(Integer.parseInt(request.getParameter("id")),request, response);
   	    	case "/display_project":
   	    		getProjectDetails(Integer.parseInt(request.getParameter("id")),request, response);
   	    	case "/display_all_projects":
   	    		getAllProjectDetails(request, response);
   	    }  
    } 

    /**
     * Method to validate date
     * @param date User given date string
     * @return valid date
     */
    public Date isValidDate(String date) {
    	return projectService.isValidDate(date);
    }
  
    /**
     * Method to create new project
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public void createProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  	
        if (projectService.createProject(request.getParameter("name"), request.getParameter("managerName"),
                Date.valueOf(request.getParameter("startDate")), Date.valueOf(request.getParameter("endDate")))) {
        	request.setAttribute("message", "Project Created Successfully...!!!");
    	    request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
        	request.setAttribute("message", "Project creation failed...!!!");
    	    request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }     

    /**
     * Method to get project details based on project id
     * @param projectId project id to display project
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    public void getProjectDetails(int projectId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> projectDetails = projectService.getProjectDetails(projectId);
        if (0 != projectDetails.size()) {
        	List<Map<String, String>> projectsDetails = new ArrayList<Map<String, String>>();
        	projectsDetails.add(projectDetails);
        	request.setAttribute("projectsDetails", projectsDetails);
        	request.getRequestDispatcher("/display_project.jsp").forward(request, response);
        } else {
        	request.setAttribute("message", "No Project exist with given id");
        	request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * Methode to validate id
     * @param input project id in string format which need to be validate
     * @return true if given id is valid else false
     */
    public boolean isValidId(String input) {
        return projectService.isValidId(input);
    }

    /**
     * Method to display all project present in database
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    public void getAllProjectDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         List<Map<String, String>> projectsDetails = projectService.getAllProjectDetails(false);
         if (0 != projectsDetails.size()) {
        	 request.setAttribute("projectsDetails", projectsDetails);
         	 request.getRequestDispatcher("/display_project.jsp").forward(request, response);
         } else {
         	request.setAttribute("message", "No Projects exist");
         	request.getRequestDispatcher("/error.jsp").forward(request, response);
         }
    }

    /**
     * Method to delete project based on projectId
     * @param projectId project id to delete project
     * @param request http request object
     * @param response http response object
     * @throws ServletException
     * @throws IOException
     */
    public void deleteProject(int projectId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (projectService.deleteProject(projectId)) {
        	request.setAttribute("message", "Project deleted Successfully...!!!");
    	    request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
        	request.setAttribute("message", "Project deletion failed...!!!");
    	    request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * Method to recover deleted project
     * @param projectId project id to recover project
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    public void recoverProject(int projectId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (projectService.recoverProject(projectId)) {
    	   request.setAttribute("message", "Project recovered Successfully...!!!");
   	    request.getRequestDispatcher("/success.jsp").forward(request, response);
       } else {
       	request.setAttribute("message", "Project recovery failed...!!!");
   	    request.getRequestDispatcher("/error.jsp").forward(request, response);
       } 
    }

    /**
     * Method to update project details
     * @param projectId project id for update project
     * @param name project name
     * @param managerName name of projectManager
     * @param startDate project starting date
     * @param endDate project ending date
     * @param option indicating which project details need to update
     * @return true for successfull updation else false
     */
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate, String option) {
        return projectService.updateProject(projectId, name,
                managerName, startDate, endDate, option);
    }

    /**
     * Methode to get basic details of employees 
     * @return List of employee's basic details
     */
    public Map<Integer, String> getAllEmployeesDetails() {
        return projectService.getAllEmployeesDetails();
    }
   
    /**
     * Method to assign employees to project
     * @param employeeIdList list of employee ids
     * @param projectId project id in which we need to assign employees
     * @return true for successfull assignment else false
     */
    public boolean assignEmployee(List<Integer> employeeIdList, int projectId) {
        return projectService.assignEmployee(employeeIdList, projectId);
    }

    /**
     * Method to get all projects basics details
     * @return map of project id as key and project details as value
     */
    public Map<Integer, String> getAllProjectBasicDetails() {
        return projectService.getAllProjectBasicDetails();
    }

    /**
     * Method to remove assigned employee from project
     * @return true for successfull remove else false
     */
    public boolean removeEmployee(int projectId, int employeeId) {
        return projectService.removeEmployee(projectId, employeeId);
    }

    /**
     * Method to get details of employees assigned for given project
     * @param projectId project id  
     * @return list of employee details
     */
    public List<String> getEmployeesBasicDetails(int projectId) {
        return projectService.getEmployeesBasicDetails(projectId);
    }
}