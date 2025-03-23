package com.foo.cardevent.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.kafka.consumer")
public record KafkaConsumerProperties(
        String bootstrapServers,
        String schemaRegistryUrl,
        String topic,
        String keyDeserializer,
        String valueDeserializer,
        String groupId,
        String autoOffsetReset
) {
}
