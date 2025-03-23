package com.foo.cardevent.configuration;

import com.foo.cardevent.core.model.CardEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {


    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CardEvent> kafkaListenerContainerFactory(ConsumerFactory<String, CardEvent> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CardEvent>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }

}