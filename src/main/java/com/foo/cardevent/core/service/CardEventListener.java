package com.foo.cardevent.core.service;

import com.foo.cardevent.core.model.CardEventDto;

import com.foo.cardevent.mapping.model.service.CardEventMapper;
import com.other.cardevent.avro.CardEvent;
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
    private final CardEventMapper cardEventMapper;


    @KafkaListener(topics = "cards-events-avro-topic", groupId = "cards-events-avro-group-1")
    public void listen(CardEvent cardEventDto) {
        var record = cardEventMapper.toRecord(cardEventDto);
        log.info("Received Card Event: " + record);
    }
}