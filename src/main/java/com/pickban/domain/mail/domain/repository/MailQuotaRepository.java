package com.pickban.domain.mail.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickban.domain.mail.domain.model.MailQuota;

@Repository
public interface MailQuotaRepository extends JpaRepository<MailQuota, Long> {
    long countByEmailAndCreatedAtAfter(String email, LocalDateTime thresholdTime);
}
