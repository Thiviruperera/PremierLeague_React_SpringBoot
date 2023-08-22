package com.league.service;

import com.league.FootballClub;
import com.league.pojo.MatchesStatistics;

import java.util.List;


public interface MatchService {

     String playMatch();

    List<MatchesStatistics> getMatchesStatistics();
}
