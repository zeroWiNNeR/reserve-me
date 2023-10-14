package com.reserveme.backend.model.exception;

public class RmRuntimeException extends RuntimeException implements HandledException {

	public static final String CODE = "RUNTIME_ERROR";

	public RmRuntimeException() {
		super();
	}

	public RmRuntimeException(String message) {
		super(message);
	}

	public RmRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RmRuntimeException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return CODE;
	}

}
