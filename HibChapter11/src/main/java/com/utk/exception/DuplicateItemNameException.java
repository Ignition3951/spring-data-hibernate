package com.utk.exception;

public class DuplicateItemNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateItemNameException(String message) {
		super(message);
	}
}
