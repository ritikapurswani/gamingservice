package com.example.gamingservice.consumer;

import com.example.gamingservice.dto.ScoreMessage;
import com.example.gamingservice.service.ScoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class ScoreConsumer {

    private static final Logger LOGGER = LogManager.getLogger(ScoreConsumer.class);


    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic:gaming-service-topic}", groupId = "${kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) throws Exception {
        LOGGER.info("Received message: {}", record.toString());
        try {
            LOGGER.info("Received message: {}", record.toString());
            String jsonString = record.value();
            ScoreMessage scoreDTO = objectMapper.readValue(jsonString, ScoreMessage.class);
            CompletableFuture.runAsync(() -> {
                try {
                    scoreService.insertScore(scoreDTO);
                } catch (Exception e) {
                    LOGGER.error("Error occurred in asynchronous database insertion: {}", e.getMessage());
                }
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing JSON: {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An error occurred while consuming the message: {}", e.getMessage());
        }
    }
}
