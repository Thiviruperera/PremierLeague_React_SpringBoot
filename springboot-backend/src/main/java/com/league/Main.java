package com.league;

import com.league.persister.MatchesStatisticsPersister;
import com.league.persister.PremierLeaguePersister;
import com.league.persister.SportsClubPersister;
import com.league.pojo.MatchesStatistics;
import com.league.pojo.PremierLeague;
import com.league.pojo.SportsClub;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

    public static int currentClubId = 1;
    public static int matchId = 1;
    public static LeagueManager leagueManager;
    public static List<MatchesStatistics> matchesStatisticsList = new ArrayList<>();

    public static String matchDetails;
    public static int perDayMatchesCount = 0;
    public static int daysIncreased = 0;

    public Main() {
        PremierLeaguePersister premierLeaguePersister = new PremierLeaguePersister();
        PremierLeague premierLeague = premierLeaguePersister.deserialisePremierLeague();

        SportsClubPersister sportsClubPersister = new SportsClubPersister();
        List<SportsClub> sportsClubList = sportsClubPersister.deserialiseSportsClub();

        MatchesStatisticsPersister matchesStatisticsPersister = new MatchesStatisticsPersister();
        List<MatchesStatistics> serialisedMatchesStatisticsList = matchesStatisticsPersister.deserialiseMatchesStatistics();

        if (premierLeague != null) {
            leagueManager = new PremierLeagueManager(premierLeague.getId(), premierLeague.getName());

            if (!sportsClubList.isEmpty()) {

                for (SportsClub sportsClub : sportsClubList) {
                    leagueManager.addClub(sportsClub);
                    if (SpringbootBackendApplication.main.currentClubId < sportsClub.getClubId()) {
                        SpringbootBackendApplication.main.currentClubId = sportsClub.getClubId();
                    }
                }
                SpringbootBackendApplication.main.currentClubId += 1;
            }
            if (!serialisedMatchesStatisticsList.isEmpty()) {
                for (MatchesStatistics matchesStatistics : serialisedMatchesStatisticsList) {
                    SpringbootBackendApplication.main.matchesStatisticsList.add(matchesStatistics);
                    if (SpringbootBackendApplication.main.matchId < matchesStatistics.getMatchId()) {
                        SpringbootBackendApplication.main.matchId = matchesStatistics.getMatchId();
                    }
                }
                SpringbootBackendApplication.main.matchId += 1;
            }

        } else {
            leagueManager = new PremierLeagueManager();
        }
    }


    //run functions based on user input
    public void applicationStartup() throws IOException {
        String option = "";
        do {
            this.showMenu();
            Scanner consoleScanner = new Scanner(System.in);
            System.out.print("Enter your option: ");
            option = consoleScanner.nextLine();
            option = option.toUpperCase();

            //Add new club  into premier league
            if (option.equals("A")) {

                String clubName = "", clubLocation = "";
                System.out.print("Enter Club Name: ");
                clubName = consoleScanner.nextLine();
                System.out.print("Enter Club Location: ");
                clubLocation = consoleScanner.nextLine();

                if (clubName.isEmpty() || clubLocation.isEmpty()) {
                    System.out.println("Please enter data for Club Name and Club Location!!");
                    continue;
                }

                SportsClub sportsClub = new FootballClub(currentClubId, clubName, clubLocation, 0, 0, 0,
                        0, 0, 0
                );
                leagueManager.addClub(sportsClub);
                currentClubId += 1;
                System.out.println("\nClub is added successfully");

            }
            //delete a sport club from the premier league
            else if (option.equals("D")) {

                List<SportsClub> sportsClubList = leagueManager.getAllSportsClubs();
                for (SportsClub sportsClub : sportsClubList) {
                    System.out.println(sportsClub.getClubId() + ":" + sportsClub.getName());
                }
                System.out.print("\nEnter Club id: ");
                int selectedClubId;
                if (consoleScanner.hasNextInt()) {
                    selectedClubId = consoleScanner.nextInt();

                    //selected club details
                    SportsClub club = leagueManager.getAllSportsClubs().stream()
                            .filter(c -> c.getClubId() == selectedClubId)
                            .findFirst()
                            .orElse(null);

                    if (club != null) {

                        //deleting club
                        leagueManager.deleteClub(selectedClubId);
                        System.out.println(club.getName() + " club is deleted");
                    } else {
                        System.out.println("\nClub is not available");
                    }


                } else {
                    System.out.println("\nInvalid number !!");
                    continue;
                }


            }
            //view statistics of a club
            else if (option.equals("S")) {
                List<SportsClub> sportsClubList = leagueManager.getAllSportsClubs();


                for (SportsClub sportsClub : sportsClubList) {
                    System.out.println("(" + sportsClub.getClubId() + ")" + ":" + sportsClub.getName());
                }
                int selectedClubId;
                System.out.print("Enter Club ID: ");


                if (consoleScanner.hasNextInt()) {
                    selectedClubId = consoleScanner.nextInt();
                    //print all the statistics
                    this.printClubStatistics(selectedClubId);
                } else {
                    System.out.println("\nInvalid number !!");
                    continue;
                }


            } else if (option.equals("P")) {

                //check whether we have at least 2 teams
                if (leagueManager.getAllSportsClubs().size() < 2) {
                    System.out.printf("need at least 2 sports club!");
                    continue;
                }

                this.playMatch();
                System.out.println(matchDetails);
            }

            //get the points table of the premier league
            else if (option.equals("T")) {

                List<SportsClub> sportsClubList = leagueManager.getAllSportsClubs();

                //sorting the table based the points & goals
                Collections.sort(sportsClubList, Collections.reverseOrder());

                //creating the points table
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.printf("%10s%20s%10s%10s\t%10s\t%10s%10s%10s", "Club Id", "Name", "points",
                        "Goals", "Matches", "wins", "loses", "draws"
                );
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------------------");

                for (SportsClub sportsClub : sportsClubList) {
                    FootballClub footballClub = (FootballClub) sportsClub;
                    System.out.format("%10d%20s%10d%10d\t%10d\t%10d%10d%10d",
                            footballClub.getClubId(), footballClub.getName(), footballClub.getPoints(),
                            footballClub.getNoOfGoals(),
                            sportsClub.getNoOfMatches(), sportsClub.getWins(), sportsClub.getLoses(), sportsClub.getDraws()
                    );
                    System.out.println();
                }
                System.out.println("-----------------------------------------------------------------------------------------------------");


            }

            //get the match statistics table of the premier league
            else if (option.equals("M")) {


                //creating the points table
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%10s%20s%20s%20s%20s%20s\t%20s", "Match ID", "Club 1", "Score", "Club 2", "Score",
                        "Winner", "Date/Time"
                );
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");

                //preparing table data
                for (MatchesStatistics matchesStatistics : matchesStatisticsList) {


                    System.out.format("%10d%20s%20s%20s%20s%20s\t%20s",
                            matchesStatistics.getMatchId(), matchesStatistics.getTeamAName(), matchesStatistics.getTeamAScore(),
                            matchesStatistics.getTeamBName(), matchesStatistics.getTeamBScore(),
                            matchesStatistics.getWinningTeamName().equals("0") ? "Drawn" : matchesStatistics.getWinningTeamName(), matchesStatistics.getTimestamp()
                    );
                    System.out.println();
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");


            }
            //exit application
            else if (option.equals("Q")) {
                //deserializing before close the application
                PremierLeaguePersister premierLeaguePersister = new PremierLeaguePersister();
                PremierLeagueManager premierLeagueManager = (PremierLeagueManager) leagueManager;
                PremierLeague footballPremierLeague = premierLeagueManager.getPremierLeague();
                premierLeaguePersister.serialisePremierLeague(footballPremierLeague);

                SportsClubPersister sportsClubPersister = new SportsClubPersister();
                sportsClubPersister.serialiseSportsClub(leagueManager.getAllSportsClubs());

                MatchesStatisticsPersister matchesStatisticsPersister = new MatchesStatisticsPersister();
                matchesStatisticsPersister.serialiseMatchesStatistics(matchesStatisticsList);


                System.out.println("Exiting from the program");
                return;
            } else {
                System.out.println("Please Enter Valid Input");
            }

        } while (!option.equals("Q"));

    }

    //method to print club statistics
    public void printClubStatistics(int selectedClubId) {
        List<SportsClub> sportsClubList = leagueManager.getAllSportsClubs();
        for (SportsClub sportsClub : sportsClubList) {
            if (sportsClub.getClubId() == selectedClubId) {
                FootballClub footballClub = (FootballClub) sportsClub;
                System.out.println("-------Premier League Statistics-------");
                System.out.println("Club Id: " + footballClub.getClubId());
                System.out.println("Club Name: " + footballClub.getName());
                System.out.println("Points: " + footballClub.getPoints());
                System.out.println("No Of Goals: " + footballClub.getNoOfGoals());

                System.out.println("-------Overall Statistics-------");
                System.out.println("Total Played matches: " + sportsClub.getNoOfMatches());
                System.out.println("Total wins: " + sportsClub.getWins());
                System.out.println("Total loses: " + sportsClub.getLoses());
                System.out.println("Total draws: " + sportsClub.getDraws());

            }
        }
    }

    //showing the menu
    private void showMenu() {
        System.out.println("\n");
        System.out.println("A: Add a Football Club");
        System.out.println("D: Delete a Football Club");
        System.out.println("S: Display the statistics of a club");
        System.out.println("P: Play a match");
        System.out.println("T: Display the Premier League Table");
        System.out.println("M: Display match statistics Table");
        System.out.println("Q: Exit");
        System.out.println("\n");

    }


    //method to get random teams for the registered sports clubs
    public static List<SportsClub> getRandomTeams(List<SportsClub> lst, int n) {
        List<SportsClub> copy = new LinkedList<SportsClub>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    //method to get random goals for a team in a match
    public static int getRandomGoals(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }


    public static String getMatchDetails() {
        return matchDetails;
    }

    public static List<MatchesStatistics> getMatchesStatisticsList() {
        return matchesStatisticsList;
    }


    // get a two teams match
    public void playMatch() {

        if (leagueManager.getAllSportsClubs().size() < 2) {

            matchDetails = "need at least 2 sports club!";
            return;
        }
        //getting random 2 teams for match
        List<SportsClub> twoTeams = getRandomTeams(this.leagueManager.getAllSportsClubs(), 2);

        int teamAGoalsInMatch = 0, teamBGoalsInMatch = 0;
        MatchesStatistics matchesStatistics = new MatchesStatistics();
        matchesStatistics.setMatchId(matchId);
        Calendar cal = Calendar.getInstance();

        if (perDayMatchesCount == 2) {
            perDayMatchesCount = 1;
            daysIncreased += 1;
        } else {
            perDayMatchesCount += 1;
        }
        cal.add(Calendar.DAY_OF_MONTH, daysIncreased);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm").format(cal.getTime());
        matchesStatistics.setTimestamp(timeStamp);

        int count = 0;
        for (SportsClub sportsClub : twoTeams) {
            FootballClub footballClub = (FootballClub) sportsClub;
            if (count == 0) {
                //adding random goals for team A
                teamAGoalsInMatch = getRandomGoals(0, 5);
                footballClub.setNoOfGoals(teamAGoalsInMatch + footballClub.getNoOfGoals());
                footballClub.setNoOfMatches(footballClub.getNoOfMatches() + 1);
                matchesStatistics.setTeamAName(footballClub.getName());
                matchesStatistics.setTeamAScore(teamAGoalsInMatch);
                matchDetails = "Team " + sportsClub.getName();
            } else {
                //adding random goals for team B
                teamBGoalsInMatch = getRandomGoals(0, 5);
                footballClub.setNoOfGoals(teamBGoalsInMatch + footballClub.getNoOfGoals());
                footballClub.setNoOfMatches(footballClub.getNoOfMatches() + 1);
                matchesStatistics.setTeamBName(footballClub.getName());
                matchesStatistics.setTeamBScore(teamBGoalsInMatch);
                matchDetails = matchDetails + " and Team " + sportsClub.getName() + " played a match.\n";
            }
            count += 1;
        }

        //check the goals and decide the result
        if (teamAGoalsInMatch == teamBGoalsInMatch) {
            for (SportsClub sportsClub : twoTeams) {

                FootballClub footballClub = (FootballClub) sportsClub;
                footballClub.setPoints(footballClub.getPoints() + 1);
                footballClub.setDraws(footballClub.getDraws() + 1);

            }


        } else {

            if (teamAGoalsInMatch > teamBGoalsInMatch) {
                FootballClub footballClub = (FootballClub) twoTeams.get(0);
                footballClub.setWins(footballClub.getWins() + 1);
                footballClub.setPoints(footballClub.getPoints() + 2);
                footballClub = (FootballClub) twoTeams.get(1);
                footballClub.setLoses(footballClub.getLoses() + 1);

            } else {
                FootballClub footballClub = (FootballClub) twoTeams.get(1);
                footballClub.setWins(footballClub.getWins() + 1);
                footballClub.setPoints(footballClub.getPoints() + 2);
                footballClub = (FootballClub) twoTeams.get(0);
                footballClub.setLoses(footballClub.getLoses() + 1);
            }
        }

        //preparing match summary
        if (teamAGoalsInMatch > teamBGoalsInMatch) {
            matchesStatistics.setWinningTeamName(twoTeams.get(0).getName());
            matchDetails = matchDetails + "Team " + twoTeams.get(0).getName() + " won the match by " + teamAGoalsInMatch + " score.\n";
        } else if (teamAGoalsInMatch < teamBGoalsInMatch) {
            matchesStatistics.setWinningTeamName(twoTeams.get(1).getName());
            matchDetails = matchDetails + "Team " + twoTeams.get(1).getName() + " won the match by " + teamBGoalsInMatch + " score.\n";
        } else {
            matchesStatistics.setWinningTeamName("0");
            matchDetails = matchDetails + "Match is tied. both team scored " + teamAGoalsInMatch + " score.\n";
        }
        matchesStatisticsList.add(matchesStatistics);
        matchId += 1;
    }
}



