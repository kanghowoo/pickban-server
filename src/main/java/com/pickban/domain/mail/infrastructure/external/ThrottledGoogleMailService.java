package com.pickban.domain.mail.infrastructure.external;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pickban.domain.mail.application.MailQuotaService;
import com.pickban.domain.mail.application.MailService;
import com.pickban.domain.user.domain.model.User;

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
