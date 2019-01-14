package com.prontoitlabs.assignment;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {

	private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static String getJWTToken(String username) {
		
		Date now = new Date();
		
		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder()
				.setId(username)
				.setSubject("JWTToken")
				.setIssuedAt(now)
				.setIssuer("Pronto")
				.signWith(key);
		
		//Builds the JWT and serializes it to a compact, URL-safe string return
		return builder.compact();
	}

	public static String verifyJWTToken(String token) {

		Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		String id = claims.getBody().getId();
		return id;
	}
}
