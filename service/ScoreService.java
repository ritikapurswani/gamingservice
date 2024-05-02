package com.example.gamingservice.service;

import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.dto.ScoreMessage;

import java.util.List;

public interface ScoreService {
    List<PlayerScore> getTopPlayers();

    void insertScore(ScoreMessage scoreMessage);
}
