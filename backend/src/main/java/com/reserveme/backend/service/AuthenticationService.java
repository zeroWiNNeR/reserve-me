package com.reserveme.backend.service;

import com.reserveme.backend.config.JwtService;
import com.reserveme.backend.model.dto.AuthenticationRequest;
import com.reserveme.backend.model.dto.AuthenticationResponse;
import com.reserveme.backend.model.entity.auth.Token;
import com.reserveme.backend.model.entity.auth.User;
import com.reserveme.backend.repository.auth.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

	private final UserService userService;
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final DefaultBearerTokenResolver bearerTokenResolver;

	@Autowired
	public AuthenticationService(UserService userService,
	                             TokenRepository tokenRepository,
	                             JwtService jwtService,
	                             AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.tokenRepository = tokenRepository;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.bearerTokenResolver = new DefaultBearerTokenResolver();
	}

	@Transactional
	public AuthenticationResponse register(User user) {
		var savedUser = userService.save(user);
		var jwtToken = jwtService.generateToken(savedUser.getEmail());
		var refreshToken = jwtService.generateRefreshToken(savedUser.getEmail());
		saveUserToken(savedUser, jwtToken);
		return new AuthenticationResponse(jwtToken, refreshToken);
	}

	@Transactional
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);
		var user = userService.findByEmail(request.getEmail());
		var jwtToken = jwtService.generateToken(user.getEmail());
		var refreshToken = jwtService.generateRefreshToken(user.getEmail());
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return new AuthenticationResponse(jwtToken, refreshToken);
	}

	private void saveUserToken(User user, String jwtToken) {
		var token = new Token();
		token.setUser(user);
		token.setToken(jwtToken);
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(User user) {
		tokenRepository.deleteAllByUserId(user.getId());
	}

	@Transactional
	public AuthenticationResponse refreshToken(HttpServletRequest request) {
		try {
			String refreshToken = bearerTokenResolver.resolve(request);
			if (refreshToken == null) {
				throw new RuntimeException("Bearer token doesn't exist in request headers");
			}

			final String userEmail = jwtService.extractEmail(refreshToken);
			if (userEmail != null) {
				var user = userService.findByEmail(userEmail);
				if (jwtService.isTokenValid(refreshToken, user.getEmail())) {
					var accessToken = jwtService.generateToken(user.getEmail());
					revokeAllUserTokens(user);
					saveUserToken(user, accessToken);
					return new AuthenticationResponse(accessToken, refreshToken);
				}
			}

			throw new RuntimeException("Wrong refresh token");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
