package com.ideas2it.employeemanagement.employee.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ideas2it.employeemanagement.project.service.ProjectService;

/**
 * Class for Employee service
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
    /**
     * {@inheritdoc}
     */
    @Override
    public void createEmployee(String name, String designation, double salary,
            long mobile, Date dob, List<String[]> employeeAddressList) {
        Address employeeAddress;
        String addressType;
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
     * {@inheritdoc}
     */
    @Override
    public boolean isIdExist(int id) {
        return employeeDao.isIdExist(id);
    }
        
    /**
     * {@inheritdoc}
     */
    @Override
    public Date isValidDate(String date) {
    	Date dob = null;
        try {
	    dob = Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
        return dob;
    }
 
    /**
     * {@inheritdoc}
     */
    @Override
    public long isValidMobile(String input) {
        long mobile;
        if (Pattern.matches("[7-9][0-9]{9}", input)) {
       	    try {
                mobile = Long.parseLong(input); 
            } catch (NumberFormatException e) {
                return 0;
            }
      	} else {
      	   return 0;
      	}
        return mobile;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public double isValidSalary(String input) {
        double employeeSalary;
        try {
            employeeSalary = Double.parseDouble(input);          
        } catch (NumberFormatException e) {
            return 0;
        }
        return employeeSalary;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public int isValidId(String id) {
        int employeeId;
        try {
 	    employeeId = Integer.parseInt(id); 
    	} catch (NumberFormatException e) {
            return 0;
 	}
        return employeeId;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Map<String, String> getEmployee(int id) {    
        Employee employee = employeeDao.getEmployee(id);
        Map<String, String> employeeDetails = new HashMap<String, String>();
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
        return employeeDetails;
       
    }
   
    /**
     * {@inheritdoc}
     */
    @Override
    public List<Employee> getSpecifiedEmployees(List<Integer> employeeIdList) {
        return employeeDao.getSpecifiedEmployees(employeeIdList);
    }

    /**
     * @param employee employee object
     */
    private String getEmployeeDetails(Employee employee) {
        if (null != employee ) {
            int temporaryAddressCount = 1;
            String employeeDetails = employee.toString();
            List<Address> employeeAddresses = employee.getAddresses();
            
                for (int index = 0; index < employeeAddresses.size(); index++) {
                    if ("permanent".equals(employeeAddresses.get(index).getAddressType())) {
                        employeeDetails = employeeDetails +
                                "\nPermanent address\n-----------------\n\n";
                    }
                    if ("temporary".equals(employeeAddresses.get(index).getAddressType())) {
                        employeeDetails = employeeDetails + "\nTemporary address " +
                                temporaryAddressCount + "\n--------------------\n\n";
                        temporaryAddressCount++;
                    } 
                    if (null != employeeAddresses.get(index).getDoorNumber()) { 
                        employeeDetails = employeeDetails + (employeeAddresses.get(index)).toString();
                    } 
               }
            
        return employeeDetails;
        } else {
            return null;
        }        
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getAllEmployeesDetails() {
        List<String> employeesDetails = new ArrayList<String>();
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeDao.getAllEmployee();
        for(int index = 0; index < employees.size(); index++) {
            employeesDetails.add(getEmployeeDetails(employees.get(index)));
        }
        return employeesDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean deleteEmployee(int id) {
        List<Address> addresses = new ArrayList<Address>();
        Employee employee = employeeDao.getEmployeeWithProject(id);
        employee.setIsDeleted(true);
        for (Address address : employee.getAddresses()) {
            address.setIsDeleted(true);
            addresses.add(address); 
        }
        employee.setProjects(new ArrayList<Project>());
        employee.setAddresses(addresses);   
    	return employeeDao.updateEmployee(employee);
    }   

    /**
     * {@inheritdoc}
     */
    @Override
    public String recoverEmployee(int id) {
        List<Address> addresses = new ArrayList<Address>();
        String recoveryStatus = null;
        Employee employee = employeeDao.getEmployee(id);
        if (null != employee) {
            if (!employee.getIsDeleted()) {
                recoveryStatus = "Employee already exist....!!!"; 
            } else {    
                employee.setIsDeleted(false);
                for (Address address : employee.getAddresses()) {
                    address.setIsDeleted(false);
                    addresses.add(address);
                }
                employee.setAddresses(addresses);
                if (employeeDao.updateEmployee(employee)) {
                    recoveryStatus = "\nRecovery successfull...!!!";
                } else {
                    recoveryStatus = "\nInvalid details....!!!";
                }
            }
        }
        return recoveryStatus;
    }

    /**
     * {@inheritdoc}
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
     * {@inheritdoc}
     */
    @Override
    public void updateEmployee(int id, String name, String designation,
            double salary, Date dob, long mobile, String option) {
        Employee employee = employeeDao.getEmployee(id);
        if ("name".equals(option)) {
            employee.setName(name);           
    	}
    	if ("designation".equals(option)) {
    	    employee.setDesignation(designation);   
    	}
    	if ("salary".equals(option)) {
    	    employee.setSalary(salary);
    	}
    	if ("dob".equals(option)) {
    	    employee.setDob(dob);   
    	}
    	if ("mobile".equals(option)) {
    	   employee.setMobile(mobile);
    	}
    	employeeDao.updateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void updateAddress(int employeeId, int addressId, String[] addressDetails) {
        Employee employee = employeeDao.getEmployee(employeeId);
        List<Address> addresses = new ArrayList<Address>();
        for (Address address : employee.getAddresses()) {
            if (address.getAddressId() == addressId) {
                address = new Address(addressDetails[0],
                addressDetails[1], addressDetails[2], addressDetails[3],
                addressDetails[4], addressDetails[5], false);
                address.setAddressId(addressId);
            }
            addresses.add(address);
        }
        employee.setAddresses(addresses);
        employeeDao.updateEmployee(employee);
    }
   
    /**
     * {@inheritdoc}
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
     * {@inheritdoc}
     */
    @Override
    public Map<Integer, String> getAllProjectsBasicDetails() {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProjectBasicDetails();
    }
 
    /**
     * {@inheritdoc}
     */
    @Override
    public Map<Integer, String> getAllEmployeeBasicDetails() {
        List<Employee> employees = employeeDao.getAllEmployee();
        Map<Integer, String> allEmployeeBasicDetails = new HashMap<Integer, String>();
        for (Employee employee : employees) {
            allEmployeeBasicDetails.put(employee.getId(), "\nEmployee Id     : "
                    + employee.getId() + "\nEmployee Name   : " + employee.getName() 
                    + "\nEmployee Mobile : " + employee.getMobile() + "\n");  
        } 
        return allEmployeeBasicDetails;            
    } 

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean assignProject(List<Integer> projectIdList, int employeeId) {
        Employee employee = employeeDao.getEmployeeWithProject(employeeId);
        ProjectService projectService = new ProjectServiceImpl();
        List<Project> projects = employee.getProjects();
        projects.addAll(projectService.getSpecifiedProjects(projectIdList));        
        employee.setProjects(projects);
        return employeeDao.updateEmployee(employee);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<String> getProjectsBasicDetails(int employeeId) {
        ProjectService projectService = new ProjectServiceImpl();
        Employee employee = employeeDao.getEmployeeWithProject(employeeId);
        List<String> projectsBasicDetails = new ArrayList<String>();
        List<Project> projects = new ArrayList<Project>();
        for (Project project : employee.getProjects()) {     
            projectsBasicDetails.add("\nProject id   : " 
                    + project.getId() + "\nProject Name : " + project.getName());
        }
        return projectsBasicDetails;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public boolean removeProject(int employeeId, int projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        Employee employee = employeeDao.getEmployeeWithProject(employeeId);
        List<Project> projects = new ArrayList<Project>();
        for (Project project : employee.getProjects()) {
            if(project.getId() != projectId) {
                projects.add(project);
            }
        }
        employee.setProjects(projects);
        return employeeDao.updateEmployee(employee); 
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Employee getEmployeeObject(int id) {
        return employeeDao.getEmployee(id);
    }
}
