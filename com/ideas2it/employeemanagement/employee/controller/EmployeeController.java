package com.ideas2it.employeemanagement.employee.controller;

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

import com.ideas2it.employeemanagement.constants.Constants;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;

/**
 * Class for Employee controller
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	PrintWriter out = response.getWriter();
    	String option = request.getServletPath();
        switch (option) {
   	    case "/create_employee":
   	    	createEmployee(request, response);
   	        break;
        case "/display_employee":
   	        getEmployeeDetails(Integer.parseInt(request.getParameter("id")), request, response);
   	        break;
        case "/display_all_employee":
        	getAllEmployeesDetails(request, response);
   	        break;
        case "/delete_employee":
   	        deleteEmployee(Integer.parseInt(request.getParameter("id")), request, response);
   	        break;
        case "/restore_employee":
   	        recoverEmployee(Integer.parseInt(request.getParameter("id")), request, response);
   	        break;
        case "/update_employee":
   	        updateEmployee(Integer.parseInt(request.getParameter("id")), request, response);
   	        break;
        case "/display_available_project":
        	getAllProjectsBasicDetails(request, response);  
        case "/assign_employee":
        	assignProject(request, response); 
        case "/unassign_project":
        	removeProject(Integer.parseInt(request.getParameter("employeeId")),Integer.parseInt(request.getParameter("projectId")), request, response);
        case "/display_assigned_projects":
        	getProjectsBasicDetails(Integer.parseInt(request.getParameter("employeeId")), request, response);
        } 
    } 
    
    /**
     * 
     * @param employeeId
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    private void updateEmployee(int employeeId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	List<String> employeeDetails = new ArrayList<String>(employeeService.getEmployee(Integer.parseInt(request.getParameter("id"))).values());
    	request.setAttribute("employeeDetails", employeeDetails);
	    request.getRequestDispatcher("/employee_form.jsp").forward(request, response);
	}

	/**
     * Method to update Employee details
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException 
     * @throws ServletException 
     */
    private void updateEmployeeDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
    	String designation = request.getParameter("designation");
    	Double salary = Double.parseDouble(request.getParameter("salary"));
    	Long mobile = Long.parseLong(request.getParameter("mobile"));
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
        employeeService.updateEmployee(id, name, designation, salary, dob, mobile, addresses);		
	}

	/**
     * Method to create employee
     * @param request HttpServletRequest 
     * @param response HttpServletResponse
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws NumberFormatException 
     */
    public void createEmployee(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServletException, IOException {
    	if ("".equals(request.getParameter("id"))) { 
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
    	    employeeService.createEmployee(name, designation, salary,
    	    		mobile, dob, addresses);
    	    request.setAttribute("message", "Employee Created Successfully...!!!");
    	    request.getRequestDispatcher("/success.jsp").forward(request, response);
    	} else {
    		updateEmployeeDetails(request, response);
    	}
    }
       
    /**
     * Method to check whether the id is present in collection or not 
     * @param id Employee id
     * @return true if id present in database else return false
     */
    public boolean isIdExist(int id) {
    	return employeeService.isIdExist(id);
    }
    
    /**
     * Method to get the employee details based on employee id
     * @param id Employee id
     * @param request HttpServletRequest 
     * @param response HttpServletResponse 
     * @throws IOException 
     * @throws ServletException 
     */
    public void getEmployeeDetails(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Map<String,String>> employeesDetails = new ArrayList<Map<String,String>>();
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
        	request.getRequestDispatcher("employeeController/display_employee.jsp").forward(request, response);
        } else {
        	request.setAttribute("message", "No Employee exist with given id");
        	request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * Method to validate date
     * @param date User given date string
     * @return valid date
     */
    public Date isValidDate(String date) {
    	return employeeService.isValidDate(date);
    }

    /**
     * Method to validate mobile number
     * @param input user given number
     * @return Mobile number
     */
    public long isValidMobile(String input) {
        return employeeService.isValidMobile(input);
    }
    
    /**
     * Method to validate employee salary.
     * @param input user given salary
     * @return valid salary
     */
     public double isValidSalary(String input) {
         return employeeService.isValidSalary(input);
     }
     
    /**
     * This method will validate employee id
     * @param id employee id
     * @return valid employee id
     */
    public int isValidId(String id) {
        return employeeService.isValidId(id);
    }

    /**
     * Method to return all employee details present in collection
     * @param request HttpServletRequest 
     * @param response HttpServletResponse 
     * @throws IOException 
     * @throws ServletException 
     */
    public void getAllEmployeesDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Map<String,String>> employeesDetails = employeeService.getAllEmployeesDetails();
    	if (0 != employeesDetails.size()) {
    	    request.setAttribute("employeesDetails", employeesDetails);
     	    request.getRequestDispatcher("employeeController/display_employee.jsp").forward(request, response);
    	} else {
    		request.setAttribute("message", "No employee present");
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}   	 
    }

    /**
     * Method to delete the Employee based on employee id
     * @param id Employee id
     * @param request HttpServletRequest 
     * @param response HttpServletResponse 
     * @throws IOException 
     */
    public void deleteEmployee(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	PrintWriter out = response.getWriter();
    	employeeService.deleteEmployee(id);
    	out.println("Deletion Successfull...!!!");
    	
    }

    /**
     * Methode to recover deleted employee
     * @param id employee id
     * @param request HttpServletRequest 
     * @param response HttpServletResponse 
     * @throws IOException 
     */
    public void recoverEmployee(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	PrintWriter out = response.getWriter();
    	employeeService.deleteEmployee(id);
        employeeService.recoverEmployee(id);
        out.println("Recovery Successfull....!!!");
    }

    /**
     * Method to get all deleted employees
     * @return list of  deleted employees 
     */
    public List<String> getDeletedEmployees() {
        return employeeService.getDeletedEmployees();
    }

    /**
     * Methode to update the employee name
     * @param id Employee id
     * @param employeeName Name of employee
     * @return true for successful updation of name else return false
     */
//    public void updateName(int id, String employeeName) {
//    	employeeService.updateEmployee(id, employeeName,
//                null, 0l, null, 0l, "name");
//    }
//    
//    /**
//     * Method to update Employee designation
//     * @param id Employee id
//     * @param designation Employee Designation   
//     */
//    public void updateDesignation(int id, String designation) {
//    	employeeService.updateEmployee(id, null, designation,
//                0l, null, 0l, "designation");
//    }
//    
//    /**
//     * Method to update Employee salary
//     * @param id Employee id
//     * @param employeeSalary Salary of Employee
//     */
//    public void updateSalary(int id, double employeeSalary) {
//    	employeeService.updateEmployee(id, null, null,
//                employeeSalary, null, 0l, "salary");
//    }
//    
//    /**
//     * Method to update Employee date of birth
//     * @param id Employee id
//     * @param dob Employee date of birth
//     */
//    public void updateDob(int id, Date dob) {
//    	employeeService.updateEmployee(id, null, null, 0l, dob, 0l, "dob");
//    }
//    
//    /**
//     * Method to update employee mobile number
//     * @param id Employee id
//     * @param mobile Employee mobile number
//     */
//    public void updateMobile(int id, long mobile) {
//    	employeeService.updateEmployee(id, null, null,
//                0l, null, mobile, "mobile");
//    }

    /**
     * Methode to update address
     * @param addressId employee address id
     * @param employeeId employee id
     * @param addressDetails array of address details
     */
    public void updateAddress(int employeeId, int addressId, String[] addressDetails) {
        employeeService.updateAddress(employeeId, addressId, addressDetails);
    }

    /**
     * Method to get addressList of a employee
     * @param employeeId
     * @return list of employee address strings
     */
    public Map<Integer, String> getAddressList(int employeeId) {
        return employeeService.getAddressList(employeeId);
    }

    /**
     * Method to get projects basic details
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    public void getAllProjectsBasicDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         List<List<String>> projectsDetails = employeeService.getAllProjectsBasicDetails();
         request.setAttribute("projectsDetails", projectsDetails);
  	     request.getRequestDispatcher("display_available_projects.jsp").forward(request, response);
    }

    /**
     * Method to get all employee basic details
     * @return map of employee id as key and basic details as value
     */
    //public Map<Integer, String> getAllEmployeeBasicDetails() {
      //  return employeeService.getAllEmployeeBasicDetails();
    //}

    /**
     * Method to assign projects to employee
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    public void assignProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String[] projectIdStrings=request.getParameterValues("xyz");
    	List<Integer> projectsIdList = new ArrayList<Integer>();
    	for (String projectId : projectIdStrings) {
    		projectsIdList.add(Integer.parseInt(projectId));
    	} 
        employeeService.assignProject(projectsIdList, Integer.parseInt(request.getParameter("id")));
        request.setAttribute("message", "Project Assigned Successfully...!!!");
	    request.getRequestDispatcher("/success.jsp").forward(request, response);
    }

    /**
     * Method to get assigned projects details of specified employee
     * @param employeeId employee id to get the assigned projects details
     * @param request http request object
     * @param response http response object
     * @throws IOException 
     * @throws ServletException 
     */
    public void getProjectsBasicDetails(int employeeId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, String>> projectsDetails = employeeService.getProjectsBasicDetails(employeeId);
        request.setAttribute("projectsDetails", projectsDetails);
    	request.getRequestDispatcher("/display_assigned_projects.jsp").forward(request, response);
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
    public void removeProject(int employeeId, int projectId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeService.removeProject(employeeId, projectId);
        if (employeeService.removeProject(employeeId, projectId)) {
        	request.setAttribute("message", "Project Removed Successfully...!!!");
        	request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
        	request.setAttribute("message", "Project unassign Failed...!!!");
        	request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
