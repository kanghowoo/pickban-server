package com.pickban.domain.mail.domain.repository;

import java.time.LocalDateTime;

import com.pickban.domain.mail.domain.model.MailQuota;

public interface MailQuotaStorage {
    void recordQuotaUsage(MailQuota mailQuota);

    long countUsageBeforeThreshold(String email, LocalDateTime time);
}
