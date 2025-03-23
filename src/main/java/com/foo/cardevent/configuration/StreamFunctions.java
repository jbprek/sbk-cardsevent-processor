package com.foo.cardevent.configuration;

import com.foo.cardevent.core.model.CardEventRecord;
import lombok.extern.slf4j.Slf4j;
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
    // @Bean
    public Consumer<CardEventRecord> logCardEvents() {
        return cardEventRecord -> log.info("Received card event: {}", cardEventRecord);
    }

    /**
     * Processes card events and produces enriched events
     */
    // @Bean
    public Function<CardEventRecord, CardEventRecord> processCardEvents() {
        return cardEventRecord -> {
            log.info("Processing card event: {}", cardEventRecord);

            // Apply some business logic based on the event type
            if (cardEventRecord.cardEventType() == "CARD_REFUND") {
                // For demonstration purposes - create a processed event
                return new CardEventRecord(
                        cardEventRecord.accountId(),
                        "CARD_REFUND",
                        cardEventRecord.amount(),
                        Instant.now()
                );
            }

            return cardEventRecord;
        };
    }

    /**
     * Generates sample card events periodically
     */
    //    @Bean - Disabled
    public Supplier<CardEventRecord> generateCardEvents() {
        return () -> {
            CardEventRecord event = new CardEventRecord(
                    100L + randSeed.nextLong() * 900L,
                    Math.random() > 0.5 ? "CARD_ATM_DEPOSIT" : "CARD_ATM_WITHDRAWAL",
                    Math.round(Math.random() * 10000) / 100.0,
                    Instant.now()
            );
            log.info("Generated card event: {}", event);
            return event;
        };
    }

    /**
     * Function to filter high-value card transactions (amount > 100)
     */
    // @Bean
    public Function<CardEventRecord, CardEventRecord> filterHighValueTransactions() {
        return cardEventRecord -> {
            if (cardEventRecord.amount() > 100.0) {
                log.info("High-value transaction detected: {}", cardEventRecord);
                return cardEventRecord;
            }
            return null; // Will not be sent downstream
        };
    }

}