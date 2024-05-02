package com.example.gamingservice.service.impl;

import com.example.gamingservice.dao.PlayerScoresDao;
import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.dto.ScoreMessage;
import com.example.gamingservice.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private PlayerScoresDao playerScoresDao;

    @Value("${top.players.limit:5}")
    int limit;

    @Override
    public List<PlayerScore> getTopPlayers() {
        return playerScoresDao.getTopPlayers(limit);
    }

    @Override
    public void insertScore(ScoreMessage scoreMessage) {
        playerScoresDao.insertScore(scoreMessage);

    }
}
