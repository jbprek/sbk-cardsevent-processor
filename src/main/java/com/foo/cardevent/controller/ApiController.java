package com.foo.cardevent.controller;

import com.foo.cardevent.core.model.CardEventRecord;
import com.foo.cardevent.mapping.model.service.CardEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final String CARD_EVENTS_BINDING = "logCardEvents-out-0";

    private final CardEventMapper cardEventMapper;
    private final StreamBridge streamBridge;

    @PostMapping
    public void  sendMessage(@RequestBody CardEventInput eventInput) {
        var event = cardEventMapper.fromInput(eventInput);

        // Check if event is not null before sending
        if (event != null) {
            boolean result = this.streamBridge.send(CARD_EVENTS_BINDING, event);

            if (!result) {
                throw new RuntimeException("Failed to send message to " + CARD_EVENTS_BINDING);
            }
        } else {
            throw new IllegalArgumentException("Cannot send null event");
        }

    }   

}
