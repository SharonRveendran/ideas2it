package com.ideas2it.employeemanagement.project.model;
 
import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;

/**
 * Calss for project Pojo
 * @author Sharon v 
 * @created 21/03/2021
 */
public class Project {
    private int id;
    private String name;
    private String managerName;
    private Date startDate;
    private Date endDate;
    private boolean isDeleted;
    private List<Employee> employeeList;

    public Project() {}
    public Project(int id, String name, String managerName,
            Date startDate, Date endDate, boolean isDeleted, List<Employee> employeeList) {
        this.id = id;
        this.name = name;
        this.managerName = managerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeList = employeeList;
        this.isDeleted = isDeleted;
    }

    public Project(String name, String managerName,
            Date startDate, Date endDate, boolean isDeleted, List<Employee> employeeList) {
        this.name = name;
        this.managerName = managerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeList = employeeList;
        this.isDeleted = isDeleted;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
   
    public void setName(String name) {
        this.name = name;
    }
 
    public String getName() {
        return name;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
 
    public String getManagerName() {
        return managerName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
 
    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
 
    public Date getEndDate() {
        return endDate;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
 
    public boolean getIsDeleted() {
        return isDeleted;
    }
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
 
    public List<Employee> getEmployeeList() {
        return employeeList;
    }    

    public String toString() {
        return "\n\nProject id     = " + id + "\nProject name   = " 
                + name + "\nManager name   = " + managerName + "\nStart date     = "
                + startDate + "\nEnd date       = " + endDate;
    }
}

    