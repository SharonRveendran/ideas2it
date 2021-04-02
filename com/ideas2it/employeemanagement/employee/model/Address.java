package com.ideas2it.employeemanagement.employee.model;

//import com.ideas2it.employeemanagement.employee.model.Employee;

/**
 * Calss for address Pojo
 * @author Sharon v 
 * @created 21/03/2021
 */

public class Address {  
    private int addressId;
    private String doorNumber;
    private String street;
    private String district;
    private String state;
    private String country;
    private String addressType;
    private boolean isDeleted;
    //private Employee employee;

    public Address(){
         
    }	      
    public Address(String doorNumber, String street, String district,
            String state, String country, String addressType,boolean isDeleted) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.district = district;
        this.state = state;
        this.country = country;
        this.addressType = addressType;
        this.isDeleted = isDeleted;
    }
    
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressId() {
        return addressId; 
    }
	
    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }
	
    public String getDoorNumber() {                    
        return doorNumber;
    }
  
    public void setStreet(String street) {
        this.street = street;
    }
	
    public String getStreet() {
        return street;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
	
    public String getDistrict() {
        return district;
    }
    
    public void setState(String state) {
        this.state = state;
    }
	
    public String getState() {
        return state;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
	
    public String getCountry() {
        return country;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
	
    public String getAddressType() {
        return addressType;
    }

    public boolean getIsDeleted() {
	return isDeleted;
    }
    
    public void setIsDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }

    public String toString() {
    	return  "\nDoor number   = " + doorNumber
                + "\nStreet        = " + street
                + "\nDistrict      = " + district
                + "\nState         = " + state
                + "\nCountry       = " + country + "\n";
    }
}