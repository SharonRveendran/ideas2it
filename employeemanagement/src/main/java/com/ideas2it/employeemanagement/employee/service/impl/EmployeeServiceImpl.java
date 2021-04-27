package com.ideas2it.employeemanagement.employee.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
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
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeServiceImpl.class); 
	
    /**
     * {@inheritDoc}
     */
    @Override
    public void createEmployee(String name, String designation, double salary,
            long mobile, Date dob, List<String[]> employeeAddressList) throws EmployeeManagementException {
    	try {
    		List<Address> employeeAddresses = new ArrayList<Address>();
    		for (String employeeAddressArray[] : employeeAddressList) {
    			Address employeeAddress = new Address(employeeAddressArray[0],
    					employeeAddressArray[1], employeeAddressArray[2],
    					employeeAddressArray[3], employeeAddressArray[4],                                                 
                        employeeAddressArray[5],false);
    			employeeAddresses.add(employeeAddress);
    		} 
    		Employee employee = new Employee(name, designation, salary, mobile, dob, 
    				employeeAddresses,false); 
    		employeeDao.insertEmployee(employee);  
    	} catch(ArrayIndexOutOfBoundsException e) {
    		logger.logError(getStackTrace(e));
    		throw new EmployeeManagementException("Something went wrong...!!!");
    	}
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
    public Map<String, String> getEmployee(int id) throws EmployeeManagementException {    
		Employee employee = employeeDao.getEmployee(id);
		Map<String, String> employeeDetails = new LinkedHashMap<String, String>();
		try {
			if (null != employee) {
				if (!employee.getIsDeleted()) {
					employeeDetails.put("id", "" + employee.getId());
					employeeDetails.put("name", "" + employee.getName());
					employeeDetails.put("dob", "" + employee.getDob());
					employeeDetails.put("mobile", "" + employee.getMobile());
					employeeDetails.put("designation", "" + employee.getDesignation());
					employeeDetails.put("salary", "" + employee.getSalary());
					Address address1 = employee.getAddresses().get(0);
					employeeDetails.put("permanentDoorNumber", "" + address1.getDoorNumber());
					employeeDetails.put("permanentStreet", "" + address1.getStreet());
					employeeDetails.put("permanentDistrict", "" + address1.getDistrict());
					employeeDetails.put("permanentState", "" + address1.getState());
					employeeDetails.put("permanentCountry", "" + address1.getCountry());
					if (0 < employee.getAddresses().size()) {
						Address address2 = employee.getAddresses().get(1);
						employeeDetails.put("temporaryDoorNumber", "" + address2.getDoorNumber());
						employeeDetails.put("temporaryStreet", "" + address2.getStreet());
						employeeDetails.put("temporaryDistrict", "" + address2.getDistrict());
						employeeDetails.put("temporaryState", "" + address2.getState());
						employeeDetails.put("temporaryCountry", "" + address2.getCountry());
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			logger.logError(getStackTrace(e));
			throw new EmployeeManagementException("Something went wrong...!!!");
		}
		return employeeDetails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public List<Map<String, String>> getAllEmployeesDetails() throws EmployeeManagementException {
		List<Map<String, String>> employeesDetails = new ArrayList<Map<String, String>>();
		try {
			List<Employee> employees = employeeDao.getAllEmployee();
			for (Employee employee : employees) {
				Map<String, String> employeeDetails = new HashMap<String, String>();
				employeeDetails.put("id", "" + employee.getId());
				employeeDetails.put("name", "" + employee.getName());
				employeeDetails.put("dob", "" + employee.getDob());
				employeeDetails.put("mobile", "" + employee.getMobile());
				employeeDetails.put("designation", "" + employee.getDesignation());
				employeeDetails.put("salary", "" + employee.getSalary());
				employeeDetails.put("permanentAddress", "" + employee.getAddresses().get(0).toString());
				employeeDetails.put("temporaryAddress", "" + employee.getAddresses().get(1).toString());
				employeesDetails.add(employeeDetails);
			}
		} catch (IndexOutOfBoundsException e) {
			logger.logError(getStackTrace(e));
			throw new EmployeeManagementException("Something went wrong...!!!");
		}
		return employeesDetails;
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean deleteEmployee(int id) throws EmployeeManagementException {
		boolean deleteStatus = false;
		List<Address> addresses = new ArrayList<Address>();
		Employee employee = employeeDao.getEmployeeWithProject(id);
			if (null != employee) {
				if (false == employee.getIsDeleted()) {
					employee.setIsDeleted(true);
					for (Address address : employee.getAddresses()) {
						if (null != address) {
							address.setIsDeleted(true);
							addresses.add(address);
						}
					}
					employee.setProjects(new ArrayList<Project>());
					employee.setAddresses(addresses);
					deleteStatus = employeeDao.updateEmployee(employee);
					if (!deleteStatus) {
						throw new EmployeeManagementException("Deletion Failed...!!!");
					}
				}
			}
		return deleteStatus;
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
					for (Address address : employee.getAddresses()) {
						if (null != address) {
							address.setIsDeleted(false);
							addresses.add(address);
						}
					}
					employee.setAddresses(addresses);
					restoreStatus = employeeDao.updateEmployee(employee);
					if (!restoreStatus) {
						throw new EmployeeManagementException("Restore Failed...!!!");
					}
				}
			}
		return restoreStatus;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEmployee(int id, String name, String designation,
            double salary, Date dob, long mobile, List<String[]> addresses) 
            		throws EmployeeManagementException {
    	try {
    		Employee oldEmployee = employeeDao.getEmployeeWithProject(id);
    		List<Address> addressList = new ArrayList<Address>();
    		int index = 0;
    		for (String[] addressString : addresses) {
    			Address address = new Address(addressString[0], addressString[1], addressString[2],
    					addressString[3], addressString[4], addressString[5],false);
    			address.setAddressId(oldEmployee.getAddresses().get(index).getAddressId());
    			index++;
    			addressList.add(address);
    		}
    		Employee employee = new Employee(id, name, designation, salary, mobile,
    				dob, addressList, false);
    		employee.setProjects(employeeDao.getEmployeeWithProject(id).getProjects());
    		if (!employeeDao.updateEmployee(employee)) {
    			throw new EmployeeManagementException("Something went wrong while updating...!!!");
    		}
    	} catch(ArrayIndexOutOfBoundsException e) {
    		logger.logError(getStackTrace(e));
    		throw new EmployeeManagementException("Something went wrong...!!!");
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  List<List<String>> getAllProjectsBasicDetails() throws EmployeeManagementException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProjectBasicDetails();
    }
 
    /**
     * {@inheritDoc} 
     */
    @Override
	public List<List<String>> getAllEmployeeBasicDetails() throws EmployeeManagementException {
		List<Employee> employees = employeeDao.getAllEmployee();
		List<List<String>> employeesDetails = new ArrayList<List<String>>();
			for (Employee employee : employees) {
				if (null != employee) {
					List<String> employeeDetails = new ArrayList<String>();
					employeeDetails.add("" + employee.getId());
					employeeDetails.add("" + employee.getName());
					employeesDetails.add(employeeDetails);
				}
			}
     		return employeesDetails;
	}

    /**
     * {@inheritDoc} 
     */
    @Override
	public boolean assignProject(List<Integer> projectIdList, int employeeId) throws EmployeeManagementException {
		Employee employee = employeeDao.getEmployeeWithProject(employeeId);
		if (null != employee) {
			ProjectService projectService = new ProjectServiceImpl();
			List<Project> projects = employee.getProjects();
			projects.addAll(projectService.getSpecifiedProjects(projectIdList));
			employee.setProjects(projects);
		}
		return employeeDao.updateEmployee(employee);
	}

    /**
     * {@inheritDoc} 
     */
    @Override
	public List<Map<String, String>> getProjectsBasicDetails(int employeeId)
			throws EmployeeManagementException {
		Employee employee = employeeDao.getEmployeeWithProject(employeeId);
		List<Map<String, String>> projectsDetails = new ArrayList<Map<String, String>>();
		if (null != employee) {
			for (Project project : employee.getProjects()) {
				Map<String, String> projectDetails = new HashMap<String, String>();
				projectDetails.put("id", "" + project.getId());
				projectDetails.put("name", "" + project.getName());
				projectsDetails.add(projectDetails);
			}
		}
		return projectsDetails;
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public boolean removeProject(int employeeId, int projectId) throws EmployeeManagementException {
		boolean removeStatus = false;
		Employee employee = employeeDao.getEmployeeWithProject(employeeId);
			if (null != employee) {
				List<Project> projects = new ArrayList<Project>();
				for (Project project : employee.getProjects()) {
					if (null != project) {
						if (project.getId() != projectId) {
							projects.add(project);
						} else {
							removeStatus = true;
						}
					}
				}
				employee.setProjects(projects);
			}
			if (!employeeDao.updateEmployee(employee)) {
				removeStatus = false;
			}
		return removeStatus;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployeeObject(int id) throws EmployeeManagementException {
        return employeeDao.getEmployee(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList)
    		throws EmployeeManagementException {
        return employeeDao.getSpecifiedEmployees(employeeIdList);
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
}
