package com.example.coffeebeans.service.impl;

import com.example.coffeebeans.model.entity.ArrivalOfCoffeeBeans;
import com.example.coffeebeans.model.response.BeansResponse;
import com.example.coffeebeans.repository.ArrivalOfCoffeeBeansRepository;
import com.example.coffeebeans.service.BeansService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BeansServiceImpl implements BeansService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ArrivalOfCoffeeBeansRepository arrivalOfCoffeeBeansRepository;

    @Override
    public List<BeansResponse> getFilteredBeans(Optional<String> country,
                                                Optional<String> variety) {

        return arrivalOfCoffeeBeansRepository
                .findGrainsByCountryAndVariety(country.orElse(null), variety.orElse(null));
    }

    @Override
    @Transactional
    public void saveBeans(ArrivalOfCoffeeBeans beans) {
        ArrivalOfCoffeeBeans saveBeans = arrivalOfCoffeeBeansRepository.save(beans);
        LOGGER.info("Received event: {}", saveBeans);
    }
}
