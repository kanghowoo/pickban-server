package com.pickban.domain.match.controller.dto;

import java.time.LocalDateTime;

import com.pickban.domain.team.controller.dto.TeamResponse;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatchResponse {
    private final long id;
    private final TeamResponse homeTeam;
    private final TeamResponse awayTeam;
    private final String name;
    private final LocalDateTime schedule;

    public MatchResponse(long id, TeamResponse homeTeam, TeamResponse awayTeam, String name, LocalDateTime schedule) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.name = name;
        this.schedule = schedule;
    }
}
