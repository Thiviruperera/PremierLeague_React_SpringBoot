package com.league.persister;


import com.league.pojo.PremierLeague;

import java.io.*;
import java.util.StringTokenizer;

public class PremierLeaguePersister {

    private static final String PREMIER_LEAGUE_FILE_NAME = Constants.PROJECT_PATH+"\\PremierLeague.txt";


    //serialising PremierLeague data
    public void serialisePremierLeague(PremierLeague premierLeague) {
        FileHandler fileHandler = new FileHandler();
        System.out.println("Saving PremierLeague Data");
        try {
            fileHandler.clearFile(PREMIER_LEAGUE_FILE_NAME);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String dataLine = String.valueOf(premierLeague.getId()) + "_" + premierLeague.getName();

        try {
            System.out.println(dataLine);
            fileHandler.addDataToFile(dataLine, PREMIER_LEAGUE_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Saving PremierLeague Data : Success");
    }


    //deserialising PremierLeague data
    public PremierLeague deserialisePremierLeague() {

        File file = new File(PREMIER_LEAGUE_FILE_NAME);//opening the file

        BufferedReader br = null;
        PremierLeague premierLeague = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st = "";
            while ((st = br.readLine()) != null) { //read line by line
                StringTokenizer stok = new StringTokenizer(st);  //Extracting both premierLeagueId and premierLeagueName
                int premierLeagueId = Integer.parseInt(stok.nextToken("_"));
                String premierLeagueName = stok.nextToken("_");
                premierLeague = new PremierLeague(premierLeagueId, premierLeagueName);

            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR:- No File Found...");
        } catch (IOException e) {
        }
        System.out.println("Loading PremierLeague Data : Success");
        return premierLeague;
    }


}
