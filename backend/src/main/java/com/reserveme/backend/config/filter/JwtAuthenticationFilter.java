package com.reserveme.backend.config.filter;

import com.reserveme.backend.config.JwtService;
import com.reserveme.backend.model.entity.auth.Token;
import com.reserveme.backend.repository.auth.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final TokenRepository tokenRepository;
	private final DefaultBearerTokenResolver bearerTokenResolver;

	@Autowired
	public JwtAuthenticationFilter(JwtService jwtService,
	                               UserDetailsService userDetailsService,
	                               TokenRepository tokenRepository) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
		this.tokenRepository = tokenRepository;
		this.bearerTokenResolver = new DefaultBearerTokenResolver();
	}

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		if (request.getServletPath().contains("/api/v1/auth")) {
			filterChain.doFilter(request, response);
			return;
		}

		String bearerToken;
		final String userEmail;
		try {
			bearerToken = bearerTokenResolver.resolve(request);
			if (bearerToken == null) {
				filterChain.doFilter(request, response);
				return;
			}

			userEmail = jwtService.extractEmail(bearerToken);
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

				if (jwtService.isTokenValid(bearerToken, userDetails.getUsername())) {
					Optional<Token> isTokenValid = tokenRepository.findByToken(bearerToken);
					if (isTokenValid.isPresent()) {
						UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities()
						);
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				}
			}

		} catch (OAuth2AuthenticationException e) {
			log.error(e.getMessage(), e);
		} finally {
			filterChain.doFilter(request, response);
		}
	}

}
