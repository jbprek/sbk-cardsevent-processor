package com.foo.cardevent.core.service;

import com.foo.cardevent.core.model.CardEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component

public class CardEventListener {


    private final KafkaProperties kafkaProperties;


    // @KafkaListener(topics = "#{@kafkaProperties.topic}", groupId = "#{@kafkaProperties.consumerGroup}", containerFactory = "kafkaListenerContainerFactory")

    @KafkaListener(topics = "cards-events-json-topic", groupId = "cards-events-json-group-1")
    public void listen(CardEvent cardEvent) {
        log.info("Received Card Event: " + cardEvent);
        // Process the CardEvent here
    }
}