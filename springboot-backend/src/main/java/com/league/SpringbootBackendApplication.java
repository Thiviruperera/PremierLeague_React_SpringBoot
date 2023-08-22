package com.league;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {


    public static Main main;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringbootBackendApplication.class, args);


    }

    @Override
    public void run(String... strings) throws Exception {
        main = new Main();
        System.out.println("EXECUTING : command line runner");
        main.applicationStartup();

    }
}
