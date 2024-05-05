package com.example.gamingservice.service.impl;

import com.example.gamingservice.dao.PlayerScoresDao;
import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.dto.ScoreMessage;
import com.example.gamingservice.service.ScoreService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    private static final Logger LOGGER = LogManager.getLogger(ScoreServiceImpl.class);

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
        if (validateScoreMessage(scoreMessage)) {
            try {
                playerScoresDao.insertScore(scoreMessage);
            } catch (DataAccessException e) {
                LOGGER.error("Data Access exception occurred while inserting the score: {}", e.getMessage());
            }
        } else {
            LOGGER.error("Error occurred in validating scoreMessage DTO ");
        }

    }

    private boolean validateScoreMessage(ScoreMessage scoreMessage) {
        return scoreMessage != null && scoreMessage.getScore() >= 0 &&
                !StringUtils.isBlank(scoreMessage.getUserId())
                && !StringUtils.isBlank(scoreMessage.getPlayerName());
    }
}
