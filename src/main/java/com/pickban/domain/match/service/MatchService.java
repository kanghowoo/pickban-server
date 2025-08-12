package com.pickban.domain.match.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickban.domain.match.controller.dto.MatchResponse;
import com.pickban.domain.team.controller.dto.TeamResponse;
import com.pickban.domain.match.domain.model.Match;
import com.pickban.domain.match.domain.repository.MatchRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchResponse getMatchById(long matchId) {
        Match match = matchRepository.findById(matchId)
                                     .orElseThrow(() -> new EntityNotFoundException("Match not found"));

        return new MatchResponse(match.getId(),
                                 new TeamResponse(match.getHomeTeam()),
                                 new TeamResponse(match.getAwayTeam()),
                                 match.getName(),
                                 match.getSchedule());
    }

    public List<MatchResponse> getMatchesByDateRange(LocalDateTime startDate,
                                             LocalDateTime endDate) {
        List<Match> matches = matchRepository.findByScheduleBetween(startDate, endDate);
        return matches.stream()
                      .map(match -> new MatchResponse(match.getId(),
                                                      new TeamResponse(match.getHomeTeam()),
                                                      new TeamResponse(match.getAwayTeam()),
                                                      match.getName(),
                                                      match.getSchedule()))
                      .collect(Collectors.toList());
    }
}
