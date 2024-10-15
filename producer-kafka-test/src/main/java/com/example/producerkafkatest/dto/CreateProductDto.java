package com.example.producerkafkatest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {

    private String variety;
    private String countryOfOrigin;
    private Float arabicaPercentage;
    private Float robustaPercentage;
    private Long quantityGrainBag;
}
