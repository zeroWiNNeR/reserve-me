package com.reserveme.backend.model.entity.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
	private final Long userId;
	private final String email;
	private final String password;
	private final Collection<GrantedAuthority> grantedAuthorities;


	public UserDetails(Long userId, String email, String password, Collection<GrantedAuthority> grantedAuthorities) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.grantedAuthorities = grantedAuthorities;
	}


	public Long getUserId() {
		return userId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
