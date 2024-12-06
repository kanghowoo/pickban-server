package com.pickban.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickban.entity.Verification;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findByToken(String token);
    Optional<Verification> findByEmail(String email);
}
