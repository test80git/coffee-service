package com.example.coffeebeans.repository;

import com.example.coffeebeans.model.entity.RoastingBatch;
import com.example.coffeebeans.model.response.RoastingTeamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoastingBatchRepository extends JpaRepository<RoastingBatch, Integer> {

    @Query("""
            SELECT new com.example.coffeebeans.model.response.RoastingTeamResponse(r.roastingTeamId, r.outputWeight, r.roastingWeight, r.countryOfOrigin)
            FROM RoastingBatch r 
            WHERE (:team IS NULL OR r.roastingTeamId = :team)
            """)
    List<RoastingTeamResponse> findOutputWeightByTeam(@Param("team") UUID team);

    @Query("""
            SELECT new com.example.coffeebeans.model.response.RoastingTeamResponse(r.roastingTeamId, r.outputWeight, r.roastingWeight, r.countryOfOrigin)
             FROM RoastingBatch r WHERE (r.countryOfOrigin = :country)
            """)
    List<RoastingTeamResponse> findOutputWeightByCountry(@Param("country") String country);
}
