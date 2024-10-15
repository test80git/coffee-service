package com.example.coffeebeans.repository;

import com.example.coffeebeans.model.entity.ArrivalOfCoffeeBeans;
import com.example.coffeebeans.model.response.BeansResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArrivalOfCoffeeBeansRepository extends JpaRepository<ArrivalOfCoffeeBeans, Integer> {

    @Query("""
            SELECT new com.example.coffeebeans.model.response.BeansResponse(g.countryOfOrigin, g.variety, g.remainingQuantityGrainBag) 
            FROM ArrivalOfCoffeeBeans g 
            WHERE (:country IS NULL OR g.countryOfOrigin = :country) 
            AND (:variety IS NULL OR g.variety = :variety)
            """)
    List<BeansResponse> findGrainsByCountryAndVariety(@Param("country") String country, @Param("variety") String variety);
}
