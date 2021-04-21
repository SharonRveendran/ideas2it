package com.ideas2it.exceptions;

/**
 * Custom Exception class for id check
 * @author Sharon v
 * @created 21/04/2021
 */
public class NoIdException extends Exception {

	public NoIdException() {}

	public NoIdException(String message) {
		super(message);
	}

	public NoIdException(Throwable cause) {
		super(cause);
	}

	public NoIdException(String message, Throwable cause) {
		super(message, cause);
	}
}
