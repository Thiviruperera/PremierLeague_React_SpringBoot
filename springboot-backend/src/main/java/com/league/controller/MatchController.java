package com.league.controller;

import com.league.PremierLeagueManager;
import com.league.SpringbootBackendApplication;
import com.league.persister.MatchesStatisticsPersister;
import com.league.persister.PremierLeaguePersister;
import com.league.persister.SportsClubPersister;
import com.league.pojo.MatchesStatistics;
import com.league.pojo.PremierLeague;
import com.league.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private ApplicationContext context;


    // get all Football Club
    @GetMapping("/match/playMatch")
    public String getAllFootballClub() {
        return matchService.playMatch();
    }

    // get matches Statistics rest api
    @GetMapping("/match/matchesStatistics")
    public ResponseEntity<List<MatchesStatistics>> getMatchesStatistics() {
        List<MatchesStatistics> matchesStatistics = matchService.getMatchesStatistics();
        return ResponseEntity.ok(matchesStatistics);
    }


    @GetMapping("/shutdown-app")
    public void shutdownApp() throws IOException {

        //deserializing before close the application
        PremierLeaguePersister premierLeaguePersister = new PremierLeaguePersister();
        PremierLeagueManager premierLeagueManager = (PremierLeagueManager) SpringbootBackendApplication.main.leagueManager;
        PremierLeague footballPremierLeague = premierLeagueManager.getPremierLeague();
        premierLeaguePersister.serialisePremierLeague(footballPremierLeague);

        SportsClubPersister sportsClubPersister = new SportsClubPersister();
        sportsClubPersister.serialiseSportsClub(SpringbootBackendApplication.main.leagueManager.getAllSportsClubs());

        MatchesStatisticsPersister matchesStatisticsPersister = new MatchesStatisticsPersister();
        matchesStatisticsPersister.serialiseMatchesStatistics(SpringbootBackendApplication.main.matchesStatisticsList);

        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
        System.exit(exitCode);
    }
}
