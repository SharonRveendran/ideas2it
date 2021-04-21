package com.ideas2it.exceptions;

/**
 * Custom Exception class for create operation
 * @author Sharon v
 * @created 21/04/2021
 */
public class CreateFailException extends Exception {

	public CreateFailException() {}

	public CreateFailException(String message) {
		super(message);
	}

	public CreateFailException(Throwable cause) {
		super(cause);
	}

	public CreateFailException(String message, Throwable cause) {
		super(message, cause);
	}
}
