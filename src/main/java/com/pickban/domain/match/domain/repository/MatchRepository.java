package com.pickban.domain.match.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickban.domain.match.domain.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByScheduleBetween(LocalDateTime startDate, LocalDateTime endDate);
}
