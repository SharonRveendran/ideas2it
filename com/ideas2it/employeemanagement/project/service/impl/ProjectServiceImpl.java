package com.ideas2it.employeemanagement.project.service.impl;
  
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeemanagement.project.dao.impl.ProjectDaoImpl;
import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.service.ProjectService;

/**
 * Implementation lass for Project service interface
 * @author Sharon V
 * @created 24-03-2021
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao = new ProjectDaoImpl();

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean createProject(String name, String managerName,
            Date startDate, Date endDate) {
        Project project = new Project(name, managerName, startDate,
               endDate, false, new ArrayList<Employee>());
        return projectDao.insertProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Map<String, String> getProjectDetails(int projectId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Map<String, String> projectDetails = new LinkedHashMap<String, String>();
        Project project = projectDao.getProject(projectId);  
        if (null != project&&!project.getIsDeleted()) {
            projectDetails.put("id", "" + project.getId());   
            projectDetails.put("name", "" + project.getName());
            projectDetails.put("managerName", "" + project.getManagerName());
            projectDetails.put("startDate", "" + project.getStartDate());
            projectDetails.put("endDate", "" + project.getEndDate());
        }
        return projectDetails;  
    }  

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean isValidId(String input) {
        int projectId;
        try {
            projectId = Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }  
        return true;   
    }  

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Map<String,String>> getAllProjectDetails(boolean isDeleted) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Project> projects = projectDao.getAllProject(isDeleted);
        List<Map<String, String>> projectsDetails = new ArrayList<Map<String, String>>();      
        if (0 != projects.size()) {
            for (Project project : projects) {
            	Map<String,String> projectDetails = new HashMap<String, String>();
            	projectDetails.put("id", "" + project.getId());
            	projectDetails.put("name", "" + project.getName());
            	projectDetails.put("managerName", "" + project.getManagerName());
            	projectDetails.put("startDate", "" + project.getStartDate());
            	projectDetails.put("endDate", "" + project.getEndDate());
            	projectsDetails.add(projectDetails);
            }
        }
        return projectsDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteProject(int projectId) {
        Project project = projectDao.getProjectWithEmployee(projectId);
        project.setIsDeleted(true);
        project.setEmployees(new ArrayList<Employee>());
        return projectDao.updateProject(project);
    }
    /**
     * {@inheritdoc}
     */
    @Override
    public boolean recoverProject(int projectId) {
        boolean recoverStatus = false;
        Project project = projectDao.getProject(projectId);
        if (null != project) {
            project.setIsDeleted(false);
            recoverStatus = projectDao.updateProject(project);
        }
        return recoverStatus;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Date isValidDate(String date) {
    	Date validDate = null;
        try {
	    validDate = Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
        return validDate;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate) {
        Project project = projectDao.getProjectWithEmployee(projectId);
        project.setId(projectId);
        project.setName(name);
        project.setManagerName(managerName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);    
        return projectDao.updateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<List<String>> getAllProjectBasicDetails() {
        List<Project> projects = projectDao.getAllProject(false);         
        List<List<String>> projectDetailsList = new ArrayList<List<String>>();
        if (null != projects) {
            for (Project project : projects) {
            	List<String> projectDetails = new ArrayList<String>();
            	projectDetails.add("" + project.getId());
            	projectDetails.add("" + project.getName());
            	projectDetailsList.add(projectDetails);
            }
        } 
        return projectDetailsList;  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Project getProject(int projectId) {
        return projectDao.getProject(projectId);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList) {
        return projectDao.getSpecifiedProjects(projectIdList);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<List<String>> getAllEmployeesDetails() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        /*Map<Integer, String> employeesDetails = new HashMap<Integer, String>();

        List<Project> projects = projectDao.getAllProjectWithEmployee();
        List<Employee> employees = employeeService.getAllEmployee
        for (Employee employee : employees) {
            employeesDetails.put(employee.getId(), "\nEmployee Id       : " 
                    + employee.getId() + "\nEmployee Name"
                    + "     : " + employee.getName() + "\nEmployee Mobile   : " 
                    + employee.getMobile());   
        }
        return employeesDetails;*/
        return employeeService.getAllEmployeeBasicDetails();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean assignEmployee(List<Integer> employeeIdList, int projectId) {
        Project project = projectDao.getProjectWithEmployee(projectId);
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employeeList = project.getEmployees();
        employeeList.addAll(employeeService.getSpecifiedEmployees(employeeIdList));        
        project.setEmployees(employeeList);
        return projectDao.updateProject(project);      
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean removeEmployee(int projectId, int employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Project project = projectDao.getProjectWithEmployee(projectId);
        if (null == project) {
            return false;
        } else {
           List<Employee> employees = new ArrayList<Employee>();  
           for (Employee employee : project.getEmployees()) {
               if (employee.getId() != employeeId) {
                   employees.add(employee);
               }
           }
           project.setEmployees(employees);
        }
        return projectDao.updateProject(project);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<Map<String, String>> getEmployeesBasicDetails(int projectId) {
        List<Map<String, String>> employeesDetails = new ArrayList<Map<String, String>>();
        Project project = projectDao.getProjectWithEmployee(projectId);
        for (Employee employee : project.getEmployees()) {
            Map<String, String> employeeDetails = new HashMap<String, String>();
            employeeDetails.put("id", "" + employee.getId());
            employeeDetails.put("name", "" + employee.getName());
            employeesDetails.add(employeeDetails);
        }
        return employeesDetails;
    }
}