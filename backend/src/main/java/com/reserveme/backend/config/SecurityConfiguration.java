package com.reserveme.backend.config;

import com.reserveme.backend.config.filter.JwtAuthenticationFilter;
import com.reserveme.backend.model.entity.auth.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Autowired
	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter,
	                             AuthenticationProvider authenticationProvider,
	                             LogoutHandler logoutHandler) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
		this.logoutHandler = logoutHandler;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrfConfigurer -> {
					var requestHandler = new CsrfTokenRequestAttributeHandler();
					// Это делается для отключения отложенной генерации csrf-токена
					// (см. https://docs.spring.io/spring-security/reference/5.8/migration/servlet/exploits.html#_i_need_to_opt_out_of_deferred_tokens_for_another_reason)
					requestHandler.setCsrfRequestAttributeName(null);
					csrfConfigurer
							.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
							.csrfTokenRequestHandler(requestHandler)
							.ignoringRequestMatchers("/api/v1/auth/*");
				})
				.authorizeHttpRequests(customizer -> customizer
						.requestMatchers(
								"/api/v1/auth/**",
								"/v2/api-docs",
								"/v3/api-docs",
								"/v3/api-docs/**",
								"/swagger-resources",
								"/swagger-resources/**",
								"/configuration/ui",
								"/configuration/security",
								"/swagger-ui/**",
								"/webjars/**",
								"/swagger-ui.html"
						).permitAll()

						.requestMatchers("/api/v1/user/**").hasAnyRole(Role.CLIENT.name(), Role.OWNER.name(), Role.ADMIN.name())
						.requestMatchers("/api/v1/estblsh/**").hasAnyRole(Role.OWNER.name(), Role.ADMIN.name())
						.anyRequest().authenticated())
				.sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logoutConfigurer -> {
					logoutConfigurer.logoutUrl("/api/v1/auth/logout");
					logoutConfigurer.addLogoutHandler(logoutHandler);
					logoutConfigurer.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
				})
		;

		return http.build();
	}
}
