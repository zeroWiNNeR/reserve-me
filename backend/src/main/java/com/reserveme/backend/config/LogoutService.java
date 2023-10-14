package com.reserveme.backend.config;

import com.reserveme.backend.repository.auth.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
	private static final Logger log = LoggerFactory.getLogger(LogoutService.class);

	private final TokenRepository tokenRepository;
	private final DefaultBearerTokenResolver bearerTokenResolver;

	@Autowired
	public LogoutService(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
		this.bearerTokenResolver = new DefaultBearerTokenResolver();
	}

	@Override
	public void logout(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication
	) {
		try {
			String bearerToken = bearerTokenResolver.resolve(request);
			if (bearerToken == null) {
				return;
			}

			tokenRepository.deleteByTokenIgnoreCase(bearerToken);
			SecurityContextHolder.clearContext();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
