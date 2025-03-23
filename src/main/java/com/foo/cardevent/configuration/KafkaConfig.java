package com.foo.cardevent.configuration;

import com.other.cardevent.avro.CardEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import java.util.Map;


@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProducerProperties kafkaProducerProperties;

    @Bean
    public ProducerFactory<String, CardEvent> producerFactory(KafkaProducerProperties kafkaProducerProperties
    ) {
        return new DefaultKafkaProducerFactory<>(Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProducerProperties.bootstrapServers()

        ));


    }



    @Bean
    public KafkaTemplate<String, CardEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CardEvent> kafkaListenerContainerFactory(ConsumerFactory<String, CardEvent> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CardEvent>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}