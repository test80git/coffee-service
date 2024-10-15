package com.example.coffeebeans.config;


import com.example.coffeebeans.service.grpc.GRPCCoffeeBeansService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class GRPCServer {
    private final Logger LOGGER = LoggerFactory.getLogger(GRPCServer.class);

    @Value("${grpc.server.port}")
    private int port;

    private Server server;
    private final GRPCCoffeeBeansService grpcCoffeeBeansService;

    @PostConstruct
    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(grpcCoffeeBeansService)
                .build()
                .start();
        LOGGER.info("gRPC server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Shutting down gRPC server since JVM is shutting down");
            GRPCServer.this.stop();
            LOGGER.info("Server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    @PreDestroy
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
