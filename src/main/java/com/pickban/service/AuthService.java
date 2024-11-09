package com.pickban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickban.entity.User;
import com.pickban.util.JWTUtil;

@Service
public class AuthService {
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthService(JWTUtil jwtUtil,
                       CustomUserDetailsService customUserDetailsService,
                       RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.refreshTokenService = refreshTokenService;
    }

    public String reissueToken(String token, String refreshToken) {
        String rawToken = jwtUtil.extractRawToken(token);
        long userId = jwtUtil.getUserId(rawToken);
        User user = customUserDetailsService.findById(userId);

        refreshTokenService.verify(refreshToken, user);
        String reissueToken = jwtUtil.createJwt(userId);

        return jwtUtil.specifyType(reissueToken);
    }
}
