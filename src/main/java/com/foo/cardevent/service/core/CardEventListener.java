package com.foo.cardevent.service.core;

import com.foo.cardevent.processor.model.CardEvent;

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

    @KafkaListener(topics = "cards-events-json-topic", groupId = "card-events-json-group-1", containerFactory = "kafkaListenerContainerFactory")
    public void listen(CardEvent cardEvent) {
        log.info("Received Card Event: " + cardEvent);
        // Process the CardEvent here
    }
}