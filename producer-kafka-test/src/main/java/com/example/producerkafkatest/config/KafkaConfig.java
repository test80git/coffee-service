package com.example.producerkafkatest.config;

import com.example.core.CoffeeBeansEvent;
import com.example.core.messageSMS.MessageEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;
    @Value(value = "${spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value(value = "${spring.kafka.producer.value-serializer}")
    private String valueSerializer;
    @Value(value = "${spring.kafka.producer.acks}")
    private String acks;
    @Value(value = "${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;
    @Value(value = "${spring.kafka.producer.properties.linger.ms}")
    private String linger;
    @Value(value = "${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;
    @Value(value = "${spring.kafka.producer.properties.enable.idempotence}")
    private String idempotence;
    @Value(value = "${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private String maxInflightRequests;

    Map<String, Object> producerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        config.put(ProducerConfig.ACKS_CONFIG, acks);
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout);
        config.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, idempotence);
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInflightRequests);

        return config;
    }

    @Bean
    ProducerFactory<String, CoffeeBeansEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    KafkaTemplate<String, CoffeeBeansEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    ProducerFactory<String, MessageEvent> messageFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }


    @Bean
    KafkaTemplate<String, MessageEvent> kafkaMsgTemplate() {
        return new KafkaTemplate<>(messageFactory());
    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("beans-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "1"))
                .build();
    }

    @Bean
    NewTopic createMessageTopic() {
        return TopicBuilder.name("message-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "1"))
                .build();
    }
}
