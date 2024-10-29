package com.example.coffeebeans.service.kafka;

import com.example.coffeebeans.model.entity.ArrivalOfCoffeeBeans;
import com.example.coffeebeans.model.mapper.BeansMapper;
import com.example.coffeebeans.service.BeansService;
import com.example.core.CoffeeBeansEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GrainConsumerTest {

    @Mock
    private BeansService beansService;

    @Mock
    private BeansMapper beansMapper;

    @InjectMocks
    private BeansConsumer beansConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void consume_shouldProcessBeansMessage() {
        // Arrange
        CoffeeBeansEvent grainMessage = new CoffeeBeansEvent("Arabica", "Brazil", 80.0f, 20.0f, 100L);
        ArrivalOfCoffeeBeans arrivalOfCoffeeBeans = new ArrivalOfCoffeeBeans();

        when(beansMapper.toResponse(grainMessage)).thenReturn(arrivalOfCoffeeBeans);

        // Act
        beansConsumer.consume(grainMessage);

        // Assert
        verify(beansMapper).toResponse(grainMessage);
        verify(beansService).saveBeans(arrivalOfCoffeeBeans);
    }
}
