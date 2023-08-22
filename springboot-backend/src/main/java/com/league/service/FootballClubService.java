package com.league.service;

import com.league.FootballClub;

import java.util.List;


public interface FootballClubService {

    List<FootballClub> getAllFootballClub();

    FootballClub createFootballClub(FootballClub footballClub);

    FootballClub getFootballClubById(int footballClubId);

    FootballClub updateFootballClub(int footballClubId,FootballClub footballClub);

    void deleteFootballClub(int footballClubId);

}
