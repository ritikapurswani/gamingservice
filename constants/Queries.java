package com.example.gamingservice.constants;

public interface Queries {
    String FETCH_TOP_PLAYERS="SELECT PLAYER_NAME as player_name,SCORE_VALUE as score FROM scores ORDER BY score DESC, created_at ASC LIMIT ?";
    String INSERT_SCORE="INSERT INTO scores (player_name, score_value, user_id) VALUES (?, ?, ?)";
}
