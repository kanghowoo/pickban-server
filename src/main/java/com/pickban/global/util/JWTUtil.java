package com.pickban.global.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pickban.global.config.token.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {
    private static final String USER_ID_CLAIM_KEY = "user_id";
    private static final String AUTHENTICATION_TYPE = "Bearer";
    private static final String typePrefix = AUTHENTICATION_TYPE + " ";
    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;

    @Autowired
    public JWTUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        secretKey = new SecretKeySpec(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8),
                                   Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String token) {

        return Jwts.parser().verifyWith(secretKey).build()
                   .parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build()
                   .parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build()
                   .parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String specifyType(String token) {
        return typePrefix + token;
    }

    public String extractRawToken(String typeSpecifiedToken) {
        if (typeSpecifiedToken == null) {
            return null;
        } else if (typeSpecifiedToken.startsWith(typePrefix)) {
            return typeSpecifiedToken.split(" ")[1];
        } else {
            return typeSpecifiedToken;
        }
    }

    public Long getUserId(String token) {
        return getUserIdFromClaims(verify(token));
    }

    private Long getUserIdFromClaims(Claims claims) {
        return claims.get(USER_ID_CLAIM_KEY, Long.class);
    }

    public String createJwt(Long userId) {

        return Jwts.builder()
                   .issuer(jwtProperties.getIssuer())
                   .claim(USER_ID_CLAIM_KEY, userId)
                   .issuedAt(new Date())
                   .expiration(new Date(expirationClaimFromNow()))
                   .signWith(secretKey)
                   .compact();
    }

    private Claims verify(String token) {
        JwtParser jwtParser = Jwts.parser()
                                  .requireIssuer(jwtProperties.getIssuer())
                                  .verifyWith(secretKey)
                                  .build();

        return jwtParser.parseSignedClaims(token).getPayload();
    }

    private long expirationClaimFromNow() {
        Date now = new Date();
        return now.getTime() + ((long) jwtProperties.getExpiration()) * 1000;
    }
}
