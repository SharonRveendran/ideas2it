
package com.ideas2it.employeemanagement.project.service.impl;
  
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        Project project = projectDao.getProject(projectId);
        if (null == project) {
            return null;
        } else {
            return project.toString();
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAllProject(int isDeleted) {
        List<Project> projects = projectDao.getAllProject(isDeleted);
        List<String> projectDetailsList = new ArrayList<String>();
        if (null == projects) {
            return null;
        } else {
            for (Project project : projects) {
                projectDetailsList.add(project.toString());
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
    public List<String> getAllEmployeesDetails() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        List<String> employeesDetails = new ArrayList<String>();
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            employeesDetails.add("\nEmployee Id       : " + employee.getId() + "\nEmployee Name"
                    + "     : " + employee.getName() + "\nEmployee Mobile   : " + employee.getMobile());   
        }
        return employeesDetails;
    }

    public boolean assignEmployee(int employeeId, int projectId) {
        return projectDao.assignEmployee(employeeId, projectId);
    }
}