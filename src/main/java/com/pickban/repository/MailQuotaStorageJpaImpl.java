package com.pickban.repository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.pickban.entity.MailQuota;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MailQuotaStorageJpaImpl implements MailQuotaStorage {
    private final MailQuotaRepository mailQuotaRepository;

    public MailQuotaStorageJpaImpl(MailQuotaRepository mailQuotaRepository) {
        this.mailQuotaRepository = mailQuotaRepository;
    }

    @Override
    public void recordQuotaUsage(MailQuota mailQuota) {
        log.info("in here");
        mailQuotaRepository.save(mailQuota);
    }

    @Override
    public long countUsageBeforeThreshold(String email, LocalDateTime createdAt) {
        return mailQuotaRepository.countByEmailAndCreatedAtAfter(email, createdAt);
    }

}
