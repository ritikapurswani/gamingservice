package com.example.gamingservice.consumer;

import com.example.gamingservice.dto.ScoreMessage;
import com.example.gamingservice.service.ScoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ScoreConsumer {

    private static final Logger LOGGER = LogManager.getLogger(ScoreConsumer.class);


    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic:gaming-service-topic}", groupId = "${kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
        LOGGER.info("Received message: {}", record.toString());
        String jsonString = record.value();
        ScoreMessage scoreDTO = objectMapper.readValue(jsonString, ScoreMessage.class);
        System.out.println("Received message: " + scoreDTO);
        scoreService.insertScore(scoreDTO);
    }
}
