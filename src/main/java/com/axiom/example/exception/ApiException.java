package com.axiom.example.exception;

public class ApiException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String errorCode;
	
	public ApiException(String code) {
		super(code);
		this.errorCode=code;
    }

	public String getErrorCode() {
		return errorCode;
	}

	
}
