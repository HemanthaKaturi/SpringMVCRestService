package com.spring.webservice.exception;

/**
 * Custom Exception Handling
 * 
 * @author Hemantha
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(Throwable root) {
		this("Cause: " + root.getMessage());
	}

	public ServiceException(String message, Throwable root) {
		this(message + "-- Cause: " + root.getMessage());
	}

	public ServiceException(String message) {
		super("Cause: " + message);
	}

}
