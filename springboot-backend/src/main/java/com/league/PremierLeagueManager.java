package com.league;


import com.league.pojo.PremierLeague;
import com.league.pojo.SportsClub;

import java.util.List;

public class PremierLeagueManager implements LeagueManager {

    PremierLeague premierLeague;


    public PremierLeagueManager() {
        premierLeague = new PremierLeague(1, "FootBall Premier League");
    }

    public PremierLeagueManager(int premierLeagueId,String name) {
        premierLeague = new PremierLeague(premierLeagueId, name);
    }


    //overriding default constructor
    public PremierLeagueManager(PremierLeague premierLeague) {
        this.premierLeague = premierLeague;
    }

    //add a new club
    @Override
    public void addClub(SportsClub footballClub) {
        List<SportsClub> dbSportsClubList = premierLeague.getClubList();
        dbSportsClubList.add(footballClub);
        premierLeague.setClubList(dbSportsClubList);
    }

    //delete a club
    @Override
    public void deleteClub(int clubId) {
        List<SportsClub> dbSportsClubList = premierLeague.getClubList();
        dbSportsClubList.removeIf(p -> p.getClubId() == clubId);
        premierLeague.setClubList(dbSportsClubList);
    }

    //get all clubs
    @Override
    public List<SportsClub> getAllSportsClubs() {
        return premierLeague.getClubList();
    }

    public PremierLeague getPremierLeague() {
        return premierLeague;
    }
}
