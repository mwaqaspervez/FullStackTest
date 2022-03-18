package com.demo.assessment.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    public static String generateToken(Authentication auth) {
        Date exp = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes());
        Claims claims = Jwts.claims().setSubject(auth.getPrincipal().toString());
        return Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512).setExpiration(exp).compact();
    }
}