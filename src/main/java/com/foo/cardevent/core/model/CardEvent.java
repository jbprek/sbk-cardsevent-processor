package com.foo.cardevent.core.model;

import java.time.Instant;

/**]
 * Card Event
 */
public record CardEvent(
        long accountId,
        CardEventType cardEventType,
        double amount,
        long timestamp) {

    @Override
    public String toString() {
        return "CardEvent{" +
                "accountId=" + accountId +
                ", cardEventType=" + cardEventType +
                ", amount=" + amount +
                ", timestamp=" + Instant.ofEpochMilli(timestamp) +
                '}';
    }
}
