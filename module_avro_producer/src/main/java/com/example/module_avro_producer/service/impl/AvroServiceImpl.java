package com.example.module_avro_producer.service.impl;

import com.example.avro.Student;
import com.example.core.studentAvro.AvroRequest;
import com.example.module_avro_producer.service.AvroService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class AvroServiceImpl implements AvroService {

    private final KafkaTemplate<String, Student> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public String avroSend(AvroRequest avro) throws ExecutionException, InterruptedException {
        LOGGER.info("avro => {}", avro);

        String studentId = UUID.randomUUID().toString();
        Random random = new Random();
        long aLong = random.nextLong(100);
        Student student = Student.newBuilder()
                .setId(aLong)
                .setName(avro.getName())
                .setAge(avro.getAge())
                .setCourse(avro.getCourse())
                .build();

        LOGGER.info("student => {}", student);

        ProducerRecord<String, Student> record = new ProducerRecord<>("avro-topic", String.valueOf(aLong), student);
        SendResult<String, Student> metadata = kafkaTemplate.send(record).get();

        LOGGER.info("Sent record with key={}, to partition={}, offset={}\n",
                studentId,
                metadata.getRecordMetadata().partition(),
                metadata.getRecordMetadata().offset());

        return studentId;
    }
}
