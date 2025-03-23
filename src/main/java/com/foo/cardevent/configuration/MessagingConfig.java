package com.foo.cardevent.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@Slf4j
@Configuration
public class MessagingConfig {

    @Bean
    public ListenerContainerCustomizer<AbstractMessageListenerContainer<String, Object>> customErrorHandler() {

        return (container, destinationName, group) ->
            container.setCommonErrorHandler(
                    new DefaultErrorHandler((consumerRecord, exception) ->
                log.error("Error processing message: {} for record: {}", exception.getMessage(), consumerRecord, exception)
            , new FixedBackOff(0L, 0L))); // No retries
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
