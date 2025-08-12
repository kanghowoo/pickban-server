package com.pickban.global.web.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.pickban.domain.auth.domain.model.RefreshToken;

import jakarta.servlet.http.Cookie;

@Service
public class CookieService {
    private static final String REFRESH_TOKEN_COOKIE_KEY_NAME = "refreshToken";
    private final Environment env;

    public CookieService(Environment env) {
        this.env = env;
    }

    public Cookie generateRefreshTokenCookie(RefreshToken refreshToken) {
        Cookie cookie = createCookie(
                REFRESH_TOKEN_COOKIE_KEY_NAME, refreshToken.getToken());

        cookie.setMaxAge(calculateMaxAge(refreshToken.getExpiredAt()));
        return cookie;
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setSecure(getSecureProperty());
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }

    private boolean getSecureProperty() {
        String secureProperty = this.env.getProperty("server.servlet.session.cookie.secure");

        return Boolean.parseBoolean(secureProperty);
    }

    private int calculateMaxAge(LocalDateTime expiredAt) {
        return (int) Duration.between(LocalDateTime.now(), expiredAt).getSeconds();
    }


}
