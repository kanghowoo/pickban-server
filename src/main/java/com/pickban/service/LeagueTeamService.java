package com.pickban.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickban.dto.LeagueTeamResponse;
import com.pickban.entity.League;
import com.pickban.repository.LeagueRepository;

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
