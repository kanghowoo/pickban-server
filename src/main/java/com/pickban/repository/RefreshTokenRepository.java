package com.pickban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickban.entity.RefreshToken;
import com.pickban.entity.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByUser(User user);
}
