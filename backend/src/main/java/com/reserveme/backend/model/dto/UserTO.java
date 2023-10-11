package com.reserveme.backend.model.dto;

import com.reserveme.backend.model.entity.auth.User;

public class UserTO {

	private final Long id;
	private final String firstname;
	private final String lastname;
	private final String password;
	private final String email;
	private final Long phone;
	private final User.Type type;


	public UserTO(Long id,
	              String firstname,
	              String lastname,
	              String email,
	              String password,
	              Long phone,
	              User.Type type) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.type = type;
	}


	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Long getPhone() {
		return phone;
	}

	public User.Type getType() {
		return type;
	}

}
