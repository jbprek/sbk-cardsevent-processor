package com.foo.cardevent.processor.configuration;

import com.foo.cardevent.processor.model.CardEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final ConsumerFactory<String, CardEvent> consumerFactory;


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CardEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CardEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}