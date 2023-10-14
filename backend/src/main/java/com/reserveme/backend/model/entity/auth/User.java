package com.reserveme.backend.model.entity.auth;

import com.reserveme.backend.model.entity.DomainObject;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rm_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends DomainObject {

	public enum Type {
		Client, Owner
	}

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private Long phone;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "type", updatable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Token> tokens;


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

}
