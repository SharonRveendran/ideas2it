package com.ideas2it.employeemanagement.employee.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.exceptions.CreateFailException;
import com.ideas2it.exceptions.DeleteFailException;
import com.ideas2it.exceptions.FetchFailException;
import com.ideas2it.exceptions.NoIdException;
import com.ideas2it.exceptions.UpdateFailException;
import com.ideas2it.employeemanagement.project.service.ProjectService;

/**
 * Class for Employee service
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
    /**
     * {@inheritDoc}
     * @throws CreateFailException 
     */
    @Override
    public void createEmployee(String name, String designation, double salary,
            long mobile, Date dob, List<String[]> employeeAddressList) throws CreateFailException {
        Address employeeAddress;
        List<Address> employeeAddresses = new ArrayList<Address>();
        for (String employeeAddressArray[] : employeeAddressList) {
            employeeAddress = new Address(employeeAddressArray[0],
                        employeeAddressArray[1], employeeAddressArray[2],
                        employeeAddressArray[3], employeeAddressArray[4],                                                 
                        employeeAddressArray[5],false);
            employeeAddresses.add(employeeAddress);
        } 
    	Employee employee = new Employee(name, designation, salary, mobile, dob, employeeAddresses,false); 
        employeeDao.insertEmployee(employee);   
    }

    /**
     * {@inheritDoc}
     * @throws NoIdException 
     */
    @Override
    public void isIdExist(int id) throws NoIdException {
        if (!employeeDao.isIdExist(id)) {
        	throw new NoIdException("Given id not exist in database");
        }
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getEmployee(int id) throws FetchFailException {    
        Employee employee = employeeDao.getEmployee(id);
        Map<String, String> employeeDetails = new LinkedHashMap<String, String>();
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
      	 			Address address = employee.getAddresses().get(1);
      	 			employeeDetails.put("temporaryDoorNumber", "" + address.getDoorNumber());
      	 			employeeDetails.put("temporaryStreet", "" + address.getStreet());
      	 			employeeDetails.put("temporaryDistrict", "" + address.getDistrict());
      	 			employeeDetails.put("temporaryState", "" + address.getState());
      	 			employeeDetails.put("temporaryCountry", "" + address.getCountry());
      	 		}
      	 	}
        }
        return employeeDetails;  
    }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Map<String, String>> getAllEmployeesDetails() throws FetchFailException {
        List<Map<String, String>> employeesDetails = new ArrayList<Map<String, String>>();
        List<Employee> employees = employeeDao.getAllEmployee();
        for(Employee employee : employees) {
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
        return  employeesDetails;
    }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     * @throws DeleteFailException 
     */
    @Override
    public boolean deleteEmployee(int id) throws FetchFailException, DeleteFailException {
    	boolean deleteStatus = false;
        List<Address> addresses = new ArrayList<Address>();
        Employee employee = employeeDao.getEmployeeWithProject(id);
        if (null != employee) {
        	if (false == employee.getIsDeleted()) {
        		employee.setIsDeleted(true);
        		for (Address address : employee.getAddresses()) {
        			address.setIsDeleted(true);
        			addresses.add(address); 
        		}
        		employee.setProjects(new ArrayList<Project>());
        		employee.setAddresses(addresses);   
        		deleteStatus = employeeDao.updateEmployee(employee);
        		if (!deleteStatus) {
        			throw new DeleteFailException("Deletion Failed...!!!");
        		}
        	}
        }
        return deleteStatus;
    }   

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public boolean restoreEmployee(int id) throws FetchFailException {
        List<Address> addresses = new ArrayList<Address>();
        boolean restoreStatus = false;
        Employee employee = employeeDao.getEmployee(id);
        if (null != employee) {
            if (employee.getIsDeleted()) {    
                employee.setIsDeleted(false);
                for (Address address : employee.getAddresses()) {
                    address.setIsDeleted(false);
                    addresses.add(address);
                }
                employee.setAddresses(addresses);
                if (employeeDao.updateEmployee(employee)) {
                    restoreStatus = true;
                }
            }
        }
        return restoreStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List <String> getDeletedEmployees() {
        List <String> deletedEmployees = new ArrayList <String> ();
        List <Employee> deletedEmployeesObjects = employeeDao.getDeletedEmployees();
        for (int index = 0; index < deletedEmployeesObjects.size(); index++) {
            deletedEmployees.add(deletedEmployeesObjects.get(index).toString());
        }
        return deletedEmployees;
    }  
    
    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     * @throws UpdateFailException 
     */
    @Override
    public void updateEmployee(int id, String name, String designation,
            double salary, Date dob, long mobile, List<String[]> addresses) throws FetchFailException, UpdateFailException {
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
        Employee employee = new Employee(id, name, designation, salary, mobile,dob, addressList, false);
        employee.setProjects(employeeDao.getEmployeeWithProject(id).getProjects());
    	if (!employeeDao.updateEmployee(employee)) {
    		throw new UpdateFailException("Something went wrong while updating...!!!");
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map <Integer, String> getAddressList(int employeeId) {
        Map <Integer, String> addressList = new HashMap <Integer, String> ();
        Map <Integer, Address> addresses = employeeDao.getAddressList(employeeId);
        List <Integer> addressIdList = new ArrayList <Integer> (addresses.keySet());
        for(int index = 0; index < addressIdList.size(); index++) {
           addressList.put((addressIdList.get(index)),
                   (addresses.get(addressIdList.get(index))).toString());
        }
        return addressList;
    } 

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public  List<List<String>> getAllProjectsBasicDetails() throws FetchFailException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProjectBasicDetails();
    }
 
    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<List<String>> getAllEmployeeBasicDetails() throws FetchFailException {
        List<Employee> employees = employeeDao.getAllEmployee();
        List<List<String>> employeesDetails = new ArrayList<List<String>>();
        for (Employee employee : employees) {
        	List<String> employeeDetails = new ArrayList<String>();
        	employeeDetails.add("" + employee.getId());
        	employeeDetails.add("" + employee.getName());
        	employeesDetails.add(employeeDetails);
        } 
        return employeesDetails;            
    } 

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public boolean assignProject(List<Integer> projectIdList, int employeeId) throws FetchFailException {
        Employee employee = employeeDao.getEmployeeWithProject(employeeId);
        ProjectService projectService = new ProjectServiceImpl();
        List<Project> projects = employee.getProjects();
        projects.addAll(projectService.getSpecifiedProjects(projectIdList));        
        employee.setProjects(projects);
        return employeeDao.updateEmployee(employee);
    }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Map<String, String>> getProjectsBasicDetails(int employeeId) throws FetchFailException {
        Employee employee = employeeDao.getEmployeeWithProject(employeeId);
        List<Map<String, String>> projectsDetails = new ArrayList<Map<String, String>>();
        for (Project project : employee.getProjects()) {     
           Map<String, String> projectDetails = new HashMap<String, String>();
           projectDetails.put("id", "" + project.getId());
           projectDetails.put("name", "" + project.getName());
           projectsDetails.add(projectDetails);
        }
        return projectsDetails;
    }

    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public boolean removeProject(int employeeId, int projectId) throws FetchFailException {
    	boolean removeStatus = false;
    	Employee employee = employeeDao.getEmployeeWithProject(employeeId);
    	if (null != employee) {
    		List<Project> projects = new ArrayList<Project>();
    		for (Project project : employee.getProjects()) {
    			if (project.getId() != projectId) {
    				projects.add(project);
    			} else {
    				removeStatus = true;
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
     * @throws FetchFailException 
     */
    @Override
    public Employee getEmployeeObject(int id) throws FetchFailException {
        return employeeDao.getEmployee(id);
    }
    
    /**
     * {@inheritDoc}
     * @throws FetchFailException 
     */
    @Override
    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList) throws FetchFailException {
        return employeeDao.getSpecifiedEmployees(employeeIdList);
    }
}
