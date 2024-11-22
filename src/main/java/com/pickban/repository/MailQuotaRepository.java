package com.pickban.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pickban.entity.MailQuota;

@Repository
public interface MailQuotaRepository extends JpaRepository<MailQuota, Long> {
    long countByEmailAndCreatedAtAfter(String email, LocalDateTime thresholdTime);
}
