package com.league.pojo;


//POJO Class for Sport clubs
public class SportsClub {

    //declare attributes
    private int clubId;
    private String name;
    private String location;

    //statistics variables
    private int noOfMatches;
    private int wins;
    private int loses;
    private int draws;

    //overriding default constructor
    public SportsClub(int clubId, String name, String location, int noOfMatches, int wins, int loses, int draws) {
        this.clubId = clubId;
        this.name = name;
        this.location = location;
        this.noOfMatches = noOfMatches;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public SportsClub(int clubId, String name, String location) {
        this.clubId = clubId;
        this.name = name;
        this.location = location;
        this.noOfMatches = 0;
        this.wins = 0;
        this.loses = 0;
        this.draws = 0;
    }

    public SportsClub() {
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    @Override
    public String toString() {
        return "SportsClub{" +
                "clubId=" + clubId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", noOfMatches=" + noOfMatches +
                ", wins=" + wins +
                ", loses=" + loses +
                ", draws=" + draws +
                '}';
    }
}
