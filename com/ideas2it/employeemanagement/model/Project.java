package com.ideas2it.employeemanagement.model;
 
import java.sql.Date;

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

    public Project(int id, String name, String managerName,
            Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.managerName = managerName;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String toString() {
        return "Project id     = " + id + "\nProject name  = " 
                + name + "\nManager name  = " + managerName + "\nStart date    = "
                + startDate + "\nEnd date      = " + endDate;
    }
}

    