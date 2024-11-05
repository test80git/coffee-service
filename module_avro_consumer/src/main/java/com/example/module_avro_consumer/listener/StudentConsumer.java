package com.example.module_avro_consumer.listener;


import com.example.avro.Student;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "avro-topic", groupId = "avroGroup")
public class StudentConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(Student student) {

        LOGGER.info("id => {}", student.getId());
        LOGGER.info("name => {}", student.getName());
        LOGGER.info("age => {}", student.getAge());
        LOGGER.info("course => {}", student.getCourse());

    }

}
