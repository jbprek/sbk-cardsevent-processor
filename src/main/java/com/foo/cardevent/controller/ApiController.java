package com.foo.cardevent.controller;

import com.foo.cardevent.core.model.CardEventRecord;
import com.foo.cardevent.mapping.model.service.CardEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final String CARD_EVENTS_BINDING = "cardEvents-out-0";

    private final CardEventMapper cardEventMapper;
    private final StreamBridge streamBridge;

    @PostMapping
    public void sendMessage(@RequestBody CardEventInput eventInput) {
        final var event = cardEventMapper.fromInput(eventInput);

        if (event == null) {
            log.error("Mapping resulted in null for input: {}", eventInput);
            throw new IllegalArgumentException("Cannot send event: mapped event is null");
        }

        final boolean result = this.streamBridge.send(CARD_EVENTS_BINDING, event);

        if (!result) {
            log.error("Failed to send message to binding '{}', payload: {}", CARD_EVENTS_BINDING, event);
            throw new RuntimeException("Failed to send message to " + CARD_EVENTS_BINDING);
        }

        log.info("Successfully posted event to binding '{}'", CARD_EVENTS_BINDING);
    }


}
