package com.example.coffeebeans.service;



import com.example.coffeebeans.model.entity.ArrivalOfCoffeeBeans;
import com.example.coffeebeans.model.response.BeansResponse;

import java.util.List;
import java.util.Optional;

public interface BeansService {
    List<BeansResponse> getFilteredBeans(
            Optional<String> country,
            Optional<String> variety);

    void saveBeans(ArrivalOfCoffeeBeans grain);
}
