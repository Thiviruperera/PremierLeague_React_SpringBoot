package com.league;


import com.league.pojo.SportsClub;

import java.util.List;

public interface LeagueManager {

    void addClub(SportsClub sportsClub);
    void deleteClub(int clubId);
    List<SportsClub> getAllSportsClubs();

}
