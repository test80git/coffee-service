package com.example.module_avro_producer.controller;

import com.example.core.studentAvro.AvroRequest;
import com.example.module_avro_producer.service.AvroService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("avro")
@RestController
@RequiredArgsConstructor
public class AvroController {
    private final Logger LOGGER = LoggerFactory.getLogger(AvroController.class);
    private final AvroService avroService;

    @PostMapping
    public String avroController(@RequestBody AvroRequest avro) throws ExecutionException, InterruptedException {
        LOGGER.info(avro.getName());
        return avroService.avroSend(avro);
    }
}
