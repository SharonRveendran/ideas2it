package com.ideas2it.employeemanagement.employee.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.exceptions.EmployeeManagementException;
import org.apache.log4j.Logger;

/**
 * Employee controller servlet class
 * @author Sharon V
 * @created 21-04-2021
 */
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//final static Logger logger = Logger.getLogger(EmployeeController.class);
	private EmployeeService employeeService = new EmployeeServiceImpl();
    
    /**
     * Method to accept get request from  user
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String option = request.getParameter("action");
        switch (option) {
            case "display_all_employee":
            	 //logger.info("Successfull.....................!!!");
    	        getAllEmployeesDetails(request, response);
	            break;
            case "display_available_project":
            	getAllProjectsBasicDetails(request, response); 
        }
    }
    
    /**
     * Method to accept post request from  user
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String option = request.getParameter("action");
        switch (option) {
   	        case "create_or_update_employee":
   	    	    insertOrUpdateEmployee(request, response);
   	            break;
   	        case "display_employee":
    	        getEmployeeDetails(Integer.parseInt(request.getParameter("id")), request, response);
    	        break;
   	        case "delete_employee":
    	        deleteEmployee(Integer.parseInt(request.getParameter("id")), request, response);
    	        break;
            case "restore_employee":
    	        restoreEmployee(Integer.parseInt(request.getParameter("id")), request, response);
    	        break;
            case "update_employee":
    	        getUpdatePage(Integer.parseInt(request.getParameter("id")), request, response);
          	    break;
            case "assign_project":
            	assignProject(request, response); 
            	break;
            case "unassign_project":
            	removeProject(Integer.parseInt(request.getParameter("employeeId")),
                        Integer.parseInt(request.getParameter("projectId")), request, response);
            	break;
            case "display_assigned_projects":
            	getProjectsBasicDetails(Integer.parseInt(request.getParameter("employeeId")), request, response); 	    
        }
    }
    
    /**
     * Method to display the update page to user
     * @param employeeId employee id
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getUpdatePage(int employeeId, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	    try {
    	    	if (employeeService.isIdExist(employeeId)) {
    	    		List<String> employeeDetails = new ArrayList<String>(employeeService
    	    				.getEmployee(Integer.parseInt(request.getParameter("id"))).values());
    	    		request.setAttribute("employeeDetails", employeeDetails);
    	    		request.getRequestDispatcher("/employee_form.jsp").forward(request, response);
    	        } else {
    	    	    request.setAttribute("message", "No Employee exist with given id");
            	    request.getRequestDispatcher("/update_employee.jsp").forward(request, response);
    	        }
    	    } catch (EmployeeManagementException e) {
    	    	request.setAttribute("message", e.getMessage());
            	request.getRequestDispatcher("/error.jsp").forward(request, response);
    	    }
	}
    
	/**
     * Method to create employee
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws NumberFormatException 
     */
    private void insertOrUpdateEmployee(HttpServletRequest request, HttpServletResponse response)
    		throws NumberFormatException, ServletException, IOException { 	
        String name = request.getParameter("name");
    	String designation = request.getParameter("designation");
    	double salary = Double.parseDouble(request.getParameter("salary"));
    	long mobile = Long.parseLong(request.getParameter("mobile"));
    	Date dob = Date.valueOf(request.getParameter("dob"));
    	List<String[]> addresses = new ArrayList<String[]>();
        String permanentAddress[] = new String[6];
        permanentAddress[0] = request.getParameter("doorNumber");
    	permanentAddress[1] = request.getParameter("street");   		
    	permanentAddress[2] = request.getParameter("district");
    	permanentAddress[3] = request.getParameter("state");
    	permanentAddress[4] = request.getParameter("country");
    	permanentAddress[5] = "Permanent";
    	addresses.add(permanentAddress);
    	String TemporaryAddress[] = new String[6];
    	TemporaryAddress[0] = request.getParameter("temporaryDoorNumber");
    	TemporaryAddress[1] = request.getParameter("temporaryStreet");   		
    	TemporaryAddress[2] = request.getParameter("temporaryDistrict");
        TemporaryAddress[3] = request.getParameter("temporaryState");
    	TemporaryAddress[4] = request.getParameter("temporaryCountry");
    	TemporaryAddress[5] = "Temporary";
    	addresses.add(TemporaryAddress);
    	if ("".equals(request.getParameter("id"))) { 
    	    try {
				employeeService.createEmployee(name, designation, salary,
					    mobile, dob, addresses);
		   	    request.setAttribute("message", "Employee Created Successfully...!!!");
	    	    request.getRequestDispatcher("/success.jsp").forward(request, response);
			} catch (EmployeeManagementException e) {
				request.setAttribute("message", e.getMessage());
	    	    request.getRequestDispatcher("/success.jsp").forward(request, response);
			} 
        } else {
    	    int id = Integer.parseInt(request.getParameter("id"));
    	    try {
                employeeService.updateEmployee(id, name, designation, salary, dob, mobile, addresses);		
    	        request.setAttribute("message", "Employee Updated Successfully...!!!");
    		    request.getRequestDispatcher("/update_employee.jsp").forward(request, response);
    	    } catch(EmployeeManagementException e) {
    	    	request.setAttribute("message", "Something went wrong...!!!");
    		    request.getRequestDispatcher("/error.jsp").forward(request, response);
    	    } 
    	}
    } 

    /**
     * Method to get the employee details based on employee id
     * @param id Employee id
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getEmployeeDetails(int id, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	List<Map<String,String>> employeesDetails = new ArrayList<Map<String,String>>();
    	try {
    		Map<String, String> employeeDetails = employeeService.getEmployee(id);	
    		if (0 != employeeDetails.size()) {
    			String permanentAddress = employeeDetails.remove("permanentDoorNumber")
    					+ "," + employeeDetails.remove("permanentStreet") + "," + employeeDetails.remove("permanentDistrict")
    					+ "," + employeeDetails.remove("permanentState") + "," + employeeDetails.remove("permanentCountry");
    			employeeDetails.put("permanentAddress", permanentAddress);
    			if(11 < employeeDetails.size()) {
    				String temporaryAddress = employeeDetails.remove("temporaryDoorNumber")
    						+ "," + employeeDetails.remove("temporaryStreet") + "," + employeeDetails.remove("temporaryDistrict")
    						+ "," + employeeDetails.remove("temporaryState") + "," + employeeDetails.remove("temporaryCountry");
    				employeeDetails.put("temporaryAddress", temporaryAddress);
    			} else {
    				employeeDetails.put("temporaryAddress", "---------------");
    			}
    			employeesDetails.add(employeeDetails);
    			request.setAttribute("employeesDetails", employeesDetails);
    			request.getRequestDispatcher("/display_employee.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "No Employee exist with given id");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }
    
    /**
     * Method to return all employee details present in collection
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getAllEmployeesDetails(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
    		List<Map<String,String>> employeesDetails = employeeService.getAllEmployeesDetails();
    		if (0 != employeesDetails.size()) {
    			request.setAttribute("employeesDetails", employeesDetails);
    			request.getRequestDispatcher("/display_employee.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "No employee present");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		} 
    	} catch(EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }

    /**
     * Method to delete the Employee based on employee id
     * @param id Employee id
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    private void deleteEmployee(int id, HttpServletRequest request, HttpServletResponse response)
    		throws IOException, ServletException {
    	try {
    		if (employeeService.deleteEmployee(id)) {
    			request.setAttribute("message", "Employee Deleted sussessfully...!!!");
    			request.getRequestDispatcher("/delete_employee.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "Deletion Failed.No employee exist with given id");
    			request.getRequestDispatcher("/delete_employee.jsp").forward(request, response);
    		}  
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }

    /**
     * Method to restore deleted employee
     * @param id employee id
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    private void restoreEmployee(int id, HttpServletRequest request, HttpServletResponse response)
    		throws IOException, ServletException {
    	try {
    		if (employeeService.restoreEmployee(id)) {
    			request.setAttribute("message", "Employee Recovered sussessfully...!!!");
    			request.getRequestDispatcher("/success.jsp").forward(request, response);
    		} else {
    			request.setAttribute("message", "No employee to recover with given id");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		} 
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", "Something went Wrong...!!!");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }  

    /**
     * Method to get projects basic details
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getAllProjectsBasicDetails(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
            List<List<String>> projectsDetails = employeeService.getAllProjectsBasicDetails();
            if (!projectsDetails.isEmpty()) {
                request.setAttribute("projectsDetails", projectsDetails);
  	            request.getRequestDispatcher("/display_available_projects.jsp").forward(request, response);
            } else {
        	    request.setAttribute("message", "No projects available");
  	            request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
    	} catch (EmployeeManagementException e) {
    		request.setAttribute("message", "Something went wrong...!!!");
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }

    /**
     * Method to assign projects to employee
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void assignProject(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
    		if (employeeService.isIdExist(Integer.parseInt(request.getParameter("id")))) {
    			String[] projectIdStrings=request.getParameterValues("xyz");
    			if (null == projectIdStrings) {
    				request.setAttribute("message", "Select atleast 1 project");
    				request.getRequestDispatcher("/error.jsp").forward(request, response);
    			} else {
    				List<Integer> projectsIdList = new ArrayList<Integer>();
    				for (String projectId : projectIdStrings) {
    					projectsIdList.add(Integer.parseInt(projectId));
    				} 
    				if (employeeService.assignProject(projectsIdList, Integer.parseInt(request.getParameter("id")))) {
    					request.setAttribute("message", "Project Assigned Successfully...!!!");
    					request.getRequestDispatcher("/success.jsp").forward(request, response);
    				} else {
    					request.setAttribute("message", "Assignment Failed.Some project already assigned");
    					request.getRequestDispatcher("/error.jsp").forward(request, response);
    				}
    			}
    		} else {
    			request.setAttribute("message", "Given employee id not exist...!!!");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch(EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }

    /**
     * Method to get assigned projects details of specified employee
     * @param employeeId employee id to get the assigned projects details
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void getProjectsBasicDetails(int employeeId, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException { 
    	try {
    		if (employeeService.isIdExist(employeeId)) {
    			List<Map<String, String>> projectsDetails = employeeService.getProjectsBasicDetails(employeeId);
    			if (0 != projectsDetails.size()) {
    				request.setAttribute("projectsDetails", projectsDetails);
    				request.getRequestDispatcher("/display_assigned_projects.jsp").forward(request, response);
    			} else {
    				request.setAttribute("message", "No project assigned for given employee");
    				request.getRequestDispatcher("/error.jsp").forward(request, response);
    			}
    		} else {
    			request.setAttribute("message", "Given employee idnot exist...!!!");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
    	} catch(EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }

    /**
     * Method to remove assigned project of employee
     * @param employeeId employee id
     * @param projectId project id
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    private void removeProject(int employeeId, int projectId, HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	try {
    		if (employeeService.isIdExist(employeeId)) {
    			if (employeeService.removeProject(employeeId, projectId)) {
    				request.setAttribute("message", "Project Removed Successfully...!!!");
    				request.getRequestDispatcher("/success.jsp").forward(request, response);
    			} else {
    				request.setAttribute("message", "No project with given id assigned for given employee...!!!");
    				request.getRequestDispatcher("/error.jsp").forward(request, response);
    			}
    		} else {
    			request.setAttribute("message", "Given employeeid not exist...!!!");
    			request.getRequestDispatcher("/error.jsp").forward(request, response);
    		}
        } catch(EmployeeManagementException e) {
    		request.setAttribute("message", e.getMessage());
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    }
}
