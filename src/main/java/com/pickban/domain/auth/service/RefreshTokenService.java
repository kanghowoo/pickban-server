package com.pickban.domain.auth.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickban.domain.auth.exception.ExpiredRefreshTokenException;
import com.pickban.domain.auth.exception.RefreshTokenSignatureException;
import com.pickban.domain.auth.domain.model.RefreshToken;
import com.pickban.domain.user.domain.model.User;
import com.pickban.domain.auth.domain.repository.RefreshTokenRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RefreshTokenService {
    public static final int REFRESH_TOKEN_LIFESPAN = 365;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void verify(String token, User user) {
        RefreshToken refreshToken = findOrCreate(user);

        if (!refreshToken.getToken().equals(token)) {
            throw new RefreshTokenSignatureException("refresh token is not correct");
        }

        if (refreshToken.isExpired()) {
            throw new ExpiredRefreshTokenException("refresh token is expired");
        }
    }

    @Transactional
    public RefreshToken generateOrUpdateToken(User user) {
        RefreshToken refreshToken = findOrCreate(user);

        if (refreshToken.isExpired()) {
            refreshToken = reissue(refreshToken);
        }

        return refreshToken;
    }

    public RefreshToken reissue(RefreshToken refreshToken) {
        refreshToken.updateToken(generateToken(), expiredAtFromNow());
        log.info("refresh token updated");
        return refreshToken;
    }

    private RefreshToken findOrCreate(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user);

        if (refreshToken == null) {
            return create(user);
        }

        return refreshToken;
    }

    private RefreshToken create(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(generateToken())
                .expiredAt(expiredAtFromNow())
                .build();

        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private LocalDateTime expiredAtFromNow() {
        return LocalDateTime.now().plusDays(REFRESH_TOKEN_LIFESPAN);
    }
}
