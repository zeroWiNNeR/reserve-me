package com.reserveme.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSettings {

	@Value("${rm.database.url}")
	private String databaseUrl;

	@Value("${rm.database.username}")
	private String databaseUsername;

	@Value("${rm.database.password}")
	private String databasePassword;

	@Value("${rm.database.poolsize:20}")
	private int databasePoolSize;

	@Value("${rm.security.jwt.secret-key}")
	private String jwtSecretKey;

	@Value("${rm.security.jwt.expiration}")
	private long jwtExpiration;

	@Value("${rm.security.jwt.refresh-token.expiration}")
	private long jwtRefreshExpiration;


	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public int getDatabasePoolSize() {
		return databasePoolSize;
	}

	public String getJwtSecretKey() {
		return jwtSecretKey;
	}

	public long getJwtExpiration() {
		return jwtExpiration;
	}

	public long getJwtRefreshExpiration() {
		return jwtRefreshExpiration;
	}

}
