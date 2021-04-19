package com.ideas2it.employeemanagement.project.service.impl;
  
import java.sql.Date;
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
     * {@inheritDoc}
     */
    @Override
    public boolean createProject(String name, String managerName,
            Date startDate, Date endDate) {
        Project project = new Project(name, managerName, startDate,
               endDate, false, new ArrayList<Employee>());
        return projectDao.insertProject(project);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getProjectDetails(int projectId) {
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
     * {@inheritDoc}
     */
    @Override
    public List<Map<String,String>> getAllProjectDetails(boolean isDeleted) {
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
     * {@inheritDoc}
     */
    @Override
    public boolean deleteProject(int projectId) {
    	boolean deleteStatus = false;
        Project project = projectDao.getProjectWithEmployee(projectId);
        if (null != project) {
        	if (false == project.getIsDeleted()) {
                project.setIsDeleted(true);
                project.setEmployees(new ArrayList<Employee>());
                System.out.println(project.getIsDeleted()+"in service delete");
                deleteStatus = projectDao.updateProject(project);
            }
        }
        return deleteStatus;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate) {
    	boolean updateStatus = false;
    	Project project = projectDao.getProjectWithEmployee(projectId);
    	
        if (null != project) {
        	project.setId(projectId);
        	project.setName(name);
        	project.setManagerName(managerName);
        	project.setStartDate(startDate);
        	project.setEndDate(endDate); 
        	System.out.println(project.getIsDeleted()+"in service update");
        	updateStatus = projectDao.updateProject(project);
        }
        return updateStatus;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean restoreProject(int projectId) {
        boolean restoreStatus = false;
        Project project = projectDao.getProject(projectId);
        if (null != project) {
        	if (project.getIsDeleted()) {
                project.setIsDeleted(false);
                restoreStatus = projectDao.updateProject(project);
        	}
        }
        return restoreStatus;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public Project getProject(int projectId) {
        return projectDao.getProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getSpecifiedProjects(List<Integer> projectIdList) {
        return projectDao.getSpecifiedProjects(projectIdList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<String>> getAllEmployeesDetails() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getAllEmployeeBasicDetails();
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public boolean removeEmployee(int projectId, int employeeId) {
    	boolean removeStatus = false;
        Project project = projectDao.getProjectWithEmployee(projectId);
        if (null != project) {
           List<Employee> employees = new ArrayList<Employee>();  
           for (Employee employee : project.getEmployees()) {
               if (employee.getId() != employeeId) {
                   employees.add(employee);
               } else {
            	   removeStatus = true;
               }
           }
           project.setEmployees(employees);
        }
        if (!projectDao.updateProject(project)) {
        	removeStatus = false;
        }
        return removeStatus;
    }

    /**
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isIdExist(int projectId) {
		return projectDao.isIdExist(projectId);
	}
}