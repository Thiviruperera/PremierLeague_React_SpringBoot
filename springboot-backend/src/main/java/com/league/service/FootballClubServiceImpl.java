package com.league.service;


import com.league.FootballClub;
import com.league.SpringbootBackendApplication;
import com.league.exception.ResourceNotFoundException;
import com.league.pojo.SportsClub;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FootballClubServiceImpl implements FootballClubService {

    @Override
    public List<FootballClub> getAllFootballClub() {

        List<SportsClub> sportsClubList = SpringbootBackendApplication.main.leagueManager.getAllSportsClubs();

        Collections.sort(sportsClubList, Collections.reverseOrder());
        List<FootballClub> footballClubList = new ArrayList<>();
        for (SportsClub sportsClub : sportsClubList) {

            FootballClub footballClub = (FootballClub) sportsClub;

            footballClub.setNoOfMatches(sportsClub.getNoOfMatches());
            footballClub.setWins(sportsClub.getWins());
            footballClub.setLoses(sportsClub.getLoses());
            footballClub.setDraws(sportsClub.getDraws());
            footballClubList.add(footballClub);
        }

        return footballClubList;
    }

    @Override
    public FootballClub createFootballClub(FootballClub footballClub) {
        SportsClub sportsClub = new FootballClub(SpringbootBackendApplication.main.currentClubId, footballClub.getName(), footballClub.getLocation(),
                0, 0, 0, 0, 0, 0);
        SpringbootBackendApplication.main.leagueManager.addClub(sportsClub);
        SpringbootBackendApplication.main.currentClubId += 1;
        return (FootballClub) sportsClub;
    }

    @Override
    public FootballClub getFootballClubById(int footballClubId) {
        SportsClub club = SpringbootBackendApplication.main.leagueManager.getAllSportsClubs().stream()
                .filter(c -> c.getClubId() == footballClubId)
                .findFirst()
                .orElse(null);

        if (club == null) {
            new ResourceNotFoundException("Football Club not exist with id :" + footballClubId);
        }
        return (FootballClub) club;
    }

    @Override
    public FootballClub updateFootballClub(int footballClubId, FootballClub footballClub) {
        SportsClub club = SpringbootBackendApplication.main.leagueManager.getAllSportsClubs().stream()
                .filter(c -> c.getClubId() == footballClubId)
                .findFirst()
                .orElse(null);

        if (club == null) {
            new ResourceNotFoundException("Football Club not exist with id :" + footballClubId);
        }

        club.setName(footballClub.getName());
        club.setLocation(footballClub.getLocation());
        return (FootballClub) club;
    }

    @Override
    public void deleteFootballClub(int footballClubId) {
        SportsClub club = SpringbootBackendApplication.main.leagueManager.getAllSportsClubs().stream()
                .filter(c -> c.getClubId() == footballClubId)
                .findFirst()
                .orElse(null);


        if (club != null) {

            //deleting club
            SpringbootBackendApplication.main.leagueManager.deleteClub(footballClubId);
            System.out.println(club.getName() + " club is deleted");
        } else {
            new ResourceNotFoundException("Football Club not exist with id :" + footballClubId);
        }

    }
}
