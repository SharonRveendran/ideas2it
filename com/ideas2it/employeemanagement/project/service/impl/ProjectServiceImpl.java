
package com.ideas2it.employeemanagement.project.service.impl;
  
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public boolean createProject(String name, String managerName,
            Date startDate, Date endDate) {
        Project project = new Project(name, managerName, startDate,
               endDate,new ArrayList<Employee>());
        return projectDao.insertProject(project);
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
    public String getProject(int projectId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        String projectDetails = "";
        Project project = projectDao.getProject(projectId);
        List<Employee> employees = new ArrayList<Employee>();
        List<Integer> employeeIdList = projectDao.getEmployeesId(projectId);
        for (int employeeId : employeeIdList) {
            employees.add(employeeService.getEmployeeObject(employeeId));
        }     
        if (null == project) {
            return null;
        } else {
            projectDetails = project.toString() + "\n";
            if (0 != employees.size()) {
                projectDetails = projectDetails + "\n........... LIST OF ASSIGNED "
                        + "EMPLOYEES DETAILS ............\n";
                for (Employee employee : employees) {
                    projectDetails = projectDetails + "\nEmployee Id       : " + 
                            employee.getId() + "\nEmployee Name"
                            + "     : " + employee.getName() + "\nEmployee Mobile   : " 
                            + employee.getMobile() + "\n";
                }
            }   
            return projectDetails;
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAllProject(int isDeleted) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Project> projects = projectDao.getAllProject(isDeleted);
        List<String> projectDetailsList = new ArrayList<String>();
        String projectDetails = "";
        if (null == projects) {
            return null;
        } else {
            for (Project project : projects) {
                List<Employee> employees = new ArrayList<Employee>();
                List<Integer> employeeIdList = projectDao.getEmployeesId(project.getId());
                for (int employeeId : employeeIdList) {
                    employees.add(employeeService.getEmployeeObject(employeeId));
                }
                projectDetails = project.toString() + "\n";
                if (0 != employees.size()) {
                    projectDetails = projectDetails + "\n........... LIST OF ASSIGNED "
                        + "EMPLOYEES DETAILS ............\n";
                    for (Employee employee : employees) {if (null != employee){
                        projectDetails = projectDetails + "\nEmployee Id       : " + 
                            employee.getId() + "\nEmployee Name"
                            + "     : " + employee.getName() + "\nEmployee Mobile   : " 
                            + employee.getMobile() + "\n";}
                    }
                }   
                projectDetailsList.add(projectDetails);
            }
        } 
        return projectDetailsList;   
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteProject(int projectId) {
        return projectDao.deleteProject(projectId);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean recoverProject(int projectId) {
        return projectDao.recoverProject(projectId);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean updateProject(int projectId, String name, String managerName,
            Date startDate, Date endDate, String option) {
        Project project = projectDao.getProject(projectId);
        if (null == project) {
            return false;
        } else {
            switch (option) {
                case "name":
                    project.setName(name);
                    break;
                case "manager name":
                    project.setManagerName(managerName);
                    break;
                case "start date":
                    project.setStartDate(startDate);
                    break;
                case "end date":
                    project.setEndDate(endDate);
            }
        }
        return projectDao.updateProject(project, option);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Map<Integer, String> getAllEmployeesDetails() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        Map<Integer, String> employeesDetails = new HashMap<Integer, String>();
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            employeesDetails.put(employee.getId(), "\nEmployee Id       : " 
                    + employee.getId() + "\nEmployee Name"
                    + "     : " + employee.getName() + "\nEmployee Mobile   : " 
                    + employee.getMobile());   
        }
        return employeesDetails;
    }

    public boolean assignEmployee(List<Integer> employeeIdList, int projectId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<Employee> employeeList = new ArrayList<Employee>();
        for (int employeeId : employeeIdList) {
            employeeList.add(employeeService.getEmployeeObject(employeeId));
        }
        Project project = projectDao.getProject(projectId);
        project.setEmployeeList(employeeList);
        return projectDao.assignEmployee(project);      
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Map<Integer, String> getAllProjectBasicDetails() {
        List<Project> projects = projectDao.getAllProject(0); 
        Map<Integer, String> projectDetailsList = new HashMap<Integer, String>();
        for (Project project : projects) {
            projectDetailsList.put(project.getId(), "\nProject id   : " 
                    + project.getId() + "\nProject Name : " + project.getName());
        } 
        return projectDetailsList;  
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Project getProjectObject(int projectId) {
        return projectDao.getProject(projectId);
    }
}