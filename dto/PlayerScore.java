package com.example.gamingservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerScore {
    private String playerName;
    private long score;

    public PlayerScore(String playerName, long score) {
        this.playerName = playerName;
        this.score = score;
    }

    public PlayerScore() {

    }
}
