package com.foo.cardevent.mapping.model;

import java.time.Instant;

public record CardEventRecord(
        long accountId,
        String cardEventType,
        double amount,
        Instant timestamp
) {}

