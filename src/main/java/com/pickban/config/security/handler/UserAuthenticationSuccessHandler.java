package com.pickban.config.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.pickban.common.request.ApiHeader;
import com.pickban.entity.RefreshToken;
import com.pickban.entity.User;
import com.pickban.service.CookieService;
import com.pickban.service.RefreshTokenService;
import com.pickban.util.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final String TOKEN_RESPONSE_HEADER_NAME = ApiHeader.AUTH_TOKEN.getName();
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final CookieService cookieService;
    @Autowired
    public UserAuthenticationSuccessHandler(JWTUtil jwtUtil,
                                            RefreshTokenService refreshTokenService,
                                            CookieService cookieService) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.cookieService = cookieService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.generateOrUpdateToken(user);

        String token = jwtUtil.createJwt(user.getId());
        String typeSpecifiedToken = jwtUtil.specifyType(token);

        response.setHeader(TOKEN_RESPONSE_HEADER_NAME, typeSpecifiedToken);
        response.addCookie(cookieService.generateRefreshTokenCookie(refreshToken));
    }

}
