package com.pickban.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pickban.entity.User;

@Service
public class ThrottledGoogleMailService implements MailService {

    private final MailService mailService;
    private final MailQuotaService mailQuotaService;

    public ThrottledGoogleMailService(@Qualifier("googleMailService") MailService mailService,
                                      MailQuotaService mailQuotaService) {
        this.mailService = mailService;
        this.mailQuotaService = mailQuotaService;
    }

    @Override
    public synchronized void sendVerifyEmail(User user, String token) {
        mailService.sendVerifyEmail(user, token);
        mailQuotaService.recordQuotaUsage(user.getEmail());
    }

    @Override
    public void sendPasswordResetEmail(User user, String token) {
        mailService.sendPasswordResetEmail(user, token);
        mailQuotaService.recordQuotaUsage(user.getEmail());
    }
}
