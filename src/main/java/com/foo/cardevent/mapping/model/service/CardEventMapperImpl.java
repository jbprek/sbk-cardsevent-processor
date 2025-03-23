package com.foo.cardevent.mapping.model.service;

import com.foo.cardevent.controller.CardEventInput;
import com.foo.cardevent.core.model.CardEventRecord;
import com.other.cardevent.avro.CardEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CardEventMapperImpl implements CardEventMapper {
    @Override
    public CardEvent fromInput(CardEventInput cardEventInput) {
        if (cardEventInput == null) {
            return null;
        }

        return CardEvent.newBuilder()
                .setAccountId(cardEventInput.accountId())
                .setCardEventType(cardEventInput.cardEventType())
                .setAmount(cardEventInput.amount())
                .setTimestamp(Instant.now()) // Assuming generated timestamp
                .build();
    }   

    @Override
    public CardEventRecord toRecord(CardEvent cardEvent) {
        if (cardEvent == null) {
            return null;
        }
        return new CardEventRecord(
                cardEvent.getAccountId(),
                cardEvent.getCardEventType(),
                cardEvent.getAmount(),
                cardEvent.getTimestamp()
        );
    }
}
