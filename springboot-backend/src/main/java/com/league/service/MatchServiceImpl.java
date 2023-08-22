package com.league.service;

import com.league.SpringbootBackendApplication;
import com.league.pojo.MatchesStatistics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {




    @Override
    public String playMatch() {

       SpringbootBackendApplication.main.playMatch();
        return SpringbootBackendApplication.main.getMatchDetails();
    }

    @Override
    public List<MatchesStatistics> getMatchesStatistics() {

        return SpringbootBackendApplication.main.getMatchesStatisticsList();
    }
}
