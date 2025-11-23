package com.example.studyspot.auth.jwt;

import com.example.studyspot.auth.exception.AuthErrorType;
import com.example.studyspot.auth.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String BEARER = "Bearer ";

    private SecretKey key;
    private Long accessTokenExpiredTime;
    
    public JwtTokenProvider (@Value("${jwt.secret-key}") String secretKey,
                                   @Value("${jwt.access-token-expiration-time}") Long accessTokenExpiredTime) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes((StandardCharsets.UTF_8)));
        this.accessTokenExpiredTime = accessTokenExpiredTime;
    }

    public String generateAccessToken(Long userId) {
        Date now = new Date();
        return Jwts.builder()
                .claim("id", userId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenExpiredTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractToken(String token) {
        if (token == null)
            throw new AuthException(AuthErrorType.NULL_TOKEN_EXCEPTION);

        if (!token.contains(BEARER))
            throw new AuthException(AuthErrorType.NOT_VALID_TOKEN);

        return token.substring(BEARER.length());
    }

    public Long getUserIFromToken(String accessToken) {
        Claims claims = parseClaims(accessToken);
        return claims.get("id", Long.class);
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new AuthException(AuthErrorType.EXPIRED_TOKEN);
        } catch (SignatureException e) {
            throw new AuthException(AuthErrorType.NOT_VALID_TOKEN);
        }
    }
}