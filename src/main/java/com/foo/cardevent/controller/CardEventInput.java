package com.foo.cardevent.controller;

/**
 * Card Event
 */
public record CardEventInput(
        Long accountId,
        String cardEventType,
        Double amount
        ) {
}
