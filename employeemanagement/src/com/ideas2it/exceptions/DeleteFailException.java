package com.ideas2it.exceptions;

/**
 * Custom Exception class for Delete operations
 * @author Sharon v
 * @created 21/04/2021
 */
public class DeleteFailException extends Exception {

	public DeleteFailException() {}

	public DeleteFailException(String message) {
		super(message);
	}

	public DeleteFailException(Throwable cause) {
		super(cause);
	}

	public DeleteFailException(String message, Throwable cause) {
		super(message, cause);
	}
}
