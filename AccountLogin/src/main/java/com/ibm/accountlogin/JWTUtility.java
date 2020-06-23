package com.ibm.accountlogin;

import java.util.Random;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JWTUtility {

	private String SECRET_KEY = "secret";

	public String generateToken(String usrName) {
		Random random = new Random();
		double d = random.nextDouble();
		Claims claim = Jwts.claims().setSubject(usrName + d);
		return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

}
