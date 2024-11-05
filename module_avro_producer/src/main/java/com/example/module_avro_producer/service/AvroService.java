package com.example.module_avro_producer.service;

import com.example.core.studentAvro.AvroRequest;

import java.util.concurrent.ExecutionException;

public interface AvroService {

    String avroSend(AvroRequest avro) throws ExecutionException, InterruptedException;

}
