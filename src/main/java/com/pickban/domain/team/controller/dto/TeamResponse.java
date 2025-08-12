package com.pickban.domain.team.controller.dto;

import com.pickban.domain.team.domain.model.Team;

import lombok.Getter;

@Getter
public class TeamResponse {
    private final long id;
    private final String slug;
    private final String fullName;
    private final long leagueId;

    public TeamResponse(long id, String slug, String fullName, long leagueId) {
        this.id = id;
        this.slug = slug;
        this.fullName = fullName;
        this.leagueId = leagueId;
    }

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.slug = team.getSlug();
        this.fullName = team.getFullName();
        this.leagueId = team.getLeague().getId();
    }
}
