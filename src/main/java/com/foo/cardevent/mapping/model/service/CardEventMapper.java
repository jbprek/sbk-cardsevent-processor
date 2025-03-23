package com.foo.cardevent.mapping.model.service;

import com.foo.cardevent.controller.CardEventInput;
import com.foo.cardevent.mapping.model.CardEventRecord;
import com.other.cardevent.avro.CardEvent;

public interface CardEventMapper {

    CardEvent fromInput(CardEventInput cardEventInput);
    CardEventRecord toRecord(CardEvent cardEvent);
}
