package com.reserveme.backend.model.mapper.auth;

import com.reserveme.backend.model.dto.UserTO;
import com.reserveme.backend.model.entity.auth.Client;
import com.reserveme.backend.model.entity.auth.Owner;
import com.reserveme.backend.model.entity.auth.Role;
import com.reserveme.backend.model.entity.auth.User;
import com.reserveme.backend.model.exception.RmNotFoundException;
import com.reserveme.backend.service.UserService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public abstract class UserMapper {

	@Autowired
	protected PasswordEncoder passwordEncoder;
	@Autowired
	protected UserService userService;

	public User domainUser(UserTO transport) throws RmNotFoundException.User {
		User domain;
		if (transport.getId() == null) {
			domain = switch (transport.getType()) {
				case Client -> new Client();
				case Owner -> new Owner();
			};
			domain.setPassword(passwordEncoder.encode(transport.getPassword()));
			domain.setType(transport.getType());
			domain.setRole(switch (transport.getType()) {
				case Client -> Role.CLIENT;
				case Owner -> Role.OWNER;
			});
		} else {
			domain = userService.findById(transport.getId());
		}

		domain.setFirstname(transport.getFirstname());
		domain.setLastname(transport.getLastname());
		domain.setEmail(transport.getEmail());
		domain.setPhone(transport.getPhone());

		return domain;
	}

}
