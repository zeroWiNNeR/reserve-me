package com.reserveme.backend.model.entity.auth;

import jakarta.persistence.*;

@Entity
@Table(name = "rm_user_token")
public class Token {

	@Id
	@Column(name = "token")
	private String token;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
