package com.league.persister;


import com.league.FootballClub;
import com.league.pojo.SportsClub;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SportsClubPersister {

    private static final String SPORTS_CLUB_FILE_NAME = Constants.PROJECT_PATH+"\\SportsClub..txt";

    //serialising SportsClub data
    public void serialiseSportsClub(List<SportsClub> sportsClubList) throws IOException {
        FileHandler fileHandler = new FileHandler();
        System.out.println("Saving SportsClub Data");
        try {
            fileHandler.clearFile(SPORTS_CLUB_FILE_NAME);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (SportsClub sportsClub : sportsClubList) {
            FootballClub footballClub = (FootballClub) sportsClub;
            String dataLine = footballClub.getClubId() + "_" + footballClub.getName() + "_" + footballClub.getLocation() + "_" + footballClub.getPoints() + "_" + footballClub.getNoOfGoals() + "_" +
                    sportsClub.getNoOfMatches() + "_" + sportsClub.getWins() + "_" + sportsClub.getLoses() + "_" + sportsClub.getDraws();

            try {
                System.out.println(dataLine);
                fileHandler.addDataToFile(dataLine, SPORTS_CLUB_FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Saving SportsClub Data : Success");
    }


    //deserialising SportsClub data
    public List<SportsClub> deserialiseSportsClub() {

        File file = new File(SPORTS_CLUB_FILE_NAME);//opening the file

        BufferedReader br = null;
        List<SportsClub> sportsClubList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String st = "";
            while ((st = br.readLine()) != null) { //read line by line
                StringTokenizer stok = new StringTokenizer(st);  //Extracting both seatID and Customer name
                int clubId = Integer.parseInt(stok.nextToken("_"));
                String clubName = stok.nextToken("_");
                String clubLocation = stok.nextToken("_");
                int points = Integer.parseInt(stok.nextToken("_"));
                int goals = Integer.parseInt(stok.nextToken("_"));
                int noOfMatches = Integer.parseInt(stok.nextToken("_"));
                int wins = Integer.parseInt(stok.nextToken("_"));
                int loses = Integer.parseInt(stok.nextToken("_"));
                int draws = Integer.parseInt(stok.nextToken("_"));

                SportsClub footballClub = new FootballClub(clubId, clubName, clubLocation, noOfMatches, wins,
                        loses, draws, points, goals);
                sportsClubList.add(footballClub);


            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR:- No File Found...");
        } catch (IOException e) {
        }
        System.out.println("Loading SportsClub Data : Success");
        return sportsClubList;
    }

}
