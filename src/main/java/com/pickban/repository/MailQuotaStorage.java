package com.pickban.repository;

import java.time.LocalDateTime;

import com.pickban.entity.MailQuota;

public interface MailQuotaStorage {
    void recordQuotaUsage(MailQuota mailQuota);

    long countUsageBeforeThreshold(String email, LocalDateTime time);
}
