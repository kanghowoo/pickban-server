package com.pickban.domain.mail.infrastructure.external;

import org.springframework.stereotype.Service;

import com.pickban.domain.mail.application.MailService;
import com.pickban.domain.user.domain.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MockMailService implements MailService {

    @Override
    public void sendVerifyEmail(User user, String token) {
        log.info("Send verify email: {}", user.getEmail());
    }

    @Override
    public void sendPasswordResetEmail(User user, String token) {
        log.info("Send password reset email: {}", user.getEmail());
    }

}
