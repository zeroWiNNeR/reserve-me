package com.reserveme.backend.model.dto;

public class AuthenticationResponse {

	private final String accessToken;

	private final String refreshToken;


	public AuthenticationResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

}
