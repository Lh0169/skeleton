package com.skeleton.common.core.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private final SecretKey key;
    private final long expiration;

    public JwtUtils(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder().subject(subject).claims(claims).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration)).signWith(key).compact();
    }

    public String generateToken(String subject) { return generateToken(subject, Map.of()); }

    public Claims parseToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public String getSubject(String token) { return parseToken(token).getSubject(); }

    public boolean isExpired(String token) {
        try { return parseToken(token).getExpiration().before(new Date()); }
        catch (JwtException e) { return true; }
    }

    public boolean validate(String token) {
        try { parseToken(token); return true; }
        catch (JwtException e) { return false; }
    }

    public long getExpiration() { return expiration; }
}
