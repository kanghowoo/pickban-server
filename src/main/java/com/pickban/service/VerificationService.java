package com.pickban.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickban.common.error.ErrorCode;
import com.pickban.common.error.exception.EntityBadStatusException;
import com.pickban.common.error.exception.EntityNotFoundException;
import com.pickban.entity.User;
import com.pickban.entity.Verification;
import com.pickban.repository.VerificationRepository;

@Service
public class VerificationService {
    public static final int VERIFY_TOKEN_EXPIRATION = 1;
    private final VerificationRepository verificationRepository;
    private final UserFindService userFindService;
    private final MailService mailService;

    public VerificationService(VerificationRepository verificationRepository,
                               UserFindService userFindService,
                               @Qualifier("throttledGoogleMailService") MailService mailService) {
        this.verificationRepository = verificationRepository;
        this.userFindService = userFindService;
        this.mailService = mailService;
    }

    public void recordToken(String email, String token) {
        Verification verification = verificationRepository
                .findByEmail(email)
                .orElse(Verification.builder()
                                .email(email)
                                .token(token)
                                .build());

        verification.updateToken(token);
        verificationRepository.save(verification);
    }

    @Transactional
    public String verify(String token) {
        Verification verification = findByToken(token);
        checkTokenExpiration(verification);

        final String email = verification.getEmail();

        User user = userFindService.findByEmail(email);
        checkUserVerifyStatus(user);

        user.markVerified();

        return email;
    }

    public void sendVerifyEmail(String email) {
        User user = userFindService.findByEmail(email);
        checkUserVerifyStatus(user);

        String token = generateVerifyToken();
        mailService.sendVerifyEmail(user, token);

        recordToken(email, token);
    }

    public void sendPasswordResetEmail(String email) {
        User user = userFindService.findByEmail(email);
        String token = generateVerifyToken();

        mailService.sendPasswordResetEmail(user, token);

        recordToken(email, token);
    }

    private Verification findByToken(String token) {
        return verificationRepository
                .findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException(
                        "token is not found", ErrorCode.USER_VERIFY_FAILED));
    }

    private void checkTokenExpiration(Verification verification) {
        final LocalDateTime updatedAt = verification.getUpdatedAt();

        if (updatedAt.isBefore(LocalDateTime.now().minusHours(VERIFY_TOKEN_EXPIRATION))) {
            throw new EntityBadStatusException("token is expired", ErrorCode.USER_VERIFY_FAILED);
        }
    }

    private void checkUserVerifyStatus(User user) {
        if (user.isVerified()) {
            throw new EntityBadStatusException(
                    "user already verified", ErrorCode.USER_VERIFY_FAILED);
        }
    }

    private String generateVerifyToken() {
        return UUID.randomUUID().toString();
    }
}
