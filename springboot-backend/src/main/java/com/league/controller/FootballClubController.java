package com.league.controller;

import com.league.FootballClub;
import com.league.pojo.SportsClub;
import com.league.service.FootballClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class FootballClubController {

    @Autowired
    private FootballClubService footballClubService;

    // get all Football Club
    @GetMapping("/footballClubs")
    public List<FootballClub> getAllFootballClub() {
        return footballClubService.getAllFootballClub();
    }

    // create Football Club rest api
    @PostMapping("/footballClubs")
    public SportsClub createFootballClub(@RequestBody FootballClub footballClub) {

        return footballClubService.createFootballClub(footballClub);
    }

    // get Football Club by id rest api
    @GetMapping("/footballClubs/{id}")
    public ResponseEntity<FootballClub> getFootballClubById(@PathVariable Integer id) {
        FootballClub footballClub = footballClubService.getFootballClubById(id);
        return ResponseEntity.ok(footballClub);
    }

    // update Football Club rest api
    @PutMapping("/footballClubs/{id}")
    public ResponseEntity<FootballClub> updateFootballClub(@PathVariable Integer id, @RequestBody FootballClub footballClub) {
        FootballClub updateFootballClub = footballClubService.updateFootballClub(id, footballClub);
        return ResponseEntity.ok(updateFootballClub);
    }

    // delete Football Club rest api
    @DeleteMapping("/footballClubs/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFootballClub(@PathVariable Integer id) {

        footballClubService.deleteFootballClub(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
