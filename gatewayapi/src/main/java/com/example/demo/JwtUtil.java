package com.example.demo;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	public static final String secret= "5367566859703373367639792F423F452848284D6251655468576D5A71347437";
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }
    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }
    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }


}
