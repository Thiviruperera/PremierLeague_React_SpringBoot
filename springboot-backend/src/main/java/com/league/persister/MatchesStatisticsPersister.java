package com.league.persister;

import com.league.pojo.MatchesStatistics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MatchesStatisticsPersister {

    private static final String STATISTICS_FILE_NAME = Constants.PROJECT_PATH+"\\Statistics.txt";


    //serialising match statistics data
    public void serialiseMatchesStatistics(List<MatchesStatistics> matchesStatisticsList) throws IOException {
        FileHandler fileHandler = new FileHandler();
        System.out.println("Saving Statistics Data");
        try {
            fileHandler.clearFile(STATISTICS_FILE_NAME);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (MatchesStatistics matchesStatistics : matchesStatisticsList) {
            String dataLine = matchesStatistics.getMatchId() + "_" + matchesStatistics.getTeamAName() + "_" + matchesStatistics.getTeamAScore() + "_" +
                    matchesStatistics.getTeamBName() + "_" + matchesStatistics.getTeamBScore() + "_" +
                    matchesStatistics.getWinningTeamName() + "_" + matchesStatistics.getTimestamp();

            try {
                System.out.println(dataLine);
                fileHandler.addDataToFile(dataLine, STATISTICS_FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Saving Statistics Data : Success");
    }


    //deserialising match statistics data
    public List<MatchesStatistics> deserialiseMatchesStatistics() {

        File file = new File(STATISTICS_FILE_NAME);//opening the file

        BufferedReader br = null;
        List<MatchesStatistics> matchesStatisticsList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String st = "";
            while ((st = br.readLine()) != null) { //read line by line
                StringTokenizer stok = new StringTokenizer(st);  //Extracting both seatID and Customer name
                int mactchId = Integer.parseInt(stok.nextToken("_"));
                String teamAName = stok.nextToken("_");
                int teamAScore = Integer.parseInt(stok.nextToken("_"));
                String teamBName = stok.nextToken("_");
                int teamBScore = Integer.parseInt(stok.nextToken("_"));
                String winningTeamName = stok.nextToken("_");
                String timestamp = stok.nextToken("_");

                MatchesStatistics matchesStatistics = new MatchesStatistics(mactchId, teamAName,  teamBName, teamAScore,teamBScore,
                        winningTeamName, timestamp);
                matchesStatisticsList.add(matchesStatistics);


            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR:- No File Found...");
        } catch (IOException e) {
        }
        System.out.println("Loading Statistics Data : Success");
        return matchesStatisticsList;
    }

}
