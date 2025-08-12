package com.pickban.domain.team.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickban.domain.team.domain.model.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    List<League> findAll();
}
