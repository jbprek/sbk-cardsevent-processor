package com.foo.cardevent.core.model;

import java.time.Instant;

/**]
 * Card Event
 */
public record CardEventRecord(
        long accountId,
        String cardEventType,
        double amount,
        Instant timestamp) {

}
