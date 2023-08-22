package com.league;

import com.league.pojo.SportsClub;


public class FootballClub extends SportsClub implements Comparable<FootballClub> {

    //declare attributes
    private int points;
    private int noOfGoals;


    //override default constructor
    public FootballClub(int clubId, String name, String location, int totalNoOfMatches, int totalWins, int totalLoses,int totalDraws) {
        super(clubId, name, location, totalNoOfMatches, totalWins, totalLoses,totalDraws);
        this.points = 0;
        this.noOfGoals = 0;
    }

    public FootballClub(int clubId, String name, String location, int totalNoOfMatches, int totalWins, int totalLoses,
                        int totalDraws, int points,  int noOfGoals) {
        super(clubId, name, location, totalNoOfMatches, totalWins, totalLoses,totalDraws);
        this.points = points;
        this.noOfGoals = noOfGoals;
    }

    public FootballClub() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public int getNoOfGoals() {
        return noOfGoals;
    }

    public void setNoOfGoals(int noOfGoals) {
        this.noOfGoals = noOfGoals;
    }


    public String toString() {
        return "FootballClub{" +
                "currentClubId=" + getClubId() +
                ", name='" + getName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", points=" + points +
                ", noOfGoals=" + noOfGoals +
                ", noOfMatches=" + getNoOfMatches() +
                ", wins=" + getWins() +
                ", loses=" + getLoses() +
                '}';
    }


    //sorting based on points & goals here
    @Override
    public int compareTo(FootballClub o) {

        if (this.points != o.getPoints()) {
            return (this.points - o.getPoints());
        } else {
            return (this.noOfGoals - o.getNoOfGoals());
        }
    }
}
