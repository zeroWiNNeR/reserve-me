package com.reserveme.backend.model.entity.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum Role {

	NOT_AUTHENTICATED,
	CLIENT,
	OWNER,
	ADMIN,
	;

	public List<GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
	}

}
