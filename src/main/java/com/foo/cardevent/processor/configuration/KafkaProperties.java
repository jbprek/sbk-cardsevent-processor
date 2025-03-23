package com.foo.cardevent.processor.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.kafka.topic")
public record KafkaProperties(
        String topic,
        String consumerGroup) {
}
