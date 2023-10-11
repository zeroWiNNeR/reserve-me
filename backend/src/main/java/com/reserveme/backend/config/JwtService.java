package com.reserveme.backend.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.reserveme.backend.model.exception.RmRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

	private final ApplicationSettings applicationSettings;

	@Autowired
	public JwtService(ApplicationSettings applicationSettings) {
		this.applicationSettings = applicationSettings;
	}

	public String extractEmail(String token) {
		return extractClaim(token, JWTClaimsSet::getSubject);
	}

	public <T> T extractClaim(String token, Function<JWTClaimsSet, T> claimsResolver) {
		final JWTClaimsSet claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private JWTClaimsSet extractAllClaims(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(applicationSettings.getJwtSecretKey());
			signedJWT.verify(verifier);
			return signedJWT.getJWTClaimsSet();
		} catch (Exception e) {
			throw new RmRuntimeException("Wrong JWT");
		}
	}

	public String generateToken(String email) {
		return buildToken(email, applicationSettings.getJwtExpiration());
	}

	public String generateRefreshToken(String email) {
		return buildToken(email, applicationSettings.getJwtRefreshExpiration());
	}

	private String buildToken(String email, long expiration) {
		try {
			JWSSigner signer = new MACSigner(applicationSettings.getJwtSecretKey());
			long current = System.currentTimeMillis();
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
					.subject(email)
					.issueTime(new Date(current))
					.expirationTime(new Date(current + expiration))
					.build();

			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
			signedJWT.sign(signer);
			return signedJWT.serialize();
		} catch (Exception e) {
			throw new RmRuntimeException("Wrong JWT");
		}
	}

	public boolean isTokenValid(String token, String email) {
		final String extractedEmail = extractEmail(token);
		return extractedEmail.equals(email) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, JWTClaimsSet::getExpirationTime);
	}

}
