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
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * Project controller servlet
 * @author Sharon V
 * @created 27-03-2021
 */
public class ProjectController extends HttpServlet{
	private ProjectService projectService = new ProjectServiceImpl();
    EmployeeManagementLogger logger = new EmployeeManagementLogger(ProjectController.class);
    
    /**
     * Method to accept get request from  user
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response){
    	try {
    		String option = request.getParameter("action");
    		switch (option) {
        		case "display_all_projects":
        			getAllProjectDetails(request, response);
        			break;
        		case "display_available_employees":
        			getAllEmployeesDetails(request, response);
        			break;
    		}
    	} catch (ServletException | IOException e) {
            logger.logError(projectService.getStackTrace(e));
		}
    }
    
    /**
     * Method to accept post request from  user
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		String option = request.getParameter("action");
    		switch (option) {
   	    		case "create_or_update_project":
   	    			createOrUpdateProject(request, response);
   	    			break;
   	    		case "delete_project":
   	    			deleteProject(Integer.parseInt(request.getParameter("id")),request, response);
   	    			break;
   	    		case "restore_project":
   	    			restoreProject(Integer.parseInt(request.getParameter("id")),request, response);
   	    			break;
   	    		case "display_project":
   	    			getProjectDetails(Integer.parseInt(request.getParameter("id")),request, response);
   	    			break;
   	    		case "assign_employee":
   	    			assignEmployee(request, response);
   	    			break;
   	    		case "unassign_employee":
   	    			removeEmployee(Integer.parseInt(request.getParameter("projectId")),
   	    					Integer.parseInt(request.getParameter("employeeId")), request, response);
   	    			break;
   	    		case "display_assigned_employees":
   	    			getEmployeesBasicDetails(Integer.parseInt(request.getParameter("projectId")), request, response);
   	    			break;
   	    		case "update_project":
   	    			getUpdatePage(Integer.parseInt(request.getParameter("id")), request, response);
    		}
    	} catch (ServletException | IOException e) {
    		logger.logError(projectService.getStackTrace(e));
		}
    }

    /**
     * Method to update Project
     * @param projectId project id to get and update project details
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getUpdatePage(int projectId, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
    		if (projectService.isIdExist(projectId)) {
    			List<String> projectDetails = new ArrayList<String>(projectService
    					.getProjectDetails(projectId).values());
    			request.setAttribute("projectDetails", projectDetails);
    			request.getRequestDispatcher("/project_form.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "Given project id not exist...!!!");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
	}
  
    /**
     * Method to create new project
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void createOrUpdateProject(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	try {
    		if("".equals(request.getParameter("id"))) {
    			if (projectService.createProject(request.getParameter("name"), 
    					request.getParameter("managerName"),
    			        Date.valueOf(request.getParameter("startDate")), 
    			        Date.valueOf(request.getParameter("endDate")))) {
    				request.setAttribute("message", "Project Created Successfully...!!!");
    				request.getRequestDispatcher("/success.jsp").forward(request, response);
    			} else {
    				request.setAttribute("message", "Project creation failed...!!!");
    				request.getRequestDispatcher("/error.jsp").forward(request, response);
    			}
    	    } else {
    			updateProjectDetails(Integer.parseInt(request.getParameter("id")),
    					request.getParameter("name"),
    					request.getParameter("managerName"),
    					Date.valueOf(request.getParameter("startDate")),
    					Date.valueOf(request.getParameter("endDate")), 
    					request, response);
    	    }
    	} catch(EmployeeManagementException e) {
			request.setAttribute("message", e.getMessage());
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
    private void getProjectDetails(int projectId, HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	try {
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
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }

    /**
     * Method to display all project present in database
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getAllProjectDetails(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	try {
            List<Map<String, String>> projectsDetails = projectService.getAllProjectDetails(false);
            if (0 != projectsDetails.size()) {
        	    request.setAttribute("projectsDetails", projectsDetails);
         	    request.getRequestDispatcher("/display_project.jsp").forward(request, response);
            } else {
         	   request.setAttribute("message", "No Projects exist");
         	   request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
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
    private void deleteProject(int projectId, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
    		if (projectService.deleteProject(projectId)) {
    			request.setAttribute("message", "Project deleted Successfully...!!!");
    			request.getRequestDispatcher("/success.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "Project deletion failed...!!!");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }

    /**
     * Method to restore deleted project
     * @param projectId project id to recover project
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void restoreProject(int projectId, HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        try {
        	if (projectService.restoreProject(projectId)) {
        		request.setAttribute("message", "Project restored Successfully...!!!");
        		request.getRequestDispatcher("/success.jsp").forward(request, response);
        	} else {
        		request.setAttribute("message", "Project restore failed...!!!");
        		request.getRequestDispatcher("/error.jsp").forward(request, response);
        	} 
        } catch(EmployeeManagementException e) {
        	request.setAttribute("message", e.getMessage());
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
     * @throws IOException 
     * @throws ServletException 
     */
    private void updateProjectDetails(int projectId, String name, String managerName,
            Date startDate, Date endDate, HttpServletRequest request, HttpServletResponse response) 
            		throws ServletException, IOException {
    	try {
    		if (projectService.updateProject(projectId, name,
    				managerName, startDate, endDate)) {
    			request.setAttribute("message", "Project updated Successfully...!!!");
    			request.getRequestDispatcher("/success.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "Project Updation Failed...!!!");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch(EmployeeManagementException e) {
        	request.setAttribute("message", e.getMessage());
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * Methode to get basic details of employees 
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getAllEmployeesDetails(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
    		List<List<String>> employeesDetails = projectService.getAllEmployeesDetails();
    		request.setAttribute("employeesDetails", employeesDetails);
    		request.getRequestDispatcher("/display_available_employees.jsp").forward(request, response);
    	} catch(EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }
   
    /**
     * Method to assign employees to project
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
	private void assignEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String[] employeesIdStrings = request.getParameterValues("employee_assignment");
			List<Integer> employeesIdList = new ArrayList<Integer>();
			if (null == employeesIdStrings) {
				request.setAttribute("message", "Select atleast 1 employee");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			} else {
				for (String employeeId : employeesIdStrings) {
					employeesIdList.add(Integer.parseInt(employeeId));
				}
				projectService.isIdExist(Integer.parseInt(request.getParameter("id")));
				if (projectService.assignEmployee(employeesIdList, 
						Integer.parseInt(request.getParameter("id")))) {
					request.setAttribute("message", "Employee Assigned Successfully...!!!");
					request.getRequestDispatcher("/success.jsp")
					.forward(request, response);
				} else {
					request.setAttribute("message", 
							"Employee Assignment Failed...Some employee already assigned");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}
		} catch (EmployeeManagementException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

    /**
     * Method to remove assigned employee from project
     * @param projectId project id
     * @param employeeId employee id
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void removeEmployee(int projectId, int employeeId, HttpServletRequest request, 
    		HttpServletResponse response) 
    		throws ServletException, IOException {
    	try {
    		projectService.isIdExist(projectId);
    		if (projectService.removeEmployee(projectId, employeeId)) {
    			request.setAttribute("message", "Employee Removed Successfully...!!!");
    			request.getRequestDispatcher("/success.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "Given Employee not assigned for given project");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch(EmployeeManagementException e) {
        	request.setAttribute("message", e.getMessage());
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * Method to get details of employees assigned for given project
     * @param projectId project id  
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getEmployeesBasicDetails(int projectId, HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	try { 
    		if (projectService.isIdExist(projectId)) {
    			List<Map<String, String>> employeesDetails =  projectService
    					.getEmployeesBasicDetails(projectId);
    			if (0 != employeesDetails.size()) {
    				request.setAttribute("employeesDetails", employeesDetails);
    				request.getRequestDispatcher("/display_assigned_employees.jsp")
    				.forward(request, response);
    			} else {
    				request.setAttribute("message", "No employee assigned for given project");
    				request.getRequestDispatcher("/error.jsp").forward(request, response);	
    			}
    		} else {
    			request.setAttribute("message", "Given project id not exist...!!!");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch(EmployeeManagementException e) {
        	request.setAttribute("message", e.getMessage());
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}