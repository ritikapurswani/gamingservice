package com.example.gamingservice.dao.impl;

import com.example.gamingservice.constants.Queries;
import com.example.gamingservice.dao.PlayerScoresDao;
import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.dto.ScoreMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Repository
public class PlayerScoresDaoImpl implements PlayerScoresDao {
    @Autowired
    @Qualifier("masterJdbcTemplate")
    JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("slaveJdbcTemplate")
    JdbcTemplate jdbcTemplateSlave;

    @Override
    public List<PlayerScore> getTopPlayers(int limit) {
        return jdbcTemplateSlave.query(Queries.FETCH_TOP_PLAYERS, new Object[]{limit}, (rs, rowNum) -> {
            PlayerScore score = new PlayerScore();
            score.setPlayerName(rs.getString("player_name"));
            score.setScore(rs.getInt("score"));
            return score;
        });
    }

    @Override
    public void insertScore(ScoreMessage scoreMessage) {
        jdbcTemplate.update(Queries.INSERT_SCORE, scoreMessage.getPlayerName(), scoreMessage.getScore(), scoreMessage.getCustId());
    }
}
