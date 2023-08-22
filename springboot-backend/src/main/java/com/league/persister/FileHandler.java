package com.league.persister;


import java.io.*;

public class FileHandler {

    //add given details to the file
    public void addDataToFile(String line, String file) throws IOException {

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(line);
        bw.newLine();

        bw.close();
        fw.close();
    }


    //Clear the file content
    public void clearFile(String file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }

}
