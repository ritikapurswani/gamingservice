package com.example.gamingservice.dao.impl;

import com.example.gamingservice.constants.Queries;
import com.example.gamingservice.consumer.ScoreConsumer;
import com.example.gamingservice.dao.PlayerScoresDao;
import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.dto.ScoreMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collections;
import java.util.List;

@Repository
public class PlayerScoresDaoImpl implements PlayerScoresDao {
    private static final Logger LOGGER = LogManager.getLogger(ScoreConsumer.class);

    @Autowired
    @Qualifier("masterJdbcTemplate")
    JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("slaveJdbcTemplate")
    JdbcTemplate jdbcTemplateSlave;

    @Override
    public List<PlayerScore> getTopPlayers(int limit) throws Exception {
        try {
            return jdbcTemplateSlave.query(Queries.FETCH_TOP_PLAYERS, new Object[]{limit}, (rs, rowNum) -> {
                PlayerScore score = new PlayerScore();
                score.setPlayerName(rs.getString("player_name"));
                score.setScore(rs.getInt("score"));
                return score;
            });
        } catch (Exception e) {
            LOGGER.error("Exception occurred in fetching top players: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void insertScore(ScoreMessage scoreMessage) {
        jdbcTemplate.update(Queries.INSERT_SCORE, scoreMessage.getPlayerName(), scoreMessage.getScore(), scoreMessage.getCustId());
    }
}
