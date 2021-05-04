package com.ideas2it.employeemanagement.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.employeemanagement.project.service.ProjectService;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * Class for Employee service
 * 
 * @author Sharon V
 * @created 21-03-2021
 */
@Configurable
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;
	private ProjectService projectService;

	public EmployeeServiceImpl(EmployeeDao employeeDao, ProjectService projectService) {
		this.employeeDao = employeeDao;
		this.projectService = projectService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertEmployee(Employee employee) throws EmployeeManagementException {
		employeeDao.insertEmployee(employee);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Employee getEmployee(int employeeId) throws EmployeeManagementException {
		return employeeDao.getEmployee(employeeId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Employee> getAllEmployees() throws EmployeeManagementException {
		return employeeDao.getAllEmployee();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isIdExist(int id) throws EmployeeManagementException {
		return employeeDao.isIdExist(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteEmployee(int id) throws EmployeeManagementException {
		List<Address> addresses = new ArrayList<Address>();
		Employee employee = employeeDao.getEmployeeWithProject(id);
		if (null != employee) {
			if (false == employee.getIsDeleted()) {
				employee.setIsDeleted(true);
				if (null != employee.getAddresses()) {
					for (Address address : employee.getAddresses()) {
						if (null != address) {
							address.setIsDeleted(true);
							addresses.add(address);
						}
					}
				}
				employee.setProjects(new ArrayList<Project>());
				employee.setAddresses(addresses);
				employeeDao.updateEmployee(employee);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean restoreEmployee(int id) throws EmployeeManagementException {
		List<Address> addresses = new ArrayList<Address>();
		boolean restoreStatus = false;
		Employee employee = employeeDao.getEmployee(id);
		if (null != employee) {
			if (employee.getIsDeleted()) {
				employee.setIsDeleted(false);
				if (null != employee.getAddresses()) {
					for (Address address : employee.getAddresses()) {
						if (null != address) {
							address.setIsDeleted(false);
							addresses.add(address);
						}
					}
				}
				employee.setAddresses(addresses);
				employeeDao.updateEmployee(employee);
				restoreStatus = true;
			}
		}
		return restoreStatus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEmployee(Employee employee) throws EmployeeManagementException {
		int index = 0;
		Employee existingEmployee = employeeDao.getEmployeeWithProject(employee.getId());
		if (null != employee.getAddresses()) {
			for (Address address : employee.getAddresses()) {
				address.setAddressId(existingEmployee.getAddresses().get(index).getAddressId());
				index++;
			}
		}
		employee.setProjects(existingEmployee.getProjects());
		employeeDao.updateEmployee(employee);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Project> getAvailableProject(int employeeId) throws EmployeeManagementException {
		List<Integer> assignedProjectsIds = new ArrayList<Integer>();
		List<Project> projects = new ArrayList<Project>();
		Employee employee = employeeDao.getEmployeeWithProject(employeeId);
		List<Project> allProjects = projectService.getAllProjects();
		if (null != employee) {
			List<Project> assignedProjects = employee.getProjects();
			if (null != assignedProjects) {
				for (Project project : assignedProjects) {
					assignedProjectsIds.add(project.getId());
				}
			}
			if (null != allProjects) {
				for (Project project : allProjects) {
					if (!(assignedProjectsIds.contains(project.getId()))) {
						projects.add(project);
					}
				}
			}
		}
		return projects;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assignProject(List<Integer> projectIdList, int employeeId) throws EmployeeManagementException {
		Employee employee = employeeDao.getEmployeeWithProject(employeeId);
		if (null != employee) {
			List<Project> projects = employee.getProjects();
			if (null != projects) {
				projects.addAll(projectService.getSpecifiedProjects(projectIdList));
				employee.setProjects(projects);
			}
		}
		employeeDao.updateEmployee(employee);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Project> getAssignedProjects(int employeeId) throws EmployeeManagementException {
		return employeeDao.getEmployeeWithProject(employeeId).getProjects();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStackTrace(Exception e) {
		StackTraceElement[] stack = e.getStackTrace();
		String logMessage = e.getMessage();
		for (StackTraceElement stackTraceElement : stack) {
			logMessage = logMessage + "\n" + stackTraceElement.toString();
		}
		return logMessage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unAssignProject(List<Integer> projectsIdList, int employeeId) throws EmployeeManagementException {
		// ProjectService projectService = new ProjectServiceImpl();
		List<Integer> projectsIds = new ArrayList<Integer>();
		List<Project> newProjects = new ArrayList<Project>();
		Employee employee = employeeDao.getEmployeeWithProject(employeeId);
		if (null != employee) {
			List<Project> assignedProjects = employee.getProjects();
			List<Project> projects = projectService.getSpecifiedProjects(projectsIdList);
			for (Project project : projects) {
				projectsIds.add(project.getId());
			}
			for (Project project : assignedProjects) {
				if (!(projectsIds.contains(project.getId()))) {
					newProjects.add(project);
				}
			}
			employee.setProjects(newProjects);
			employeeDao.updateEmployee(employee);
		}
	}
}
