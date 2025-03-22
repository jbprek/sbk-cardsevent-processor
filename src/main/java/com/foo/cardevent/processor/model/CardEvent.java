package com.foo.cardevent.processor.model;

public record CardEvent(
        long accountId,
        CardEventType cardEventType,
        double amount,
        long timestamp) {
}
