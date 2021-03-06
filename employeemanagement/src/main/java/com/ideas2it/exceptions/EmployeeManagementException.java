package com.ideas2it.exceptions;

/**
 * Custom Exception class
 * @author Sharon v
 * @created 22/04/2021
 */
public class EmployeeManagementException extends Exception {

	public EmployeeManagementException(String message) {
		super(message);
	}

	public EmployeeManagementException(Throwable cause) {
		super(cause);
	}

	public EmployeeManagementException(String message, Throwable cause) {
		super(message, cause);
	}
}
