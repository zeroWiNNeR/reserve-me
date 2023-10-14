package com.reserveme.backend.service;

import com.reserveme.backend.model.entity.auth.User;
import com.reserveme.backend.model.entity.auth.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserService userService;

	@Autowired
	public UserDetailsService(UserService userService) {
		this.userService = userService;
	}


	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		return new UserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getRole().getAuthorities());
	}

}
