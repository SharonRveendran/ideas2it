package com.ideas2it.employeemanagement.employee.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * Employee controller servlet class
 * @author Sharon V
 * @created 21-04-2021
 */
@Controller
public class EmployeeController {
//	Resource r=new ClassPathResource("EmployeeManagement-servlet.xml");  
//  BeanFactory factory=new XmlBeanFactory(r);  
//	private EmployeeService employeeService = (EmployeeService)factory.getBean("employeeService");  //new EmployeeServiceImpl();
	

	//private EmployeeService employeeService = new EmployeeServiceImpl();

	ApplicationContext context =   new ClassPathXmlApplicationContext("EmployeeManagement-servlet.xml");

	private EmployeeService employeeService = (EmployeeService)context.getBean("employeeService");

	
	EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeController.class);

	/**
	 * Method to get the index page
	 * @return index page name
	 */
    @RequestMapping("/")
    public String getIndexPage(){
    	return "index";
    }
    
    /**
     * Method to get the employee management page
     * @return employee management page name
     */
    @RequestMapping("/employee_management")
    public String getEmployeeManagementPage(){
		return "employeemanagement";
    }
    
    /**
     * Method to get the employee form page name
     * @param employee employee model object
     * @return employee form page name
     */
    @RequestMapping("/employee_form")
    public String getEmployeeForm(@ModelAttribute("employee") Employee employee) {
		return "employee_form";
    }
    
    /**
     * Method to insert Employee
     * @param employee employee model object
     * @return ModelAndView object containing output page name and response message
     */
    @RequestMapping("/create_employee")
    public ModelAndView insertEmployee(Employee employee){
    	ModelAndView modelAndView = new ModelAndView();
		try {
			if (0 == employee.getId()) {
				employeeService.insertEmployee(employee);
				modelAndView.addObject("message", "Employee Created Successfully");
			} else {
				employeeService.updateEmployee(employee);
				modelAndView.addObject("message", "Employee Updated Successfully");
			}
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("output");
		return modelAndView;
    }
    
    /**
     * Method to get specified employee
     * @param employeeId employee id 
     * @return ModelAndView object containing employee object and output page name
     */
    @PostMapping("/display_employee")
    public ModelAndView getEmployee(@RequestParam(value="id")int employeeId) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
			if (employeeService.isIdExist(employeeId)) {
				List<Employee> employees = new ArrayList<Employee>();
				employees.add(employeeService.getEmployee(employeeId));
				modelAndView.addObject("employees", employees);
				modelAndView.setViewName("display_employee");
    		} else {
    			modelAndView.addObject("message", "No Employee exist with given id...!!!");
        		modelAndView.setViewName("output");
    		}
    	} catch(EmployeeManagementException e) {
    		modelAndView.addObject("message",  e.getMessage());
    		modelAndView.setViewName("output");
    	}
		return modelAndView;
    }
    
    /**
     * Method to get all employee objects
     * @return ModelAndView object containing all employee object as list and output page name
     */
    @RequestMapping("/display_all_employee")
    public ModelAndView getAllEmployees() {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			List<Employee> employees = employeeService.getAllEmployees();
			if (employees.isEmpty()) {
				modelAndView.addObject("message", "No Employee exist in database...!!!");
				modelAndView.setViewName("output");
			} else {
				modelAndView.addObject("employees", employees);
				modelAndView.setViewName("display_employee");
			}
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("output");
		}
		return modelAndView;
    }
    
    /**
     * Method to delete employee
     * @param employeeId Employee id
     * @return ModelAndView object contain response message
     */
    @RequestMapping("/delete_employee/{employeeId}")
    public ModelAndView deleteEmployee(@PathVariable int employeeId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			employeeService.deleteEmployee(employeeId);
			modelAndView = getAllEmployees();
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", "Employee Deletion Failed");
			modelAndView.setViewName("output");
		}
		return modelAndView;
    }
    
    /**
     * Method to get employee restore page
     * @return restore page name 
     */
    @RequestMapping("/employee_restore_page")
    public String getEmployeeRestorePage() {
		return "restore_employee";
    }
    
    /**
     * Method to restore employee
     * @param employeeId Employee id
     * @return ModelAndView object containing response message
     */
    @RequestMapping("/restore_employee")
    public ModelAndView restoreEmployee(@RequestParam("id") int employeeId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (employeeService.restoreEmployee(employeeId)) {
				modelAndView = getEmployee(employeeId);
				modelAndView.addObject("message", "Employee Restored SuccessFully");
			} else {
				modelAndView.addObject("message", "Employee Restore Failed...!!!");
				modelAndView.setViewName("output");
			}
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", "Employee Restore Failed...!!!");
			modelAndView.setViewName("output");
		}
		return modelAndView;
    }
    
    /**
     * Method to get the update page
     * @param employee employee object
     * @param employeeId employee id
     * @return ModelAndView object containing employee object and employee form file name
     */
    @RequestMapping("/update_employee/{employeeId}")
    public  ModelAndView getUpdatePage(@ModelAttribute("employee") Employee employee, 
    		@PathVariable int employeeId) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
    	modelAndView.addObject("employee", employeeService.getEmployee(employeeId));
    	modelAndView.setViewName("employee_form");
    	} catch(EmployeeManagementException e) {
    		modelAndView.addObject("message", "Something went wrong");
			modelAndView.setViewName("output");
    	}
    	return modelAndView;
    }
    
    /**
     * Method to get the available projects to assign employee 
     * @param employeeId employee id
     * @return ModelAndView object containing available project objects
     */
    @RequestMapping("/display_available_projects/{employeeId}")
    public  ModelAndView getAvailableProjects(@PathVariable int employeeId) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
    	    List<Project> projects = employeeService.getAvailableProject(employeeId);
    	    if (projects.isEmpty()) {
    	    	modelAndView.addObject("message", "No projects Available...");
    			modelAndView.setViewName("output");
    	    } else {
    	    	modelAndView.addObject("projects", projects);
    	    	modelAndView.addObject("employeeId", employeeId);
    			modelAndView.setViewName("display_available_projects");
    	    }
    	} catch(EmployeeManagementException e) {
    		modelAndView.addObject("message", "Something went wrong");
			modelAndView.setViewName("output");
    	}
    	return modelAndView;
    }

	/**
	 * Method to assign projects to employee
	 * @param employeeId Employee id
	 * @param request http request object
	 */
    @PostMapping("/assign_project/{employeeId}")
	private ModelAndView assignProject(@PathVariable int employeeId, HttpServletRequest request) {
			
		ModelAndView modelAndView = new ModelAndView();
		try {
			String[] projectIdStrings = request.getParameterValues("projects");
			if (null == projectIdStrings) {
				modelAndView.addObject("message", "Select atleast 1 project...");
				modelAndView.setViewName("output");
			} else {
				List<Integer> projectsIdList = new ArrayList<Integer>();
				for (String projectId : projectIdStrings) {
					projectsIdList.add(Integer.parseInt(projectId));
				}
				employeeService.assignProject(projectsIdList, employeeId);
				modelAndView.addObject("message", "Project assigned succsessfully....");
				modelAndView.setViewName("output");
			}
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("output");
		}
		return modelAndView;
	}
    
    /**
     * Method to get assigned projects
     * @param employeeId employee id
     * @return ModelAndView object containing assigned projects objects and display file name
     */
    @RequestMapping("/display_assigned_projects/{employeeId}")
    public  ModelAndView getAssignedProjects(@PathVariable int employeeId) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
    	    List<Project> assignedProjects = employeeService.getAssignedProjects(employeeId);
    	    System.out.println(assignedProjects);
    	    if (assignedProjects.isEmpty()) {
    	    	modelAndView.addObject("message", "No projects Assigned for given Employee...");
    			modelAndView.setViewName("output");
    	    } else {
    	    	modelAndView.addObject("projects", assignedProjects);
    			modelAndView.setViewName("display_assigned_projects");
    	    }
    	} catch(EmployeeManagementException e) {
    		modelAndView.addObject("message", "Something went wrong");
			modelAndView.setViewName("output");
    	}
    	return modelAndView;
    }
    
    /**
     * Method to get unassign page
     * @param employeeId employee id
     * @return ModelAndView object containing assigned projects objects and unassign file name
     */
    @RequestMapping("/get_assigned_projects/{employeeId}")
    public  ModelAndView getUnAssignPage(@PathVariable int employeeId) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
    	    List<Project> projects = employeeService.getAssignedProjects(employeeId);
    	    if (projects.isEmpty()) {
    	    	modelAndView.addObject("message", "No projects Assigned for given employee...");
    			modelAndView.setViewName("output");
    	    } else {
    	    	modelAndView.addObject("projects", projects);
    	    	modelAndView.addObject("employeeId", employeeId);
    			modelAndView.setViewName("unassign_project");
    	    }
    	} catch(EmployeeManagementException e) {
    		modelAndView.addObject("message", "Something went wrong");
			modelAndView.setViewName("output");
    	}
    	return modelAndView;
    }
    
    /**
	 * Method to remove projects of employee
	 * @param employeeId employee id
	 * @param request http request object
	 * @return ModelAndView object containing response message and response file name
	 */
    @PostMapping("/unassign_project/{employeeId}")
	private ModelAndView unassignProject(@PathVariable int employeeId, HttpServletRequest request) {			
		ModelAndView modelAndView = new ModelAndView();
		try {
			String[] projectIdStrings = request.getParameterValues("projects");
			if (null == projectIdStrings) {
				modelAndView.addObject("message", "Select atleast 1 project...");
				modelAndView.setViewName("output");
			} else {
				List<Integer> projectsIdList = new ArrayList<Integer>();
				for (String projectId : projectIdStrings) {
					projectsIdList.add(Integer.parseInt(projectId));
				}
				employeeService.unAssignProject(projectsIdList, employeeId);
				modelAndView.addObject("message", "Project removed succsessfully....");
				modelAndView.setViewName("output");
			}
		} catch (EmployeeManagementException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("output");
		}
		return modelAndView;
	}
}
