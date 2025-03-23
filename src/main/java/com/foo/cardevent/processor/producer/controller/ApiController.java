package com.foo.cardevent.processor.producer.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foo.cardevent.processor.model.CardEvent;

@RestController
@RequestMapping("/api")
public class ApiController {

    
	@Autowired
	private KafkaTemplate<Object, Object> template;

    @PostMapping
    public void  sendMessage(CardEventInput eventInput) {
        var  event = new CardEvent(eventInput.accountId(), eventInput.cardEventType(), eventInput.amount(), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        this.template.send("cards-events-json-topic", event);
    }   

}
