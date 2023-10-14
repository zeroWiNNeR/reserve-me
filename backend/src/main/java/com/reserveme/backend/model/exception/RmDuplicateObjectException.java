package com.reserveme.backend.model.exception;

public abstract class RmDuplicateObjectException extends RmRuntimeException {

	public static final String CODE = "DUPLICATE_OBJECT";

	RmDuplicateObjectException(String message) {
		super(message);
	}

	@Override
	public String getErrorCode() {
		return CODE;
	}

	public static class User extends RmDuplicateObjectException {
		public User(String email) {
			super("Duplicate user, email: " + email);
		}
	}

}
