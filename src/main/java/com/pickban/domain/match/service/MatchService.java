package com.pickban.domain.match.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pickban.domain.match.controller.dto.MatchResponse;
import com.pickban.domain.team.controller.dto.TeamResponse;
import com.pickban.domain.match.domain.model.Match;
import com.pickban.domain.match.domain.repository.MatchRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchResponse getMatchById(long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));
        return toResponse(match);
    }

    public List<MatchResponse> getMatchesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return matchRepository.findByScheduleBetween(startDate, endDate).stream()
                .map(this::toResponse)
                .toList();
    }

    private MatchResponse toResponse(Match match) {
        return new MatchResponse(
                match.getId(),
                new TeamResponse(match.getHomeTeam()),
                new TeamResponse(match.getAwayTeam()),
                match.getName(),
                match.getSchedule()
        );
    }
}
