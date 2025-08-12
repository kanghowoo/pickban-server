package com.pickban.domain.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickban.domain.team.controller.dto.LeagueTeamResponse;
import com.pickban.domain.team.service.LeagueTeamService;

@RestController
@RequestMapping("/api")
public class LeagueTeamController {
    private final LeagueTeamService leagueTeamService;

    @Autowired
    public LeagueTeamController(LeagueTeamService teamService) {
        this.leagueTeamService = teamService;
    }

    @GetMapping("/leagues")
    public ResponseEntity<?> list() {
        List<LeagueTeamResponse> response = this.leagueTeamService.getAllLeagueTeam();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
