package com.reserveme.backend.model.exception;

public class RmException extends Exception implements HandledException {

	public static final String CODE = "GENERAL_ERROR";

	public RmException() {
		super();
	}

	public RmException(String message) {
		super(message);
	}

	public RmException(String message, Throwable cause) {
		super(message, cause);
	}

	public RmException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return CODE;
	}

}
