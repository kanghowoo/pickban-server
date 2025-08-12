package com.pickban.domain.team.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickban.domain.team.controller.dto.LeagueTeamResponse;
import com.pickban.domain.team.domain.model.League;
import com.pickban.domain.team.domain.repository.LeagueRepository;

@Service
public class LeagueTeamService {

    private final LeagueRepository leagueRepository;

    @Autowired
    public LeagueTeamService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public List<LeagueTeamResponse> getAllLeagueTeam() {
        List<League> leagues = leagueRepository.findAll();

        return leagues.stream()
                .map(LeagueTeamResponse::new)
                .collect(Collectors.toList());
    }
}
