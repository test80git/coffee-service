package com.example.coffeebeans.service.impl;


import com.example.coffeebeans.model.response.RoastingLossesCountryResponse;
import com.example.coffeebeans.model.response.RoastingLossesTeamResponse;
import com.example.coffeebeans.model.response.RoastingTeamResponse;
import com.example.coffeebeans.repository.RoastingBatchRepository;
import com.example.coffeebeans.service.RoastingTeamService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoastingTeamServiceImpl implements RoastingTeamService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final RoastingBatchRepository RBRepository;

    @Override
    public List<RoastingLossesTeamResponse> getTeamLosses(String team) {

        List<RoastingLossesTeamResponse> newOutputWeightByTeam = new ArrayList<>();

        List<RoastingTeamResponse> outputWeightByTeam = RBRepository.findOutputWeightByTeam(UUID.fromString(team));

        Map<UUID, List<RoastingTeamResponse>> listMap = outputWeightByTeam.stream()
                .collect(Collectors.groupingBy(RoastingTeamResponse::getRoastingTeamId));

        for (Map.Entry<UUID, List<RoastingTeamResponse>> c : listMap.entrySet()) {
            int count = 0;
            float avgOutputWeight = 0.0f;
            RoastingLossesTeamResponse roastingLossesTeamResponse = new RoastingLossesTeamResponse();

            for (RoastingTeamResponse rtr : c.getValue()) {
                avgOutputWeight = avgOutputWeight + ((float) rtr.getOutputWeight() / rtr.getRoastingWeight() * 100);
                count++;
            }
            avgOutputWeight = 100 - (avgOutputWeight / count);

            roastingLossesTeamResponse.setRoastingTeamId(c.getKey().toString());
            roastingLossesTeamResponse.setPercentageOfLoss(avgOutputWeight);
            newOutputWeightByTeam.add(roastingLossesTeamResponse);
            LOGGER.info("newOutputWeightByTeam => {}",newOutputWeightByTeam);
        }
        return newOutputWeightByTeam;
    }

    @Override
    public List<RoastingLossesCountryResponse> getCountryLosses(String country) {
        List<RoastingLossesCountryResponse> newOutputWeightByCountry = new ArrayList<>();
        List<RoastingTeamResponse> outputWeightByCountry = RBRepository.findOutputWeightByCountry(country);

        Map<String, List<RoastingTeamResponse>> listMap =
                outputWeightByCountry.stream().collect(Collectors.groupingBy(RoastingTeamResponse::getCountryOfOrigin));


        for (Map.Entry<String, List<RoastingTeamResponse>> c : listMap.entrySet()) {
            int count = 0;
            float avgOutputWeight = 0.0f;

            RoastingLossesCountryResponse roastingLossesCountryResponse = new RoastingLossesCountryResponse();
            for (RoastingTeamResponse rtr : c.getValue()) {
                avgOutputWeight = avgOutputWeight + ((float) rtr.getOutputWeight() / rtr.getRoastingWeight() * 100);
                count++;
            }
            avgOutputWeight = 100 - (avgOutputWeight / count);

            roastingLossesCountryResponse.setCountryOfOrigin(c.getKey());
            roastingLossesCountryResponse.setPercentageOfLoss(avgOutputWeight);
            newOutputWeightByCountry.add(roastingLossesCountryResponse);
            LOGGER.info("newOutputWeightByCountry => {}",newOutputWeightByCountry);
        }
        return newOutputWeightByCountry;
    }
}
