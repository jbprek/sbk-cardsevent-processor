package com.foo.cardevent.controller;

import com.foo.cardevent.core.model.CardEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    
	private final KafkaTemplate<Object, Object> template;

    @PostMapping
    public void  sendMessage(@RequestBody CardEventInput eventInput) {
        var event = new CardEvent(eventInput.accountId(),
                eventInput.cardEventType(),
                eventInput.amount(),
                Instant.now().toEpochMilli());
        this.template.send("cards-events-json-topic", event);
    }   

}
