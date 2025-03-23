package com.foo.cardevent.configuration;

import com.foo.cardevent.core.model.CardEvent;
import com.foo.cardevent.core.model.CardEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Configuration
public class StreamFunctions {

    private final Random randSeed = new Random();

    /**
     * Consumes card events and logs them
     */
    @Bean
    public Consumer<CardEvent> logCardEvents() {
        return cardEvent -> log.info("Received card event: {}", cardEvent);
    }

    /**
     * Processes card events and produces enriched events
     */
    // @Bean
    public Function<CardEvent, CardEvent> processCardEvents() {
        return cardEvent -> {
            log.info("Processing card event: {}", cardEvent);

            // Apply some business logic based on the event type
            if (cardEvent.cardEventType() == CardEventType.CARD_REFUND) {
                // For demonstration purposes - create a processed event
                return new CardEvent(
                        cardEvent.accountId(),
                        CardEventType.CARD_REFUND,
                        cardEvent.amount(),
                        Instant.now().toEpochMilli()
                );
            }

            return cardEvent;
        };
    }

    /**
     * Generates sample card events periodically
     */
    //    @Bean - Disabled
    public Supplier<CardEvent> generateCardEvents() {
        return () -> {
            CardEvent event = new CardEvent(
                    100L + randSeed.nextLong() * 900L,
                    Math.random() > 0.5 ? CardEventType.CARD_ATM_DEPOSIT : CardEventType.CARD_ATM_WITHDRAWAL,
                    Math.round(Math.random() * 10000) / 100.0,
                    Instant.now().toEpochMilli()
            );
            log.info("Generated card event: {}", event);
            return event;
        };
    }

    /**
     * Function to filter high-value card transactions (amount > 100)
     */
    // @Bean
    public Function<CardEvent, CardEvent> filterHighValueTransactions() {
        return cardEvent -> {
            if (cardEvent.amount() > 100.0) {
                log.info("High-value transaction detected: {}", cardEvent);
                return cardEvent;
            }
            return null; // Will not be sent downstream
        };
    }

}