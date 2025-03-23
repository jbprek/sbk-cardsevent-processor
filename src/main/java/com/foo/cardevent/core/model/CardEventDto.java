package com.foo.cardevent.core.model;

import java.time.Instant;

/**]
 * Card Event
 */
public record CardEventDto(
        long accountId,
        CardEventType cardEventType,
        double amount,
        long timestamp) {

    @Override
    public String toString() {
        return "CardEvent{accountId=%d, cardEventType=%s, amount=%.2f, timestamp=%s}"
                .formatted(accountId, cardEventType, amount, Instant.ofEpochMilli(timestamp));
    }
}
