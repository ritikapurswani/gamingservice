package com.example.gamingservice.controller;
import com.example.gamingservice.dto.PlayerScore;
import com.example.gamingservice.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/fetch/topPlayers")
public class ScoreController {
        private static final Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);

        @Autowired
        private ScoreService scoreService;

        @GetMapping
        public List<PlayerScore> getTopPlayers(@RequestHeader Map<String,Object> headers) throws Exception {
                LOGGER.info("Fetching top players");
                return scoreService.getTopPlayers();
        }
}
