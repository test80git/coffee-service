package com.example.coffeebeans.service.impl;


import com.example.coffeebeans.model.entity.ArrivalOfCoffeeBeans;
import com.example.coffeebeans.model.response.BeansResponse;
import com.example.coffeebeans.repository.ArrivalOfCoffeeBeansRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BeansServiceImplTest {

    @Mock
    private ArrivalOfCoffeeBeansRepository arrivalOfCoffeeBeansRepository;

    @InjectMocks
    private BeansServiceImpl grainService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    void getFilteredBeans_shouldReturnFilteredBeans() {
        Optional<String> country = Optional.of("Brazil");
        Optional<String> variety = Optional.of("Arabica");
        List<BeansResponse> expectedResponse = List.of(new BeansResponse("Brazil", "Arabica", 80L));

        when(arrivalOfCoffeeBeansRepository.findGrainsByCountryAndVariety("Brazil", "Arabica"))
                .thenReturn(expectedResponse);

        List<BeansResponse> actualResponse = grainService.getFilteredBeans(country, variety);

        assertEquals(expectedResponse, actualResponse);
        verify(arrivalOfCoffeeBeansRepository).findGrainsByCountryAndVariety("Brazil", "Arabica");
    }

    @Test
    void getFilteredBeans_shouldHandleEmptyOptionalParameters() {
        Optional<String> country = Optional.empty();
        Optional<String> variety = Optional.empty();
        List<BeansResponse> expectedResponse = List.of();

        when(arrivalOfCoffeeBeansRepository.findGrainsByCountryAndVariety(null, null))
                .thenReturn(expectedResponse);

        List<BeansResponse> actualResponse = grainService.getFilteredBeans(country, variety);

        assertEquals(expectedResponse, actualResponse);
        verify(arrivalOfCoffeeBeansRepository).findGrainsByCountryAndVariety(null, null);
    }

    @Test
    void saveGrain_shouldSaveGrainAndLog() {
        ArrivalOfCoffeeBeans beans = new ArrivalOfCoffeeBeans();
        ArrivalOfCoffeeBeans savedBeans = new ArrivalOfCoffeeBeans();

        when(arrivalOfCoffeeBeansRepository.save(beans)).thenReturn(savedBeans);

        grainService.saveBeans(beans);

        verify(arrivalOfCoffeeBeansRepository).save(beans);
    }
}
