package com.foo.cardevent.configuration;

import com.other.cardevent.avro.CardEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Configuration
public class StreamFunctions {


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
    @Bean
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
    // @Bean
    public Supplier<CardEvent> generateCardEvents() {

        return () -> null;
    }

    /**
     * Function to filter high-value card transactions (amount > 100)
     */
    // @Bean
    //    public Function<CardEvent, CardEvent> filterHighValueTransactions() {
    //        return cardEventRecord -> {
    //            if (cardEventRecord.getAmount() > 100.0) {
    //                log.info("High-value transaction detected: {}", cardEventRecord);
    //                return cardEventRecord;
    //            }
    //            return null; // Will not be sent downstream
    //        };
    //    }

}