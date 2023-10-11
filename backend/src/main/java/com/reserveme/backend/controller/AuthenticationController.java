package com.reserveme.backend.controller;

import com.reserveme.backend.model.dto.AuthenticationRequest;
import com.reserveme.backend.model.dto.AuthenticationResponse;
import com.reserveme.backend.model.dto.UserTO;
import com.reserveme.backend.model.mapper.auth.UserMapper;
import com.reserveme.backend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	private final UserMapper userMapper;
	private final AuthenticationService authenticationService;

	@Autowired
	public AuthenticationController(UserMapper userMapper,
	                                AuthenticationService authenticationService) {
		this.userMapper = userMapper;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/register")
	public AuthenticationResponse register(@RequestBody UserTO userTO) {
		return authenticationService.register(userMapper.domainUser(userTO));
	}

	@PostMapping("/authenticate")
	public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
		return authenticationService.authenticate(request);
	}

	@PostMapping("/refresh-token")
	public AuthenticationResponse refreshToken(HttpServletRequest request) {
		return authenticationService.refreshToken(request);
	}

}
