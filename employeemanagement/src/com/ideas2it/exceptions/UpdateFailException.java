package com.ideas2it.exceptions;

/**
 * Custom Exception class for Update operations
 * @author Sharon v
 * @created 21/04/2021
 */
public class UpdateFailException extends Exception {

	public UpdateFailException() {}

	public UpdateFailException(String message) {
		super(message);
	}

	public UpdateFailException(Throwable cause) {
		super(cause);
	}

	public UpdateFailException(String message, Throwable cause) {
		super(message, cause);
	}
}
