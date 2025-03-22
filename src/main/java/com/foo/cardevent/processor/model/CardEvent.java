package com.foo.cardevent.processor.model;

/**]
 * Card Event
 */
public record CardEvent(
        long accountId,
        CardEventType cardEventType,
        double amount,
        long timestamp) {
}
