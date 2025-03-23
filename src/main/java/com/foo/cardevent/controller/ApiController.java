package com.foo.cardevent.controller;

import com.foo.cardevent.core.model.CardEventDto;
import com.foo.cardevent.mapping.model.service.CardEventMapper;
import com.other.cardevent.avro.CardEvent;
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

	private final KafkaTemplate<String, CardEvent> template;
    private final CardEventMapper cardEventMapper;

    @PostMapping
    public void  sendMessage(@RequestBody CardEventInput eventInput) {
        var event = cardEventMapper.fromInput(eventInput);
        this.template.send("cards-events-avro-topic", event);
    }   

}
