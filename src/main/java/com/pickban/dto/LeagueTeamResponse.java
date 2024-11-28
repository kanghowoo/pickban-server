package com.pickban.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.pickban.entity.League;
import com.pickban.entity.Team;

import lombok.Getter;

@Getter
public class LeagueTeamResponse {
    private final Long id;
    private final String slug;
    private final String fullName;
    private final List<TeamResponse> teams;

    public LeagueTeamResponse(League league) {
        this.id = league.getId();
        this.slug = league.getSlug();
        this.fullName = league.getFullName();
        this.teams = league.getTeams().stream()
                .map(TeamResponse::new)
                .collect(Collectors.toList());
    }
}
