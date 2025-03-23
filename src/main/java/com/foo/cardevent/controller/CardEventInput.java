package com.foo.cardevent.controller;

import com.foo.cardevent.core.model.CardEventType;

/**
 * Card Event
 */
public record CardEventInput(
        Long accountId,
        CardEventType cardEventType,
        Double amount
        ) {
}
