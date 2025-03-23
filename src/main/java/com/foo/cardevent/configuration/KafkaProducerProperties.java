package com.foo.cardevent.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.kafka.producer")
public record KafkaProducerProperties(
        String bootstrapServers,
        String schemaRegistryUrl,
        String topic,
        String acks,
        String keySerializer,
        String valueSerializer
) {
}
