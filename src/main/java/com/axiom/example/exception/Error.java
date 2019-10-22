package com.axiom.example.exception;

import java.io.Serializable;

public class Error implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String errorCode;
	String errorMessage;

	Error(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
