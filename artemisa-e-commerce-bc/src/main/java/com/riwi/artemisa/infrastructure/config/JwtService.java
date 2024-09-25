package com.riwi.artemisa.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final RestTemplate restTemplate;
    private final SecretKey key;

    public JwtService(@Value("${jwt.secret}") String secret, RestTemplate restTemplate) {
        logger.info("Received secret: {}", secret);
        logger.info("Secret length: {}", secret.length());
        this.key = getSigningKey(secret);
        this.restTemplate = restTemplate;
    }

    private SecretKey getSigningKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Claims decodeJwt(String token) {
        try {
            logger.info("Attempting to decode token: {}", token);
            Claims claims = Jwts.parserBuilder()
                       .setSigningKey(key)
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
            logger.info("Successfully decoded token. Claims: {}", claims);
            return claims;
        } catch (Exception e) {
            logger.error("Error decoding JWT: {}", e.getMessage(), e);
            return null;
        }
    }

    public String extractName(Claims claims) {
        return claims.get("name", String.class);
    }

    public String extractId(Claims claims) {
        return claims.get("id", String.class);
    }

    public String extractRoleUser(Claims claims) {
        return claims.get("roleUser", String.class);
    }

    public List<Map<String, Object>> extractPermissions(Claims claims) {
        return claims.get("permisions", List.class);
    }
}