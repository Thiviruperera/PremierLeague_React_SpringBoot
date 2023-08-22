package com.league.controller;


import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class FootballClubControllerTest {


    @Test
    public void testGetAllFootballClubSuccess() throws URISyntaxException, JSONException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8080/api/v1/footballClubs";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        JSONArray jsonArray = new JSONArray(result.getBody());
        Assert.assertEquals(true, jsonArray.length()>0);
    }
}