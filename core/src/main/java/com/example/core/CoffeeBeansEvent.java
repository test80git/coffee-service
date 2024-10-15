package com.example.core;

import java.util.Objects;


public class CoffeeBeansEvent {

    private String variety;

    private String countryOfOrigin;

    private Float arabicaPercentage;

    private Float robustaPercentage;

    private Long quantityGrainBag;

    public CoffeeBeansEvent() {
    }

    public CoffeeBeansEvent(String variety, String countryOfOrigin, Float arabicaPercentage, Float robustaPercentage, Long quantityGrainBag) {
        this.variety = variety;
        this.countryOfOrigin = countryOfOrigin;
        this.arabicaPercentage = arabicaPercentage;
        this.robustaPercentage = robustaPercentage;
        this.quantityGrainBag = quantityGrainBag;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Float getArabicaPercentage() {
        return arabicaPercentage;
    }

    public void setArabicaPercentage(Float arabicaPercentage) {
        this.arabicaPercentage = arabicaPercentage;
    }

    public Float getRobustaPercentage() {
        return robustaPercentage;
    }

    public void setRobustaPercentage(Float robustaPercentage) {
        this.robustaPercentage = robustaPercentage;
    }

    public Long getQuantityGrainBag() {
        return quantityGrainBag;
    }

    public void setQuantityGrainBag(Long quantityGrainBag) {
        this.quantityGrainBag = quantityGrainBag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeBeansEvent that = (CoffeeBeansEvent) o;
        return Objects.equals(variety, that.variety) && Objects.equals(countryOfOrigin, that.countryOfOrigin) && Objects.equals(arabicaPercentage, that.arabicaPercentage) && Objects.equals(robustaPercentage, that.robustaPercentage) && Objects.equals(quantityGrainBag, that.quantityGrainBag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variety, countryOfOrigin, arabicaPercentage, robustaPercentage, quantityGrainBag);
    }
}
