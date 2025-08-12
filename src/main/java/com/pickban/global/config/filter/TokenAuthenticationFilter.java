package com.pickban.global.config.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pickban.global.web.dto.ApiHeader;
import com.pickban.domain.user.domain.model.User;
import com.pickban.domain.user.service.CustomUserDetailsService;
import com.pickban.global.config.token.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static final String TOKEN_REQUEST_HEADER_NAME = ApiHeader.AUTH_TOKEN.getName();
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public TokenAuthenticationFilter(
            JWTUtil jwtUtil,
            CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = obtainToken(request);

        if (token != null) {

            Long userId = jwtUtil.getUserId(token);
            User user = customUserDetailsService.findById(userId);

            Authentication authentication = buildAuthentication(user);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            //MDC.put("user_id", Long.toString(userId));
        }

        filterChain.doFilter(request, response);
    }

    private String obtainToken(HttpServletRequest request) {
        String rawToken = request.getHeader(TOKEN_REQUEST_HEADER_NAME);
        return jwtUtil.extractRawToken(rawToken);
    }

    private Authentication buildAuthentication(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null,
                                                       userDetails.getAuthorities());
    }
}
