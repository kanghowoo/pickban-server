package com.pickban.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickban.common.error.ErrorCode;
import com.pickban.common.error.exception.MailSendException;
import com.pickban.common.error.exception.UserEmailSendRateLimitException;
import com.pickban.entity.MailQuota;
import com.pickban.repository.MailQuotaStorage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailQuotaService {
    private final MailQuotaStorage mailQuotaStorage;

    public MailQuotaService(@Qualifier("mailQuotaStorageJpaImpl") MailQuotaStorage mailQuotaStorage) {
        this.mailQuotaStorage = mailQuotaStorage;
    }

    public void recordQuotaUsage(String email) {
        log.info("recordQuotaUsage");
        LocalDateTime createdAt = LocalDateTime.now();

        if (!canSendEmail(email, createdAt)) {
            throw new UserEmailSendRateLimitException(ErrorCode.USER_EMAIL_SEND_RATE_LIMITED);
        }

        mailQuotaStorage.recordQuotaUsage(new MailQuota(email, createdAt));
    }

    public boolean canSendEmail(String email, LocalDateTime createdAt) {
        LocalDateTime time = createdAt.minusHours(MailQuota.THRESHOLD_HOURS);
        return mailQuotaStorage.countUsageBeforeThreshold(email, time) < MailQuota.MAIL_QUOTA;
    }
}
