package com.example.gamingservice.dao;

import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.dto.ScoreMessage;

import java.util.List;

public interface PlayerScoresDao {
    List<PlayerScore> getTopPlayers(int limit);

    void insertScore(ScoreMessage scoreMessage);
}
