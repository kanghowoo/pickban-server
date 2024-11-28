package com.pickban.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pickban.dto.MatchResponse;
import com.pickban.service.MatchService;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    public MatchResponse getMatchById(@PathVariable Long matchId) {
        return matchService.getMatchById(matchId);
    }

    @GetMapping
    public List<MatchResponse> getMatchesByDateRange(
            @RequestParam("startDate")
            LocalDateTime startDate,
            @RequestParam("endDate")
            LocalDateTime endDate
    ) {
        return matchService.getMatchesByDateRange(startDate, endDate);
    }
}
