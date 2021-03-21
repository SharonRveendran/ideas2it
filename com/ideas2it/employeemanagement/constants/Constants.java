package com.ideas2it.employeemanagement.constants;

/**
 * Class for define constants
 * @author Sharon V
 * @created 18-03-2021
 */
public class Constants {
    public static final String CRUD_OPTION = 
            "\nSelect your option\n\n1 : Create employee\n"
            + "2 : Read employee\n3 : Update employee\n"
 	    + "4 : Delete employee\n5 : Display all\n6 : Recover Employee\n7 : Exit\n";
    public static final String UPDATE_OPTION = 
            "\nWhat you want to update\n1 : Name\n"
            + "2 : Designation\n3 : Salary\n4 : DOB\n5 : Mobile\n6 : Address";  
    public static final String GET_NAME_MESSAGE = "\nEnter Name";
    public static final String GET_DESIGNATION_MESSAGE = "\nEnter Designation";
    public static final String GET_DATE_MESSAGE =
            "\nEnter date in given format yyyy-mm-dd";
    public static final String GET_ID_MESSAGE = "\nEnter employee id";
    public static final String GET_SALARY_MESSAGE = "\nEnter salary";
    public static final String GET_MOBILE_MESSAGE = "\nEnter mobile number";
    public static final String INVALID_DETAILS = "\nPlease enter valid details";
    public static final String NO_EMPLOYEE =
            "\nNo employee present with given Details...";
    public static final String SUCCESSFULL_CREATION =
            "\nEmployee created successfully...";
    public static final String SUCCESSFULL_UPDATION = 
            "\nEmployee updated successfully...";
    public static final String SUCCESSFULL_DELETION =
            "\nEmployee deleted successfully...";   
    public static final String END_MESSAGE = "\nThank you....";
    public static final String GET_ADDRESS_TYPE_MESSAGE =
            "\nSelect type of address\n1 "
            + ": Permanent\n2 : Temporary";
}
