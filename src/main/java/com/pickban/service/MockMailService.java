package com.pickban.service;

import org.springframework.stereotype.Service;

import com.pickban.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MockMailService implements MailService{

    @Override
    public void sendVerifyEmail(User user, String token) {
        log.info("Send verify email: {}", user.getEmail());
    }

    @Override
    public void sendPasswordResetEmail(User user, String token) {
        log.info("Send password reset email: {}", user.getEmail());
    }

}
