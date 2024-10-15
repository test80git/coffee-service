package com.example.coffeebeans.model.mapper;

import com.example.coffeebeans.model.entity.ArrivalOfCoffeeBeans;
import com.example.core.CoffeeBeansEvent;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BeansMapper extends Mappable<CoffeeBeansEvent, ArrivalOfCoffeeBeans> {

    @Override
    @Mapping(target = "remainingQuantityGrainBag", source = "quantityGrainBag")
    ArrivalOfCoffeeBeans toResponse(CoffeeBeansEvent event);
}
