package com.league.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//POJO Class for Premier League Manager
public class PremierLeague {

    //declare attributes
    private int id;
    private String name;

    //registered football clubs list for a premier League
    private List<SportsClub> clubList;

    //overriding the default constructor
    public PremierLeague(int id, String name) {
        this.id = id;
        this.name = name;
        this.clubList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<SportsClub> getClubList() {
        return clubList;
    }

    public void setClubList(List<SportsClub> clubList) {
        this.clubList = clubList;
    }

    @Override
    public String toString() {
        return "PremierLeague{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clubList=" + clubList.stream()
                .map(SportsClub::toString)
                .collect(Collectors.joining(",")) +
                '}';
    }
}
