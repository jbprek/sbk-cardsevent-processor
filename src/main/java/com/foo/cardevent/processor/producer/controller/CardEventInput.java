package com.foo.cardevent.processor.producer.controller;

import com.foo.cardevent.processor.model.CardEventType;

/**
 * Card Event
 */
public record CardEventInput(
        Long accountId,
        CardEventType cardEventType,
        Double amount
        ) {
}
