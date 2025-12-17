package tech.csm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Test {
	public static void main(String[] args) {

		final String SECRET_KEY = "hvndflhkfjhgfvdfsjklvhjdfsvhjfvdfhvbd";

		String token = Jwts.builder().setId("001").setSubject("Akshaykirti Muduli").setIssuer("CSM")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()).compact();

		System.out.println(token);

		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token).getBody();

		System.out.println("Subject: " + claims.getSubject());
		System.out.println("id: " + claims.getId());
		System.out.println("Issuer: " + claims.getIssuer());
	}
}
