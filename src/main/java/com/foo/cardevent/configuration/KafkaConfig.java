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

//    @Bean
//    public ConsumerFactory<String, CardEvent> consumerFactory() {
//        JsonDeserializer<CardEvent> deserializer = new JsonDeserializer<>(CardEvent.class);
//
//        // Configure the trusted packages
//        deserializer.addTrustedPackages("com.foo.cardevent.core.model");
//
//        Map<String, Object> configs = Map.of(JsonDeserializer.TRUSTED_PACKAGES, "com.foo.cardevent.core.model");
////        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "card-event-group");
////        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "com.foo.cardevent.core.model"); // Alternative config method
//
//        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
//    }



//
//    private final ConsumerFactory<String, CardEvent> consumerFactory;
//
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, CardEvent> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, CardEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }
}