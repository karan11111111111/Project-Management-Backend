package com.projectManagement.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    // Generate secret key using HMAC SHA algorithm from the secret key constant
    private static final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRETE_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // 1 day validity
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {
        // Parse JWT claims from the token

        jwt= jwt.substring(7);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        // Extract email from JWT claims
        return String.valueOf(claims.get("email"));
    }
}
