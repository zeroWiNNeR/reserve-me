package com.reserveme.backend.model.exception;

public abstract class RmNotFoundException extends RmRuntimeException {

	public static final String CODE = "OBJECT_NOT_FOUND";

	private RmNotFoundException(String message) {
		super(message);
	}

	@Override
	public String getErrorCode() {
		return CODE;
	}

	public static class User extends RmNotFoundException {
		public User(Long id) {
			super("Not found user by id: " + id);
		}

		public User(String email) {
			super("Not found user by email: " + email);
		}
	}

}
