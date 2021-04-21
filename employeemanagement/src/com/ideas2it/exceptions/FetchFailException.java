package com.ideas2it.exceptions;

/**
 * Custom Exception class for Fetch operations
 * @author Sharon v
 * @created 21/04/2021
 */
public class FetchFailException extends Exception {

	public FetchFailException() {}

	public FetchFailException(String message) {
		super(message);
	}

	public FetchFailException(Throwable cause) {
		super(cause);
	}

	public FetchFailException(String message, Throwable cause) {
		super(message, cause);
	}
}
