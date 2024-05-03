package com.example.gamingservice.service;

import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.dto.ScoreMessage;

import java.util.List;

public interface ScoreService {
    List<PlayerScore> getTopPlayers() throws Exception;

    void insertScore(ScoreMessage scoreMessage) throws Exception;
}
