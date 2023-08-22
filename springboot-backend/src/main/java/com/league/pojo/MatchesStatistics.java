package com.league.pojo;


//POJO class for storing match details per season
public class MatchesStatistics {

    //declare attributes
    private int matchId;
    private String teamAName;
    private String teamBName;
    private int teamAScore;
    private int teamBScore;
    private String winningTeamName;
    private String timestamp;

    public MatchesStatistics() {
    }

    //overriding the default constructor
    public MatchesStatistics(int matchId, String teamAName, String teamBName, int teamAScore, int teamBScore, String winningTeamName, String timestamp) {
        this.matchId = matchId;
        this.teamAName = teamAName;
        this.teamBName = teamBName;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
        this.winningTeamName = winningTeamName;
        this.timestamp = timestamp;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public String getWinningTeamName() {
        return winningTeamName;
    }

    public void setWinningTeamName(String winningTeamName) {
        this.winningTeamName = winningTeamName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    @Override
    public String toString() {
        return "MatchesStatistics{" +
                "matchId=" + matchId +
                ", teamAName=" + teamAName +
                ", teamBName=" + teamBName +
                ", teamAScore=" + teamAScore +
                ", teamBScore=" + teamBScore +
                ", winningTeamName=" + winningTeamName +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
